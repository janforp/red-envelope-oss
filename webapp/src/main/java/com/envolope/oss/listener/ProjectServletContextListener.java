package com.envolope.oss.listener;


import com.envolope.oss.config.Config;
import com.envolope.oss.consts.AttributeConsts;
import com.wujie.common.utils.support.fastjson.FastJsonHack;

import javax.servlet.ServletContextEvent;

/**
 * Created by wuqiang on 15-8-4.
 *
 * @author wuqiang
 */
public class ProjectServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setAttribute(AttributeConsts.RESOURCE_VERSION, "?_v=" + System.currentTimeMillis());
        servletContextEvent.getServletContext().setAttribute(AttributeConsts.RESOURCE_MIN, Config.isDebug() ? "" : ".min");
        FastJsonHack.hack();
        Config.init(); // 什么以不执行, 只是触发执行static{}
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
