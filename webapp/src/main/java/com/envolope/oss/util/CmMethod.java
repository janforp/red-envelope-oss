package com.envolope.oss.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doer
 */
public final class CmMethod {

    private CmMethod() {
    }


    public static List<String> fileTypes = new ArrayList<String>();
    static {
        fileTypes.add("jpg");
        fileTypes.add("jpeg");
        fileTypes.add("bmp");
        fileTypes.add("gif");
        fileTypes.add("png");
    }
    public static boolean isImageTypevalidate(String ext){
        return fileTypes.contains(ext);
    }

    public static boolean isApkTypeValidate(String ext){
        return "apk".equals(ext);
    }

    /**
     * 把字符串中的字符串进行格式化 <br> 比如：<br> src={0}格式错误; <br> formatArg(src,"密码"));<br> 返回：密码错误
     */
    public static String formatArg(String src, Object arg) {
        if (arg == null || src == null || src.length() == 0) {
            return src;
        }
        return src.replace("{0}", String.valueOf(arg));
    }

    /**
     * 把字符串中的字符串进行格式化 <br> 比如：<br> src={0}格式错误; <br> formatArg(src,new String[]{"密码"}));<br>
     * 返回：密码错误
     */
    public static <T> String formatArg(String src, T... args) {
        if (args == null || args.length == 0 || src == null || src.length() == 0) {
            return src;
        }
        int len = args.length;
        for (int i = 0; i < len; i++) {
            src = src.replace("{" + i + "}", String.valueOf(args[i]));
        }
        return src;
    }

}
