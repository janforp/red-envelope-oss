package com.envolope.oss.web.controller.page.pub;

import com.envolope.oss.consts.RequestConsts;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wuqiang on 16-1-29.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/page/pub/error", produces = RequestConsts.CONTENT_TYPE_HTML,
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.TRACE})
@Controller
public class ErrorController {
    @RequestMapping(value = "/404")
    public String e404(HttpServletRequest request) {
        return RequestConsts.ERROR_404_PAGE_RESULT;
    }

    @RequestMapping(value = "/403")
    public String e403(HttpServletRequest request) {
        return RequestConsts.ERROR_403_PAGE_RESULT;
    }

    @RequestMapping(value = "/500")
    public String e500(HttpServletRequest request) {
        return RequestConsts.ERROR_500_PAGE_RESULT;
    }
}
