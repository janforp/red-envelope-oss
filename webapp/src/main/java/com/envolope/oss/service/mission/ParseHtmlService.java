package com.envolope.oss.service.mission;

import com.envolope.oss.dao.ReAppMarketDAO;
import com.envolope.oss.model.ReAppMarket;
import com.envolope.oss.model.vo.mission.ParseData;
import com.envolope.oss.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Jan on 16/7/18.
 *
 * 解析html
 */
@Service
public class ParseHtmlService {

    @Autowired
    private ReAppMarketDAO reAppMarketDAO;


    /**
     * 从网页上爬 商品 数据
     * @param request
     * @param url
     * @param site
     * @return
     */
    public ParseData parseHtmlToGetData (HttpServletRequest request, String url, Integer site) throws IOException {

        ReAppMarket market = reAppMarketDAO.selectByPrimaryKey(site);
        String marketName = market.getMarketName();

        ParseData mission = null;

        Document doc = getDocument(url);

        if (marketName.contains("应用宝")) {    //应用宝

            mission = parseYingYongBao(doc);

        }else if (marketName.contains("小米")) {//小米

            mission = parseXiaoMi(doc);

        }else if(marketName.contains("360手机助手")){ //360

            mission = parse360 (doc);
        }else if (marketName.contains("OPPO软件商店")) {   //OPPO

            mission = parseOPPO (doc);
        }else if (marketName.contains("魅族")) { //魅族

            mission = parseMeZu (doc);
        }else if (marketName.contains("豌豆荚")) { //豌豆荚

            mission = parseWanDouJia (doc);
        }else if (marketName.contains("华为应用市场")) { //华为

            mission = parseHuaWei (doc);
        }else if (marketName.contains("百度手机助手")) { //百度手机助手

            mission = parseBaiduMobileAssistant(doc);
        }else if (marketName.contains("应用汇")) { //应用汇 http://www.appchina.com/
            mission = parseYingYongHui(doc);
        }else if (marketName.contains("PP")) { //PP助手

            mission = parsePP(doc);
        }else if (marketName.contains("搜狗手机助手")) { //搜狗手机助手

            mission = parseSouGou(doc);
        }else if (marketName.contains("安卓市场")){//安卓市场／百度旗下品牌 http://apk.hiapk.com/
            mission = parseAnZhuoShiChang(doc);
        }else if (marketName.contains("安智")){

            mission = anZhiMarket(doc);
        }
        //统一解析包名
        mission.setAppPackage(parseUrlToGetPkgName(url,site));

        String size = mission.getSize();
        size = size.replace("大小：","").replace("大小：","").replace("b","").replace("B","").replace("m","").replace("M","");

        mission.setSize(size);

        return mission;
    }

    /**
     * 安智市场：http://www.anzhi.com/
     * @param doc
     * @return
     */
    public ParseData anZhiMarket(Document doc){

        ParseData data = new ParseData();


        Elements names = doc.getElementsByClass("detail_description");
        if (names != null && names.size()>0){
            String name = names.get(0).getElementsByTag("h3").get(0).text();
            data.setAppName(name);
        }

        Element sizes = doc.getElementById("detail_line_ul");
        if (sizes != null){
            String size = sizes.getElementsByClass("spaceleft").get(1).text();
            data.setSize(size);
        }
        return data;
    }

