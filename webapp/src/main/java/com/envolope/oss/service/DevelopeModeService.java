package com.envolope.oss.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.envolope.oss.config.Config;
import com.envolope.oss.dao.ReAccessTokenDAO;
import com.envolope.oss.dao.ReCustomerDAO;
import com.envolope.oss.dao.WxmsgKeywordReplyRuleDAO;
import com.envolope.oss.model.ReAccessToken;
import com.envolope.oss.model.ReCustomer;
import com.envolope.oss.model.WxmsgKeywordReplyRule;
import com.envolope.oss.model.vo.KeywordAutoReplyVo;
import com.envolope.oss.model.vo.ResultInfo;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.weixin.GetCurrentSelfMenuInfoRequest2;
import com.envolope.oss.weixin.GetCurrentSelfMenuInfoResponse2;
import com.envolope.oss.weixin.SelfMenuInfoButtonDomain;
import com.envolope.oss.weixin.SelfMenuInfoDomain;
import com.wujie.common.redis.StringKeyRedisTemplate;
import com.wujie.common.sdk.weixin.official_account.DefaultWeixinClient;
import com.wujie.common.sdk.weixin.official_account.domain.token.TokenDomain;
import com.wujie.common.sdk.weixin.official_account.enums.MenuBtnType;
import com.wujie.common.sdk.weixin.official_account.param.menu.ButtonParam;
import com.wujie.common.sdk.weixin.official_account.param.menu.MenuParam;
import com.wujie.common.sdk.weixin.official_account.request.menu.MenuCreateRequest;
import com.wujie.common.sdk.weixin.official_account.request.message.GetCurrentAutoreplyInfoRequest;
import com.wujie.common.sdk.weixin.official_account.request.token.TokenRequest;
import com.wujie.common.sdk.weixin.official_account.response.message.GetCurrentAutoreplyInfoResponse;
import com.wujie.common.sdk.weixin.official_account.response.token.TokenResponse;
import com.wujie.common.sdk.weixin.wxmsg.out.impl.WxOutNewsMsg;
import com.wujie.common.sdk.weixin.wxmsg.out.impl.WxOutTextMsg;
import com.wujie.common.sdk.weixin.wxmsg.out.support.WxOutArticle;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by Jan on 16/8/4.
 *
 * 开发者模型
 */
@Service
public class DevelopeModeService {


    @Autowired
    private ReCustomerDAO reCustomerDAO;
    @Autowired
    private ReAccessTokenDAO reAccessTokenDAO;
    @Autowired
    private WxmsgKeywordReplyRuleDAO wxmsgKeywordReplyRuleDAO;
    @Autowired
    private StringKeyRedisTemplate wxmsgRedisTemplate;


