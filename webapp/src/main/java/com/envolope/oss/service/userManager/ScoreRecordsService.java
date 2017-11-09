package com.envolope.oss.service.userManager;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.ReScoreDetailDAO;
import com.envolope.oss.dao.ReUserDAO;
import com.envolope.oss.model.ReAccountDetail;
import com.envolope.oss.model.ReScoreDetail;
import com.envolope.oss.model.ReUser;
import com.envolope.oss.model.vo.user.UserMoneyVo;
import com.envolope.oss.model.vo.user.UserScoreVo;
import com.envolope.oss.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jan on 16/10/13.
 * 金币记录
 */
@Service
public class ScoreRecordsService {

    @Autowired
    private ReScoreDetailDAO reScoreDetailDAO ;
    @Autowired
    private ReUserDAO reUserDAO;

    /**
     * 总数,分页用
     * @param userId
     * @return
     */
    public Integer getNum(HttpServletRequest request, Integer userId){


        return reScoreDetailDAO.getNum(userId);
    }

    /**
     * 列表查询
     * @param request
     * @param userId
     * @return
     */
    public List<ReScoreDetail> getList(HttpServletRequest request, Integer pageNum, Integer userId){


        int offset = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset,ValueConsts.PAGE_SIZE);

        List<ReScoreDetail> details = reScoreDetailDAO.getList(userId,bounds);

        return details;
    }

    /**
     * 获取个人金钱记录上面的数据
     * @param userId
     * @return
     */
    public UserScoreVo getScoreVo(Integer userId){

        UserScoreVo vo = new UserScoreVo();
        vo.setUserId(userId);

        ReUser user = reUserDAO.selectByPrimaryKey(userId);

        Integer score = user.getUserScore();
        if (StringUtil.isEmpty(score)){
            score = 0;
        }
        vo.setScore(score);

        //累积获得的金币 类型;0:支出,1:收入
        Integer totalGetScore = reScoreDetailDAO.getTotalScoreByUserIdAndType(userId,1);
        if (StringUtil.isEmpty(totalGetScore)){
            totalGetScore = 0;
        }
        vo.setTotalGetScore(totalGetScore);

        //累积兑换的金币
        Integer totalExchangeScore = reScoreDetailDAO.getTotalScoreByUserIdAndType(userId,0);
        if (StringUtil.isEmpty(totalExchangeScore)){
            totalExchangeScore = 0;
        }
        vo.setTotalExchangeScore(totalExchangeScore);

        return vo;
    }

}
