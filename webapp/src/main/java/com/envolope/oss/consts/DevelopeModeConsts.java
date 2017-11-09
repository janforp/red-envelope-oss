package com.envolope.oss.consts;

/**
 * Created by Jan on 16/8/4.
 *
 * 开发者模式
 */
public class DevelopeModeConsts {

    public static String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String menuUrl = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";

    public static String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
}
