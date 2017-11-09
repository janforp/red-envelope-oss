package com.envolope.oss.web.controller.page.console.auth;

import com.envolope.oss.consts.RequestConsts;
import com.envolope.oss.dao.ReScoreDetailDAO;
import com.envolope.oss.dao.ReUserDAO;
import com.envolope.oss.model.ReUser;
import com.envolope.oss.service.userManager.UserService;
import com.envolope.oss.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jan on 16/10/9.
 */
@Controller
@RequestMapping(value = "/page/test", produces = RequestConsts.CONTENT_TYPE_HTML, method = {RequestMethod.GET, RequestMethod.POST})
public class TestController {

    @Autowired
    private ReUserDAO reUserDAO;


    @RequestMapping(value = "/axios")
    public String axios(HttpServletRequest request,Integer userId){

        return "/test/axios";
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String getDataByAxios(HttpServletRequest request,Integer userId){

        return JsonUtil.buildData(reUserDAO.selectByPrimaryKey(userId));
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    @ResponseBody
    public String postRequestByAxios(HttpServletRequest request,Integer userId,String createTime){

        return JsonUtil.buildData(reUserDAO.selectByPrimaryKey(32));
    }
}
