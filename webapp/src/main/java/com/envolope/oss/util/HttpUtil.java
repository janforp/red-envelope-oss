package com.envolope.oss.util;

import org.craigq.common.logger.ILogger;
import org.craigq.common.logger.LogMgr;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuqiang on 15-9-29.
 *
 * @author wuqiang
 */
public class HttpUtil {
    private static int connectTimeout = 10000;//10秒
    private static int readTimeout = 15000;//15秒

    /**
     * 采用get方式下载文件
     *
     * @param url
     * @param filePath
     * @return 下载成功或失败
     */
    public static boolean download(String url, String filePath) {
        boolean flag = false;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File targetFile = new File(filePath);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            inputStream = conn.getInputStream();
            outputStream = new FileOutputStream(targetFile);
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            flag = true;
        } catch (Exception e) {
            flag = false;
            ILogger logger = LogMgr.getLogger();
            if (logger.isWarnEnabled()) {
                logger.warn("HttpUtil.download: " + e);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return flag;
    }

    /**
     * 采用get方式下载文件
     *
     * @param url
     * @param filePath
     * @return contentType（如果返回null表明下载失败，如果返回：""，表明下载成功但服务器没有返回Content-Type）
     */
    public static String downloadAndRetrunContentType(String url, String filePath) {
        String contentType = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            File targetFile = new File(filePath);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            inputStream = conn.getInputStream();
            outputStream = new FileOutputStream(targetFile);
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            contentType = conn.getContentType();
            if (contentType == null) {
                contentType = "";
            }
        } catch (Exception e) {
            contentType = null;
            ILogger logger = LogMgr.getLogger();
            if (logger.isWarnEnabled()) {
                logger.warn("HttpUtil.download: " + e);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                }
            }
        }
        return contentType;
    }

    /**
     * 采用get方式下载文件
     *
     * @param url
     * @param outputStream 目标输出流（需要外部自行关闭）
     * @return contentType（如果返回null表明下载失败，如果返回：""，表明下载成功但服务器没有返回Content-Type）
     */
    public static String downloadToOutputStreamAndRetrunContentType(String url, OutputStream outputStream) {
        String contentType = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try {
            URL urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.connect();
            inputStream = conn.getInputStream();
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(data)) != -1) {
                outputStream.write(data, 0, len);
            }
            contentType = conn.getContentType();
            if (contentType == null) {
                contentType = "";
            }
        } catch (Exception e) {
            contentType = null;
            ILogger logger = LogMgr.getLogger();
            if (logger.isWarnEnabled()) {
                logger.warn("HttpUtil.download: " + e);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        return contentType;
    }

    /**
     * 抓取网页内容
     *
     * @param sUrl
     * @return String
     */
    public static String crawlHtml (String sUrl) {

        StringBuffer html = new StringBuffer();//html为爬到的网页内容
        String inputLine = null;

        try {

            URL url = new URL(sUrl);
            URLConnection yc = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            while ( ( inputLine = in.readLine()) != null ) {
                html.append(inputLine);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return html.toString();

    }

    /**
     * 抓取网页内容
     *
     * @param sUrl
     * @return String
     */
    public static Document crawlHtmlDoc (String sUrl) {

        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("searcharg", "java");
        params.put("searchtype", "t");
        params.put("SORT", "DZ");
        params.put("extended", "0");

        Document doc = null;             //使用get方法，对应还有post()
        try {
            doc = Jsoup.connect(sUrl)
                    .userAgent("Mozilla")  //声明了浏览器用于 HTTP 请求的用户代理头的值
                    .timeout(30*1000)   //超时时间
                    .data(params)       //请求参数
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;

    }

}
