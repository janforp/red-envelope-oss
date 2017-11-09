package com.envolope.oss.web.controller.page.console.auth.coinMall;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.model.vo.ExchangeListVo;
import com.envolope.oss.service.coinMall.CoinMallService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jan on 16/9/9.
 * 金币商城模块
 */
@Controller
@RequestMapping(value = "/page/console/auth/coinMall", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class CoinMallController {

    @Autowired
    private CoinMallService coinMallService;


    /**
     * 跳转到兑换列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeListPage")
    public String gotoExchangeListPage(HttpServletRequest request) {

        return "exchange_list";
    }

    /**
     * 兑换列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/exchangeList")
    @ResponseBody
    public String getExchangeList(HttpServletRequest request,
                                  Integer exchangeStatus,
                                  Integer pageSize,
                                  Integer pageNum){

        if (StringUtils.isEmpty(pageSize)){
            pageSize = 10;
        }
        if (StringUtils.isEmpty(pageNum)) {
            pageNum = 1;
        }

        Integer total = coinMallService.getExchangeNum(exchangeStatus);

        Integer totalPage = total / pageSize ;

        if (total % pageSize > 0 ) {
            totalPage = totalPage + 1;
        }
        if (total == 0){
            totalPage = 0;
        }

        List<ExchangeListVo> details = coinMallService.getExchangeDetail(request,exchangeStatus,pageSize,pageNum);

        Map<String,Object> map = new HashMap<>(3);
        map.put("details",details);
        map.put("pageNow",pageNum);
        map.put("totalPage",totalPage);

        return JsonUtil.buildData(map);
    }

    /**
     * 作废
     * @param request
     * @return
     */
    @RequestMapping(value = "/invalid")
    @ResponseBody
    public String invalid(HttpServletRequest request,Long id){

        return coinMallService.doInvalid(request,id);
    }

    /**
     * 弹出兑换框的数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/getDetail")
    @ResponseBody
    public String getDetailById(HttpServletRequest request,Long id){

        ExchangeListVo vo = coinMallService.getDetailByDetailId(id);
        if (vo == null) {
            return  JsonUtil.buildError("数据不存在");
        }

        Map<String,Object> map = new HashMap<>(1);
        map.put("vo",vo);
        return JsonUtil.buildData(map);
    }

    /**
     * 兑换
     * @param request
     * @param vo
     * @return
     */
    @RequestMapping(value = "/exchange")
    @ResponseBody
    public String exchange(HttpServletRequest request,ExchangeListVo vo) {

        return coinMallService.doExchange(request,vo);
    }
}
