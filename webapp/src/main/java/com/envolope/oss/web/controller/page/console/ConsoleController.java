package com.envolope.oss.web.controller.page.console;

import com.envolope.oss.consts.RequestConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wuqiang on 15-12-30.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/page/console", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
@Controller
public class ConsoleController {

    @RequestMapping("/index")
    public String console() {
        return "console/console";
    }

}
