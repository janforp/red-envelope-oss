package com.envolope.oss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Created by craig on 16/2/15.
 */
public class IOUtil {
    public static String getFileMd5(File file) throws RuntimeException {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16).toLowerCase();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 支持删除文件、文件夹
     *
     * @param filePath
     * @return
     */
    public static boolean deletFiles(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                // 文件夹
                File[] subFiles = file.listFiles();
                for (File f : subFiles) {
                    deletFiles(f.getAbsolutePath());
                }
                flag = file.delete();
            } else {
                flag = file.delete();
            }
        }
        return flag;
    }
}