    /**
     * 解析搜狗手机助手：http://zhushou.sogou.com/apps/
     * @param doc
     * @return
     */
    public ParseData parseSouGou(Document doc){
        ParseData mission = new ParseData();

        Elements overViews = doc.getElementsByClass("overview");
        if (overViews != null && overViews.size()>0){
            String icon = overViews.get(0).getElementsByTag("img").attr("src");
            mission.setAppIcon(icon);

            String name = overViews.get(0).getElementsByClass("name").get(0).text();
            mission.setAppName(name);

            String size = overViews.get(0).getElementsByTag("span").get(1).text();
            mission.setSize(size);
        }


        return mission;
    }
    /**
     * 安卓市场 http://apk.hiapk.com/
     * @param doc
     * @return
     */
    public ParseData parseAnZhuoShiChang(Document doc){

        ParseData mission = new ParseData();

        Elements detail = doc.getElementsByClass("detail_content");
        if (detail != null && detail.size() >0){
            String icon = detail.get(0).getElementsByTag("img").get(0).attr("src");
            mission.setAppIcon(icon);

        }
        Element name = doc.getElementById("appSoftName");
        if (name!=null){
            mission.setAppName(name.text());
        }
        Element size = doc.getElementById("appSize");
        if (size != null){
            mission.setSize(size.text());
        }

        return mission;
    }
    /**
     * PP助手
     * @param doc
     * @return
     */
    public ParseData parsePP(Document doc){

        ParseData mission = new ParseData();

        Elements strongs = doc.getElementsByTag("strong");
        if (strongs != null && strongs.size() >1){
            String size = strongs.get(1).text();
            mission.setSize(size);
        }
        Elements icons = doc.getElementsByClass("app-icon");
        if (icons != null && icons.size() >0){
            String icon = icons.get(0).getElementsByTag("img").attr("src");
            mission.setAppIcon(icon);
        }
        Elements names = doc.getElementsByClass("app-title");
        if (names != null && names.size()>0){
            String name = names.get(0).text();
            mission.setAppName(name);
        }

        return mission;
    }
    /**
     * http://www.appchina.com/ 应用汇
     * @param doc
     * @return
     */
    public ParseData parseYingYongHui(Document doc){

        ParseData mission = new ParseData();

        Elements names = doc.getElementsByClass("app-name");
        if (names != null && names.size()>0){
            String name = names.get(0).text();
            mission.setAppName(name);
        }
        Elements icons = doc.getElementsByClass("Content_Icon");
        if (icons != null && icons.size()>0){
            String icon = icons.get(0).attr("src");
            mission.setAppIcon(icon);
        }
        Elements sizes = doc.getElementsByClass("art-content");
        if (sizes != null && sizes.size() >0){
            String size = sizes.get(0).text();
            mission.setSize(size);
        }
        return mission;
    }

    /**
     * 解析一个应用宝市场的 应用链接 获取一个AsoMission 对象
     * @param doc
     * @return
     */
    public ParseData parseYingYongBao (Document doc) {

        ParseData mission = new ParseData();
        List<Element> names = doc.getElementsByAttributeValue("class", "det-name-int");
        if (names != null && names.size() > 0) {
            Element elementName = names.get(0);
            String appName = elementName.text();
            mission.setAppName(appName);
        }

        Elements sizes = doc.getElementsByClass("det-size");
        if (!StringUtil.isEmpty(sizes)){
            String size = sizes.get(0).text();
            mission.setSize(size);
        }

        Elements icons = doc.getElementsByClass("det-icon");
        if (!StringUtil.isEmpty(icons)){
            Element iconDiv = icons.get(0);
            Elements imgs = iconDiv.getElementsByTag("img");
            if (!StringUtil.isEmpty(imgs)){
                String icon = imgs.get(0).attr("src");
                mission.setAppIcon(icon);
            }

        }

        return mission;
    }

    /**
     * 解析一个百度手机助手app：http://shouji.baidu.com/
     * @param doc
     * @return
     */
    public ParseData parseBaiduMobileAssistant(Document doc){

        ParseData mission = new ParseData();

        Elements imgDiv = doc.getElementsByClass("app-pic");
        if (imgDiv != null && imgDiv.size()>0){
            String icon = imgDiv.get(0).getElementsByTag("img").attr("src");
            mission.setAppIcon(icon);
        }
        Elements names = doc.getElementsByClass("app-name");
        if (names != null && names.size()>0){
            String name = names.get(0).text();
            mission.setAppName(name);
        }
        Elements sizes = doc.getElementsByClass("app-intro");
        if (sizes != null && sizes.size()>0){
            String size = sizes.get(0).getElementsByClass("size").get(0).text();
            mission.setSize(size);
        }
        return mission;
    }

