package com.envolope.oss.web.controller.page.pub;

import com.envolope.oss.consts.RequestConsts;
import com.wujie.common.validation.support.ResourceBundleJavascriptSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by wuqiang on 16-1-9.
 *
 * @author wuqiang
 */
@RequestMapping(value = "/page/pub/resource", produces = RequestConsts.CONTENT_TYPE_JAVASCRIPT, method = RequestMethod.GET)
@Controller
public class ResourceController {
    @Autowired
    private ResourceBundleJavascriptSource messageJavascriptSource;
    @Autowired
    private ResourceBundleJavascriptSource ruleJavascriptSource;
    @Autowired
    private ResourceBundleJavascriptSource formRuleJavascriptSource;

    @RequestMapping(value = "/message.js")
    @ResponseBody
    public String message(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundleJavascriptSource.JavascriptSource javascriptSource = messageJavascriptSource.getJavascriptSource(RequestContextUtils.getLocaleResolver(request).resolveLocale(request));
        if (javascriptSource == null) {
            return "";
        }
        if (this.isModified(javascriptSource, request, response)) {
            return javascriptSource.getSource();
        } else {
            response.setStatus(304);
            return "";
        }
    }


    @RequestMapping(value = "/rule.js")
    @ResponseBody
    public String rule(HttpServletRequest request, HttpServletResponse response) {
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        ResourceBundleJavascriptSource.JavascriptSource javascriptSource = ruleJavascriptSource.getJavascriptSource(locale);
        if (javascriptSource == null) {
            return "";
        }
        if (this.isModified(javascriptSource, request, response)) {
            return javascriptSource.getSource();
        } else {
            response.setStatus(304);
            return "";
        }
    }

    @RequestMapping(value = "/form-rule.js")
    @ResponseBody
    public String formRule(HttpServletRequest request, HttpServletResponse response) {
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        ResourceBundleJavascriptSource.JavascriptSource javascriptSource = formRuleJavascriptSource.getJavascriptSource(locale);
        if (javascriptSource == null) {
            return "";
        }
        if (this.isModified(javascriptSource, request, response)) {
            return javascriptSource.getSource();
        } else {
            response.setStatus(304);
            return "";
        }
    }

    /**
     * @param javascriptSource
     * @param request
     * @return
     */
    private boolean isModified(ResourceBundleJavascriptSource.JavascriptSource javascriptSource, HttpServletRequest request, HttpServletResponse response) {
        SimpleDateFormat gmtFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);// 格林威治时间DateFormat
        gmtFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        if (!javascriptSource.isSourceCached()) {
            // 此资源服务器未缓存，则告诉客户端重新下载
            response.setHeader("Last-Modified", gmtFormat.format(new Date(javascriptSource.getLastModified())));
            return true;
        }
        String client_ifModifiedSince = request.getHeader("If-Modified-Since"); //  客户端缓存的那个文件的在服务器最后修改的时间
        if (client_ifModifiedSince == null || client_ifModifiedSince.length() == 0) {
            // 客户端没有缓存此文件，则告诉客户端重新下载
            response.setHeader("Last-Modified", gmtFormat.format(new Date(javascriptSource.getLastModified())));
            return true;
        }
        if (client_ifModifiedSince.equals(gmtFormat.format(new Date(javascriptSource.getLastModified())))) {
            // 客户端缓存的版本与服务器一致
            return false;
        }
        response.setHeader("Last-Modified", gmtFormat.format(new Date(javascriptSource.getLastModified())));
        return true;
    }
}