    private byte[] toJSONStringBytes(Object object) throws UnsupportedEncodingException {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect).getBytes(Config.CHARSET);
    }

    /**
     * 设置 公众号 菜单
     * @param customerId
     * @return
     */
    public String doSetMenu(Integer customerId) throws IOException {

        ReCustomer customer = reCustomerDAO.selectByPrimaryKey(customerId);
        if (customer == null){
            return JsonUtil.buildError("客户不存在");
        }

        DefaultWeixinClient client = new DefaultWeixinClient(customer.getCustomerAppid(),customer.getCustomerAppsecret());

        //获取菜单的请求
        GetCurrentSelfMenuInfoRequest2 selfMenuInfoRequest = new GetCurrentSelfMenuInfoRequest2();

        //获取
        GetCurrentSelfMenuInfoResponse2 response = client.execute(selfMenuInfoRequest);

        SelfMenuInfoDomain domain = response.getSelfMenuInfo();

        //生成菜单的请求
        MenuCreateRequest menuCreateRequest = new MenuCreateRequest();
        //需要向生成菜单中加入 数据,response中有之前菜单的数据

        MenuParam param = turnSelfMenuInfoDomainToMenuParam(domain);

        menuCreateRequest.setParam(param);

        client.execute(menuCreateRequest);


        return JsonUtil.buildSuccess();
    }

    /**
     * 拿到的菜单数据 塞进 创建菜单 请求中
     * @param domain
     * @return
     */
    public MenuParam turnSelfMenuInfoDomainToMenuParam (SelfMenuInfoDomain domain) {

        MenuParam param = new MenuParam();

        List<SelfMenuInfoButtonDomain> buttonDomains = domain.getButton();

        List<ButtonParam> buttonParams = new ArrayList<>();

        for (SelfMenuInfoButtonDomain buttonDomain : buttonDomains) {

            ButtonParam buttonParam = new ButtonParam(true);

            buttonParam.setName(buttonDomain.getName());

            if (buttonDomain.getSubButton() == null) { // 不存在子菜单

                String rootType = buttonDomain.getType();

                if(!"text".equals(rootType)) { // 不支持text类型

                    if ("news".equals(rootType) || "video".equals(rootType) || "voice".equals(rootType) || "img".equals(rootType)) {
                        rootType = "media_id";
                        buttonParam.setMedia_id(buttonDomain.getValue());
                    }else if("media_id".equals(rootType)) {
                        buttonParam.setMedia_id(buttonDomain.getMedia_id());
                    }

                    buttonParam.setType(MenuBtnType.valueOf(rootType));
                    buttonParam.setKey(buttonDomain.getKey());
                    buttonParam.setUrl(buttonDomain.getUrl());


                    buttonParams.add(buttonParam);
                }


            }else { // 子菜单

                List<ButtonParam> subButton = new ArrayList<>();

                List<SelfMenuInfoButtonDomain> subButtonInfos = buttonDomain.getSubButton();

                if (subButtonInfos != null && subButtonInfos.size() >0) {

                    for (SelfMenuInfoButtonDomain subButtonInfo : subButtonInfos){

                        ButtonParam subButtonParam = new ButtonParam(false);

                        String subType = subButtonInfo.getType();

                        if(!"text".equals(subType)) { // 不支持text类型

                            if ("news".equals(subType) || "video".equals(subType) || "voice".equals(subType) || "img".equals(subType)) {
                                subType = "media_id";
                                subButtonParam.setMedia_id(subButtonInfo.getValue());
                            }else if ("media_id".equals(subType)){
                                subButtonParam.setMedia_id(subButtonInfo.getMedia_id());
                            }

                            subButtonParam.setType(MenuBtnType.valueOf(subType));
                            subButtonParam.setName(subButtonInfo.getName());
                            subButtonParam.setKey(subButtonInfo.getKey());
                            subButtonParam.setUrl(subButtonInfo.getUrl());

                            subButton.add(subButtonParam);

                        }

                    }
                    buttonParam.setSub_button(subButton);
                    buttonParams.add(buttonParam);
                }

            }

        }
        param.setButton(buttonParams);

        return param;
    }


    /**
     * 保存 公众号 自动回复配置
     * @param customerId
     * @return
     * @throws IOException
     */
    public String doSaveAutoReply(Integer customerId) throws IOException {

        ReCustomer customer = reCustomerDAO.selectByPrimaryKey(customerId);
        if (customer == null){
            return JsonUtil.buildError("客户不存在");
        }

        DefaultWeixinClient client = new DefaultWeixinClient(customer.getCustomerAppid(),customer.getCustomerAppsecret());

        GetCurrentAutoreplyInfoRequest autoreplyInfoRequest = new GetCurrentAutoreplyInfoRequest();

        GetCurrentAutoreplyInfoResponse autoreplyInfoResponse = client.execute(autoreplyInfoRequest);

        List<Map<String, Object>> keywordAutoreplyInfo = autoreplyInfoResponse.getKeywordAutoreplyInfo();

        if(keywordAutoreplyInfo != null) { // 关键字 自动回复

            for (Map<String, Object> map : keywordAutoreplyInfo) {

                String rule_name = (String) map.get("rule_name");
                long create_time = (long) map.get("create_time");
                int matchMode = 0;

                List<Map<String, String>> keywordList = (List<Map<String, String>>) map.get("keyword_list_info");

                String keywords = "";

                for (Map info : keywordList) {
                    if("".equals(keywords)) {
                        keywords = "#"+info.get("content")+"#";
                    }else {
                        keywords += "_#"+info.get("content")+"#";
                    }

                    if("equal".equals(info.get("match_mode"))){
                        matchMode = 1;
                    }
                }

                List<Map<String, Object>> replyList = (List<Map<String, Object>>) map.get("reply_list_info");

                for (Map info : replyList) {

                    String type = (String) info.get("type");

                    if("text".equals(type)) { // 文字消息

                        String content = (String) info.get("content");

                        WxOutTextMsg textMsg = new WxOutTextMsg();
                        textMsg.setContent(content);

                        WxmsgKeywordReplyRule rule = new WxmsgKeywordReplyRule();
                        rule.setRuleName(rule_name);
                        rule.setCustomerId(customerId);
                        rule.setKeywords(keywords);
                        rule.setMatchMode(matchMode);
                        rule.setMsgType(type);
                        rule.setMsgBody(JSON.toJSONString(textMsg));
                        rule.setCreateTime(create_time);
                        wxmsgKeywordReplyRuleDAO.insert(rule);


                    }else if("news".equals(type)){ // 图文消息

                        Map<String, Object> news_info = (Map<String, Object>) info.get("news_info");

                        List<Map<String, Object>> list = (List<Map<String, Object>>) news_info.get("list");


                        List<WxOutArticle> articles = new ArrayList<>();


                        for (Map newsInfo : list) {

                            WxOutArticle article = new WxOutArticle();
                            article.setTitle((String) newsInfo.get("title"));
                            article.setDescription((String) newsInfo.get("digest"));
                            article.setPicUrl((String) newsInfo.get("cover_url"));
                            article.setUrl((String) newsInfo.get("content_url"));
                            articles.add(article);

                        }

                        WxOutNewsMsg newsMsg = new WxOutNewsMsg();
                        newsMsg.setArticleCount(list.size());
                        newsMsg.setArticles(articles);


                        WxmsgKeywordReplyRule rule = new WxmsgKeywordReplyRule();
                        rule.setRuleName(rule_name);
                        rule.setCustomerId(customerId);
                        rule.setKeywords(keywords);
                        rule.setMatchMode(matchMode);
                        rule.setMsgType(type);
                        rule.setMsgBody(JSON.toJSONString(newsMsg));
                        rule.setCreateTime(create_time);
                        wxmsgKeywordReplyRuleDAO.insert(rule);


                    }


                }

            }

        }


        // 设置缓存
        List<WxmsgKeywordReplyRule> ruleList = wxmsgKeywordReplyRuleDAO.selectByCustomerId(customerId);

        Map<String, List<KeywordAutoReplyVo>> map = new HashMap<>();
        if (ruleList != null && ruleList.size() > 0) {
            for (WxmsgKeywordReplyRule rule : ruleList) {
                List<String> keywordList = new ArrayList<>();
                String[] keywordArray = rule.getKeywords().split("_");
                for (String k : keywordArray) {
                    k = k.substring(1, k.length() - 1).trim();
                    if (!keywordList.contains(k)) {
                        keywordList.add(k);
                        List<KeywordAutoReplyVo> voList = map.get(k);
                        if (voList == null) {
                            voList = new ArrayList<>();
                        }
                        KeywordAutoReplyVo vo = new KeywordAutoReplyVo();
                        vo.setRuleId(rule.getRuleId());
                        vo.setMatchMode(rule.getMatchMode());
                        vo.setCustomerId(rule.getCustomerId());
                        voList.add(vo);
                        map.put("k"+customerId+"_"+k, voList);
                    }
                }
            }
        }
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            List<KeywordAutoReplyVo> voList = map.get(key);
            if (voList != null && voList.size() > 0) {
                wxmsgRedisTemplate.set(key, toJSONStringBytes(voList));
            }
        }

        return JsonUtil.buildSuccess();

    }


    /**
     * 清空 公众号 自动回复配置
     * @param customerId
     * @return
     */
    public String clearAutoReply(Integer customerId) throws IOException {

        ReCustomer customer = reCustomerDAO.selectByPrimaryKey(customerId);
        if (customer == null){
            return JsonUtil.buildError("客户不存在");
        }

        wxmsgKeywordReplyRuleDAO.deleteByCustomerId(customerId);

        // 清空缓存
        Set<String> keySet = wxmsgRedisTemplate.keys("k"+customerId+"_*");

        for (String key : keySet) {
            wxmsgRedisTemplate.delete(key);
        }

        return JsonUtil.buildSuccess();

    }


    /**
     * 重置 自动回复配置 缓存
     * @return
     * @throws IOException
     */
    public String rebuildAutoReply() throws IOException {

        // 清空缓存
        Set<String> oKeySet = wxmsgRedisTemplate.keys("k*");
        for (String key : oKeySet) {
            wxmsgRedisTemplate.delete(key);
        }

        // 设置缓存
        List<WxmsgKeywordReplyRule> ruleList = wxmsgKeywordReplyRuleDAO.selectAll();

        Map<String, List<KeywordAutoReplyVo>> map = new HashMap<>();
        if (ruleList != null && ruleList.size() > 0) {
            for (WxmsgKeywordReplyRule rule : ruleList) {
                List<String> keywordList = new ArrayList<>();
                String[] keywordArray = rule.getKeywords().split("_");
                for (String k : keywordArray) {
                    k = k.substring(1, k.length() - 1).trim();
                    if (!keywordList.contains(k)) {
                        keywordList.add(k);
                        List<KeywordAutoReplyVo> voList = map.get(k);
                        if (voList == null) {
                            voList = new ArrayList<>();
                        }
                        KeywordAutoReplyVo vo = new KeywordAutoReplyVo();
                        vo.setRuleId(rule.getRuleId());
                        vo.setMatchMode(rule.getMatchMode());
                        vo.setCustomerId(rule.getCustomerId());
                        voList.add(vo);
                        map.put("k"+rule.getCustomerId()+"_"+k, voList);
                    }
                }
            }
        }
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            List<KeywordAutoReplyVo> voList = map.get(key);
            if (voList != null && voList.size() > 0) {
                wxmsgRedisTemplate.set(key, toJSONStringBytes(voList));
            }
        }

        return JsonUtil.buildSuccess();

    }

    /**
     * 重新初始化微信公众号配置缓存Map
     * @return
     * @throws IOException
     */
    public String rebuildWxCache() {

        HttpClient httpClient = new HttpClient();

        GetMethod getMethod = new GetMethod(Config.getRebuildWxAppCfgCacheUrl());

        int code = 0;

        try{
            httpClient.executeMethod(getMethod);//发送请求
            byte[] responseBody = getMethod.getResponseBody();// 读取为字节数组
            String response = new String(responseBody, Config.CHARSET);
            ResultInfo result = JSON.parseObject(response, ResultInfo.class);
            code = result.getCode();
        }catch (HttpException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            getMethod.releaseConnection();//关闭连接
        }

        if(code != 0) {
            return JsonUtil.buildError();
        }

        return JsonUtil.buildSuccess();

    }




    /**
     * 获取 token
     * @param appId
     * @param appSecret
     * @return
     */
    public String getAccessToken(String appId, String appSecret) throws IOException {

        ReAccessToken reAccessToken = reAccessTokenDAO.selectByPrimaryKey(appId);

        if(reAccessToken != null) {

            long invalidTime = reAccessToken.getTimeMillis();
            long now = System.currentTimeMillis();

            if(invalidTime > now) { //未过期
                return reAccessToken.getAccessToken();
            }

            // 已过期
            TokenRequest request = new TokenRequest(appId, appSecret);
            DefaultWeixinClient client = new DefaultWeixinClient(appId,appSecret);
            TokenResponse response = client.execute(request);
            TokenDomain tokenDomain = response.getToken();

            //把新的token存入到数据库
            Long expire_in = System.currentTimeMillis() + (long)tokenDomain.getExpires_in() - 100*1000;
            reAccessToken.setAccessToken(tokenDomain.getAccess_token());
            reAccessToken.setTimeMillis(expire_in);
            reAccessTokenDAO.updateByPrimaryKey(reAccessToken);

            return tokenDomain.getAccess_token();

        }


        TokenRequest request = new TokenRequest(appId, appSecret);
        DefaultWeixinClient client = new DefaultWeixinClient(appId,appSecret);
        TokenResponse response = client.execute(request);
        TokenDomain tokenDomain = response.getToken();


        ReAccessToken accessToken = new ReAccessToken();
        accessToken.setAccessToken(tokenDomain.getAccess_token());
        accessToken.setAppId(appId);
        accessToken.setTimeMillis(System.currentTimeMillis() + (long)tokenDomain.getExpires_in() - 100*1000);
        reAccessTokenDAO.insert(accessToken);

        return tokenDomain.getAccess_token();

    }

}