    /**
     * 解析一个小米市场的 应用链接 获取一个AsoMission 对象
     * @param doc       链接的dom
     * @return
     */
    public ParseData parseXiaoMi (Document doc) {

        ParseData mission = new ParseData();
        List<Element> names = doc.getElementsByTag("h3");
        String appName = names.get(0).text();
        mission.setAppName(appName);

        Elements imgDiv = doc.getElementsByClass("app-info");
        if (imgDiv != null && imgDiv.size() > 0){
            String icon = imgDiv.get(0).getElementsByTag("img").get(0).attr("src");
            mission.setAppIcon(icon);
        }

        Elements sizeUl = doc.getElementsByClass("cf");
        if (sizeUl != null && sizeUl.size()>=2){
            String size = sizeUl.get(2).getElementsByTag("li").get(1).text();
            mission.setSize(size);
        }

        return mission;
    }

    /**
     * 解析一个360市场的 应用链接 获取一个AsoMission 对象
     * @param doc       链接的dom
     * @return
     */
    public ParseData parse360 (Document doc) {

        ParseData mission = new ParseData();

        List<Element> names = doc.getElementsByTag("dd");
        Element name = names.get(0).child(0);
        String  appName = name.text();
        mission.setAppName(appName);

        Elements softInfoDl = doc.getElementsByClass("software-info");
        if (softInfoDl != null && softInfoDl.size() >0){

            Element info = softInfoDl.get(0);
            String  icon = info.getElementsByTag("img").get(0).attr("src");
            mission.setAppIcon(icon);

            String size = info.getElementsByClass("num").get(0).text();
            mission.setSize(size);
        }

        return mission;
    }

    /**
     * 解析一个OPPO市场的 应用链接 获取一个AsoMission 对象
     * @param doc       链接的dom
     * @return
     */
    public ParseData parseOPPO (Document doc) {

        ParseData mission = new ParseData();

        List<Element> names = doc.getElementsByClass("soft_info_middle");
        Element name = names.get(0).child(0);
        String appName = name.text();
        mission.setAppName(appName);

        Elements img = doc.getElementsByClass("soft_info_left");
        if (img!= null && img.size() >0){
            String icon = img.get(0).getElementsByTag("img").get(0).attr("dataimg");
            mission.setAppIcon(icon);
        }

        Elements sizeUl = doc.getElementsByClass("soft_info_more");
        if (sizeUl!= null && sizeUl.size() >0){
            String size = sizeUl.get(0).getElementsByTag("li").get(1).text();
            mission.setSize(size);
        }



        return mission;
    }


    /**
     * 解析一个MeZu市场的 应用链接 获取一个AsoMission 对象
     * @param doc       链接的dom
     * @return
     */
    public ParseData parseMeZu (Document doc) {

        ParseData mission = new ParseData();

        List<Element> names = doc.getElementsByAttribute("data-name");
        Element name = names.get(0);
        String appName = name.attr("data-name");
        mission.setAppName(appName);

        Elements sizdDiv = doc.getElementsByClass("app_content");
        if(sizdDiv != null && sizdDiv.size() >=6){
            String size = sizdDiv.get(5).text();
            mission.setSize(size);
        }

        Elements imgDiv = doc.getElementsByClass("app_download");
        if (imgDiv != null && imgDiv.size()>0){
            String icon = imgDiv.get(0).getElementsByTag("img").attr("src");
            mission.setAppIcon(icon);
        }

        return mission;
    }

    /**
     * 解析一个豌豆荚市场的 应用链接 获取一个AsoMission 对象
     * @param doc       链接的dom
     * @return
     */
    public ParseData parseWanDouJia (Document doc) {

        ParseData mission = new ParseData();

        List<Element> names = doc.getElementsByClass("title");
        Element name = names.get(0);
        String appName = name.text();
        mission.setAppName(appName);

        Elements infos = doc.getElementsByClass("infos-list");
        if (infos != null && infos.size() >0){
            String size = infos.get(0).getElementsByTag("dd").get(0).text();
            mission.setSize(size);
        }

        Elements icons = doc.getElementsByClass("app-icon");
        if (icons != null && icons.size() >0){
            String icon = icons.get(0).getElementsByTag("img").get(0).attr("src");
            mission.setAppIcon(icon);
        }

        return mission;
    }

