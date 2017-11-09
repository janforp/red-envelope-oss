package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.RequestConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/page/console/auth/cash", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class CashRedController {

    /**
     * 现金红包首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){

        return "/redManager/cash_red";
    }


}



