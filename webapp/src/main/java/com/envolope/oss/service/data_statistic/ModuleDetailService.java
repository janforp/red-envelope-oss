package com.envolope.oss.service.data_statistic;

import com.envolope.oss.consts.ValueConsts;
import com.envolope.oss.dao.*;
import com.envolope.oss.enums.ModuleType;
import com.envolope.oss.model.vo.data_statistics.ModuleDetail;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 2016/11/29.
 * 统计每天的详情
 */
@Service
public class ModuleDetailService {

    @Autowired
    private ReRecommendTaskDAO reRecommendTaskDAO;
    @Autowired
    private ReCodeExchangeDetailDAO reCodeExchangeDetailDAO;
    @Autowired
    private ReAppTaskDAO reAppTaskDAO;
    @Autowired
    private ReNewcomerMissionDetailDAO reNewcomerMissionDetailDAO;
    @Autowired
    private ReShareMissionDetailDAO reShareMissionDetailDAO;
    @Autowired
    private RePasswordRedDetailDAO rePasswordRedDetailDAO;
    @Autowired
    private ReReceiveDetailDAO reReceiveDetailDAO;

    /**
     * 查询<code>model</code>模块的<code>oneDate</code>的详情数据(完成任务的详情)
     * @param module
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getModuleDetails(Integer module,String oneDate,Integer pageNum){

        int offset = (pageNum - 1) * ValueConsts.PAGE_SIZE;
        RowBounds bounds = new RowBounds(offset,ValueConsts.PAGE_SIZE);

        List<ModuleDetail> details = new ArrayList<>();

        if (ModuleType.great_mission.val.equals(module)){//高额任务

            details = getHighRewardDetailList(oneDate,bounds);

        }else if (ModuleType.attention_mission.val.equals(module)){//关注任务

            details = getExchangeCodeDetailList(oneDate,bounds);

        }else if (ModuleType.demo_mission.val.equals(module)){//试玩任务

            details = getDemoDetailList(oneDate,bounds);

        }else if (ModuleType.newcomer.val.equals(module)){//新手任务

            details = getNewComerDetailList(oneDate,bounds);

        }else if (ModuleType.share_mission.val.equals(module)){//分享任务

            details = getShareDetailList(oneDate,bounds);

        }else if (ModuleType.word_red.val.equals(module)){//口令红包

            details = getPasswordDetailList(oneDate,bounds);

        }else if (ModuleType.fix_red.val.equals(module)){//定时红包

            details = getFixDetailList(oneDate,bounds);

        }else if (ModuleType.alliance_mission.val.equals(module)){//联盟任务

            details = getAllianceDetailList(oneDate,bounds);
        }

        return details;
    }

    /**
     * 查询<code>model</code>模块的<code>oneDate</code>的详情数据(完成任务的详情)
     * 分页用
     * @param module
     * @param oneDate
     * @return
     */
    public Integer getNum(Integer module,String oneDate){

        if (ModuleType.great_mission.val.equals(module)){//高额任务

            return reRecommendTaskDAO.getCompleteDetailNum(oneDate);

        }else if (ModuleType.attention_mission.val.equals(module)){//关注任务

            return reCodeExchangeDetailDAO.getCompleteDetailNum(oneDate);

        }else if (ModuleType.demo_mission.val.equals(module)){//试玩任务

            return reAppTaskDAO.getCompleteDetailNum(oneDate);

        }else if (ModuleType.newcomer.val.equals(module)){//新手任务

            return reNewcomerMissionDetailDAO.getCompleteDetailNum(oneDate);

        }else if (ModuleType.share_mission.val.equals(module)){//分享任务

            return reShareMissionDetailDAO.getCompleteDetailNum(oneDate);

        }else if (ModuleType.word_red.val.equals(module)){//口令红包

            return rePasswordRedDetailDAO.getCompleteDetailNum(oneDate);

        }else if (ModuleType.fix_red.val.equals(module)){//定时红包

            return reReceiveDetailDAO.getCompleteDetailNum(oneDate);

        }else if (ModuleType.alliance_mission.val.equals(module)){//联盟任务

            return 0;
        }

        return 0;

    }

    /**
     * 高额任务某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getHighRewardDetailList(String oneDate,RowBounds bounds){

        return reRecommendTaskDAO.getCompleteDetailByDate(oneDate,bounds);
    }

    /**
     * 关注任务某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getExchangeCodeDetailList(String oneDate,RowBounds bounds){

        return reCodeExchangeDetailDAO.getCompleteDetailByDate(oneDate,bounds);
    }

    /**
     * 试玩任务某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getDemoDetailList(String oneDate,RowBounds bounds){

        return reAppTaskDAO.getCompleteDetailByDate(oneDate,bounds);
    }

    /**
     * 新手任务某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getNewComerDetailList(String oneDate,RowBounds bounds){

        return reNewcomerMissionDetailDAO.getCompleteDetailByDate(oneDate,bounds);
    }

    /**
     * 分享到朋友圈点击任务某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getShareDetailList(String oneDate,RowBounds bounds){

        return reShareMissionDetailDAO.getCompleteDetailByDate(oneDate,bounds);
    }

    /**
     * 口令红包某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getPasswordDetailList(String oneDate,RowBounds bounds){

        return rePasswordRedDetailDAO.getCompleteDetailByDate(oneDate,bounds);
    }

    /**
     * 定时红包某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getFixDetailList(String oneDate,RowBounds bounds){

        return reReceiveDetailDAO.getCompleteDetailByDate(oneDate,bounds);
    }

    /**
     * 联盟任务某天完成的所有记录详情
     * @param oneDate
     * @return
     */
    public List<ModuleDetail> getAllianceDetailList(String oneDate,RowBounds bounds){

        return null;
    }

}