    /**
     * 解析一个百度市场的 应用链接 获取一个AsoMission 对象
     * @param doc       链接的dom
     * @return
     */
    public ParseData parseBaiDu (Document doc) {

        ParseData mission = new ParseData();

        Element name = doc.getElementById("appSoftName");
        String  appName = name.text();
        mission.setAppName(appName);

        return mission;
    }
    /**
     * 解析一个华为市场的 应用链接 获取一个AsoMission 对象
     * @param doc       链接的dom
     * @return
     */
    public ParseData parseHuaWei (Document doc) {

        ParseData mission = new ParseData();

        Element name = doc.getElementById("appName");
        String appName = name.attr("value");
        mission.setAppName(appName);

        Elements img = doc.getElementsByClass("app-ico");
        if (img != null && img.size() >0){
            String icon = img.get(0).attr("lazyload");
            mission.setAppIcon(icon);
        }
        Elements appInfo = doc.getElementsByClass("app-info-ul");
        if (appInfo != null && appInfo.size()>=2){
            String size = appInfo.get(1).getElementsByTag("li").get(0).text();
            mission.setSize(size);
        }

        return mission;
    }





    /**
     * 代理请求给的的url地址获取一个dom
     * @param url
     * @return
     * @throws IOException
     */
    public Document getDocument (String url) throws IOException {

        URL parseUrl = new URL(url);

        return Jsoup.parse(parseUrl,10000);

    }

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public Document getConnect (String url) throws  IOException {

        url = "view-source:"+url;
        url = url.replace("http://","");
        return Jsoup.connect(url).get();
    }

    /**
     * http://android.myapp.com/myapp/detail.htm?apkName=com.tencent.mobileqq
     * '='后面的就是包名,而去各个市场都差不多
     * @param url
     * @return
     */
    public String parseUrlToGetPkgName (String url,Integer site) throws IOException {

        ReAppMarket market = reAppMarketDAO.selectByPrimaryKey(site);
        String marketName = market.getMarketName();

        if (marketName.contains("应用宝")) {    //应用宝
            String[] urls = url.split("=");
            if (urls.length == 2) {
                return urls[1];
            }

        }else if (marketName.contains("小米")) {//小米
            String[] urls = url.split("=");
            if (urls.length == 2) {
                return urls[1];
            }

        }else if(marketName.contains("360手机助手")){ //360
            String [] paras = url.split("&");
            String containPack = paras[0];

            String[] pks = containPack.split("=");

            return pks[1];
        }else if (marketName.contains("OPPO")) {   //OPPO
            return "";
        }else if (marketName.contains("魅族")) { //魅族
            String[] urls = url.split("=");
            if (urls.length == 2) {
                return urls[1];
            }
        }else if (marketName.contains("豌豆荚")) { //豌豆荚
            String [] urls = url.split("apps/");

            return urls[1];
        }else if (marketName.contains("华为应用市场")) { //华为
            String[] urls = url.split("app/");
            return urls[1];
        }else if (marketName.contains("百度手机助手")) { //百度手机助手
            Document doc = getDocument(url);
            if (doc != null){

                Elements packs = doc.getElementsByAttribute("data_package");
                if (packs != null && packs.size()>0){
                    return packs.get(0).attr("data_package");
                }
            }

        }else if (marketName.contains("应用汇")) { //应用汇
            String[] urls = url.split("app/");
            return urls[1];

        }else if (marketName.contains("PP")) { //PP助手

            Document doc = getDocument(url);
            Elements packs = doc.getElementsByClass("detail-side");
            if (packs != null && packs.size()>0){

                Element e = packs.get(0);
                String packageName = e.attr("pack_id");

                return packageName;
            }

        }else if (marketName.contains("搜狗手机助手")) { //搜狗手机助手

            return "";
        }else if (marketName.contains("安卓市场")){

            String[] paras = url.split("\\?");
            String contatinPack = paras[0];

            String [] pks = contatinPack.split("/");

            return pks[pks.length-1];
        }else if (marketName.contains("魅族")){
            String[] urls = url.split("=");
            if (urls.length == 2) {
                return urls[1];
            }
        }else if (marketName.contains("安智")){
            Document doc = getDocument(url);
            Elements packages = doc.getElementsByClass("detail_icon");
            String packageName = packages.get(0).getElementsByTag("ul").get(0).getElementsByTag("a").get(1).attr("href");

            return packageName.split("package=")[1];

        }

        return null;
    }

}
