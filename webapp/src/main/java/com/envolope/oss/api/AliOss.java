package com.envolope.oss.api;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;

import com.envolope.oss.config.Config;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public class AliOss {

    private static String bucketName;
    private static String baseURL;

    public final static OSSClient CLIENT;

    static {
        try {
            final String ACCESS_ID = Config.getConfigValue("aliyun.oss.accessKeyId");
            final String ACCESS_KEY = Config.getConfigValue("aliyun.oss.accessKeySecret");
            final String ENDPOINT = Config.getConfigValue("aliyun.oss.endpoint");
            bucketName = Config.getConfigValue("aliyun.oss.bucketName");
            baseURL = Config.getConfigValue("aliyun.oss.baseURL");
            CLIENT = new OSSClient(ENDPOINT, ACCESS_ID, ACCESS_KEY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // 创建Bucket.
    public static void ensureBucket(String bucketName)
            throws OSSException, ClientException {
        try {
            // 创建bucket
            CLIENT.createBucket(bucketName);
        } catch (ServiceException e) {
            if (!OSSErrorCode.BUCKET_ALREADY_EXISTS.equals(e.getErrorCode())) {
                // 如果Bucket已经存在，则忽略
                throw e;
            }
        }
    }

    // 删除一个Bucket和其中的Objects
    public static void deleteBucket(String bucketName)
            throws OSSException, ClientException {

        ObjectListing ObjectListing = CLIENT.listObjects(bucketName);
        List<OSSObjectSummary> listDeletes = ObjectListing
                .getObjectSummaries();
        for (int i = 0; i < listDeletes.size(); i++) {
            String objectName = listDeletes.get(i).getKey();
            // 如果不为空，先删除bucket下的文件
            CLIENT.deleteObject(bucketName, objectName);
        }
        CLIENT.deleteBucket(bucketName);
    }

    // 把Bucket设置为所有人可读
    public static void setBucketPublicReadable(String bucketName)
            throws OSSException, ClientException {
        //创建bucket
        CLIENT.createBucket(bucketName);

        //设置bucket的访问权限，public-read-write权限
        CLIENT.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
    }

    // 上传文件
    public static String uploadFile(String bucketName, String key, String filename, String type)
            throws OSSException, ClientException, FileNotFoundException {
        File file = new File(filename);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        // 可以在metadata中标记文件类型
        objectMeta.setContentType("image/" + type);
        InputStream input = new FileInputStream(file);
        CLIENT.putObject(bucketName, key, input, objectMeta);
        return baseURL + key;
    }

    // 上传文件
    public static String uploadFile(String key, String filename, String type)
            throws OSSException, ClientException, FileNotFoundException{
        return uploadFile(bucketName, key, filename, type);
    }

    // 上传Multipart文件
    public static String uploadMultipartFile(String key, MultipartFile multipartFile, String type)
            throws OSSException, ClientException, IOException {
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(multipartFile.getSize());
        objectMeta.setContentType("image/" + type);
        CLIENT.putObject(bucketName, key, multipartFile.getInputStream(), objectMeta);
        return baseURL + key;
    }

    //上传网络流
    public static String uploadByUrl(String imgUrl,String key,String type){
        try {
            MultipartFile multipartFile = new MockMultipartFile(key, new URL(imgUrl).openStream());
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentLength(multipartFile.getSize());
            // 可以在metadata中标记文件类型
            objectMeta.setContentType("image/"+type);
            CLIENT.putObject(bucketName,key,multipartFile.getInputStream(),objectMeta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseURL + key;
    }

    // 上传apk文件
    public static String uploadMultipartApk(String key, MultipartFile multipartFile)
            throws OSSException, ClientException, IOException {
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(multipartFile.getSize());
        objectMeta.setContentType("application/octet-stream");
        CLIENT.putObject(bucketName, key, multipartFile.getInputStream(), objectMeta);
        return baseURL + key;
    }

    // 下载文件
    public static void downloadFile(String bucketName, String key, String filename)
            throws OSSException, ClientException {
        CLIENT.getObject(new GetObjectRequest(bucketName, key),
                new File(filename));
    }

    // 下载文件
    public static void downloadFile(String key, String filename)
            throws OSSException, ClientException {
        downloadFile(bucketName, key, filename);
    }

    public static boolean deleteFileByURL(String url){
        try{
            CLIENT.deleteObject(bucketName, url.substring(baseURL.length()));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static boolean deleteFileByKey(String key){
        try{
            CLIENT.deleteObject(bucketName, key);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        String uploadFilePath = "d:/tmp/550133346.jpg";
        String downloadFilePath = "d:/tmp/down.jpg";
        String key = "server"+UUID.randomUUID().toString();
        try {
            String url = AliOss.uploadFile(key,uploadFilePath,"jpg");
            try {
                System.out.println(url+"上传成功！5秒后下载文件保存路径为："+downloadFilePath);
                Thread.sleep(5*1000);
                AliOss.downloadFile(key, downloadFilePath);
                System.out.println("下载完成！");
            } catch (Exception e) {
                System.out.println("下载失败！");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("上传失败");
            e.printStackTrace();
        }
    }

}
