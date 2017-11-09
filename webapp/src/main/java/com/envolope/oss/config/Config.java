package com.envolope.oss.config;

import com.envolope.oss.consts.BaseConsts;
import com.envolope.oss.util.PropertiesLoader;
import com.envolope.oss.util.support.WujieStringConverter;
import com.wujie.common.beanutil.converters.DateStringLongConverter;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by wuqiang on 15-8-4.
 *
 * @author wuqiang
 */
public final class Config {

    private static Logger log = LoggerFactory.getLogger(Config.class);
    private static Properties properties;
    private static Set<String> tmpSubDirectoryNames = new HashSet<>();

    // 全局默认字符集编码
    public static final String CHARSET = "utf-8";
    // 开发模式，仅本地开发环境时，设置为true
    private static boolean devModel = false;
    // 调试模式，则生产环境也可以使用，有可能可以输出更多信息
    private static boolean debug = false;

    /**
     * #当前服务运行的服务器所在环境位置:如果是非阿里云中,就填空白
     * #否则如果当前运行在阿里云杭州的ECS中,就填写cn-hangzhou
     * #其他阿里云区域填写对应的区域region:cn-beijing / cn-hongkong / cn-shanghai / cn-shenzhen / cn-qingdao / us-west-1(美国硅谷) / ap-southeast-1(亚太(新加坡))
     */
    private static String serverRuntimeRegion = "";
    // 临时文件目录，一定以“分隔符”结尾
    private static String tmpDirectory = "/tmp/aigou_oss/";
    // 重新初始化微信公众号配置缓存Map
    private static String rebuildWxAppCfgCacheUrl;
    // 红包域名地址
    private static String redBaseUrl;

    /**
     * 触发执行static{}
     */
    public static void init() {
        // 什么以不执行, 只是触发执行static{}
    }

    /**
     * initialization
     */
    static {
        initBeanUtils();
        try {

            Properties profileProperties = PropertiesLoader.init("application-profile.properties");
            String profileName = profileProperties.getProperty("profile_name");
            String profileNameProperties = profileName + ".properties";

            properties = PropertiesLoader.init(profileNameProperties);
            Field[] fields = Config.class.getDeclaredFields();
            if (fields != null) {
                for (Field fd : fields) {
                    String fieldName = fd.getName();
                    if (Modifier.isFinal(fd.getModifiers()) || "log".equals(fieldName)
                            || "properties".equals(fieldName)) {
                        continue;
                    }
                    Class<?> fieldType = fd.getType();
                    if (isSimpleDataTypeOrSimpleArray(fieldType)) {
                        Object realValue = null;
                        // 是简单数据类型
                        String value = null;
                        try {
                            value = properties.getProperty(fieldName);
                        } catch (Exception e) {
                        }
                        if (value != null) {
                            Method strSetter = null;
                            try {
                                strSetter = Config.class.getDeclaredMethod("set" + initCap(fieldName), String.class);
                                strSetter.setAccessible(true);
                            } catch (Exception e) {
                            }
                            if (strSetter != null) {
                                // 有字符串类型的setter方法
                                try {
                                    strSetter.invoke(Config.class, value);
                                } catch (Exception e) {
                                    throw new RuntimeException(
                                            "failed to parse " + fieldName + " for " + Config.class
                                                    .getName() + " from " + profileNameProperties, e);
                                }
                            } else {
                                // 没有setter
                                try {
                                    if (String.class.equals(fieldType)) {
                                        realValue = value;
                                    } else if (Boolean.class.equals(fieldType)
                                            || boolean.class.equals(fieldType)) {
                                        realValue = Boolean.parseBoolean(value);
                                    } else if (Byte.class.equals(fieldType)
                                            || byte.class.equals(fieldType)) {
                                        realValue = Byte.parseByte(value);
                                    } else if (Short.class.equals(fieldType)
                                            || short.class.equals(fieldType)) {
                                        realValue = Short.parseShort(value);
                                    } else if (Integer.class.equals(fieldType)
                                            || int.class.equals(fieldType)) {
                                        realValue = Integer.parseInt(value);
                                    } else if (Long.class.equals(fieldType)
                                            || long.class.equals(fieldType)) {
                                        realValue = Long.parseLong(value);
                                    } else if (Float.class.equals(fieldType)
                                            || float.class.equals(fieldType)) {
                                        realValue = Float.parseFloat(value);
                                    } else if (Double.class.equals(fieldType)
                                            || double.class.equals(fieldType)) {
                                        realValue = Double.parseDouble(value);
                                    }
                                } catch (Exception e) {
                                    throw new RuntimeException(
                                            "failed to parse " + fieldName + " for " + Config.class
                                                    .getName() + " from " + profileNameProperties, e);
                                }
                                if (realValue != null) {
                                    fd.setAccessible(true);
                                    try {
                                        fd.set(Config.class, realValue);
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }

                        }
                    } else {
                        // Bean对象
                        fd.setAccessible(true);
                        Object subBean = fd.get(Config.class);
                        if (subBean == null) {
                            subBean = fieldType.newInstance();
                            fd.set(Config.class, subBean);
                        }
                        Field[] subFields = fieldType.getDeclaredFields();
                        if (subFields != null) {
                            for (Field sfd : subFields) {
                                String subFieldName = sfd.getName();
                                Class<?> subFieldType = sfd.getType();
                                Object realValue = null;
                                String key = fieldName + "." + subFieldName;
                                String value = null;
                                try {
                                    value = properties.getProperty(key);
                                } catch (Exception e) {
                                }
                                if (value != null) {
                                    Method strSetter = null;
                                    try {
                                        strSetter = fieldType.getDeclaredMethod("set" + initCap(subFieldName), String.class);
                                        strSetter.setAccessible(true);
                                    } catch (Exception e) {
                                    }
                                    if (strSetter != null) {
                                        // 有字符串类型的setter方法
                                        try {
                                            strSetter.invoke(subBean, value);
                                        } catch (Exception e) {
                                            throw new RuntimeException(
                                                    "failed to parse " + key + " for Config." + fieldName + " from " + profileNameProperties, e);
                                        }
                                    } else {
                                        // 没有setter
                                        try {
                                            if (String.class.equals(subFieldType)) {
                                                realValue = value;
                                            } else if (Boolean.class.equals(subFieldType)
                                                    || boolean.class.equals(subFieldType)) {
                                                realValue = Boolean.parseBoolean(value);
                                            } else if (Byte.class.equals(subFieldType)
                                                    || byte.class.equals(subFieldType)) {
                                                realValue = Byte.parseByte(value);
                                            } else if (Short.class.equals(subFieldType)
                                                    || short.class.equals(subFieldType)) {
                                                realValue = Short.parseShort(value);
                                            } else if (Integer.class.equals(subFieldType)
                                                    || int.class.equals(subFieldType)) {
                                                realValue = Integer.parseInt(value);
                                            } else if (Long.class.equals(subFieldType)
                                                    || long.class.equals(subFieldType)) {
                                                realValue = Long.parseLong(value);
                                            } else if (Float.class.equals(subFieldType)
                                                    || float.class.equals(subFieldType)) {
                                                realValue = Float.parseFloat(value);
                                            } else if (Double.class.equals(subFieldType)
                                                    || double.class.equals(subFieldType)) {
                                                realValue = Double.parseDouble(value);
                                            }
                                        } catch (Exception e) {
                                            throw new RuntimeException(
                                                    "failed to parse " + key + " for Config." + fieldName + " from " + profileNameProperties, e);
                                        }
                                        if (realValue != null) {
                                            sfd.setAccessible(true);
                                            try {
                                                sfd.set(subBean, realValue);
                                            } catch (IllegalAccessException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception mre) {
            throw new RuntimeException(mre);
        }
        initTmpDirectory();
    }

    /**
     * invoke by static{}
     */
    private static void initBeanUtils() {
        {
            ConvertUtils.register(new WujieStringConverter(BaseConsts.FMT_yyyy_MM_dd, BaseConsts.FMT_yyyy_MM_dd_HH_mm_ss), String.class);
            //注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空
            ConvertUtils.register(new DateStringLongConverter(BaseConsts.FMT_yyyy_MM_dd, BaseConsts.FMT_yyyy_MM_dd_HH_mm_ss), java.util.Date.class);
            //数字格式的转化NULL依然是NULL，不会默认为0
            ConvertUtils.register(new LongConverter(null), Long.class);
            ConvertUtils.register(new ShortConverter(null), Short.class);
            ConvertUtils.register(new IntegerConverter(null), Integer.class);
            ConvertUtils.register(new DoubleConverter(null), Double.class);
            ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        }
        try {
//            BeanUtils.putBeanCopy(new BeanCopy_WyUser_UserLoginResultVo());
        } catch (IllegalArgumentException e) {
            System.err.println("!!! [ERROR] !!! - " + e);
            System.exit(0);
        }
    }

    /**
     * 初始化临时目录
     */
    private static void initTmpDirectory() {
        if (!File.separator.equals(tmpDirectory)) {
            // 删除临时目录(如果是根目录,则不删除)
            deletFiles(tmpDirectory);
        }
        File tmpDirectoryFile = new File(tmpDirectory);
        if (!tmpDirectoryFile.exists()) {
            // 临时目录不存在, 则创建
            tmpDirectoryFile.mkdirs();
        }
        for (String subDir : tmpSubDirectoryNames) {
            File subDirFile = new File(tmpDirectory + subDir);
            if (subDirFile.exists()) {
                // 这个子目录还存在, 则单独删除这个子目录
                deletFiles(subDirFile.getAbsolutePath());
            }
            subDirFile.mkdirs();
        }
    }

    /**
     * 是否是简单数据类型(不能是数组)（Number/Boolean/String/byte/short/int/long/float/double/boolean）
     *
     * @return
     */
    public static boolean isSimpleDataTypeOrSimpleArray(Class fieldType) {
        boolean flag = false;
        if (fieldType == null) {
            return flag;
        }
        if (String.class.equals(fieldType)
                || String[].class.equals(fieldType)
                || Boolean.class.equals(fieldType)
                || Boolean[].class.equals(fieldType)
                || boolean.class.equals(fieldType)
                || boolean[].class.equals(fieldType)
                || Byte.class.equals(fieldType)
                || Byte[].class.equals(fieldType)
                || byte.class.equals(fieldType)
                || byte[].class.equals(fieldType)
                || Short.class.equals(fieldType)
                || Short[].class.equals(fieldType)
                || short.class.equals(fieldType)
                || short[].class.equals(fieldType)
                || Integer.class.equals(fieldType)
                || Integer[].class.equals(fieldType)
                || int.class.equals(fieldType)
                || int[].class.equals(fieldType)
                || Long.class.equals(fieldType)
                || Long[].class.equals(fieldType)
                || long.class.equals(fieldType)
                || long[].class.equals(fieldType)
                || Float.class.equals(fieldType)
                || Float[].class.equals(fieldType)
                || float.class.equals(fieldType)
                || float[].class.equals(fieldType)
                || Double.class.equals(fieldType)
                || Double[].class.equals(fieldType)
                || double.class.equals(fieldType)
                || double[].class.equals(fieldType)) {
            // 是基本数据类型
            flag = true;
        }
        return flag;
    }

    /**
     * 支持删除文件、文件夹
     *
     * @param filePath
     * @return
     */
    private static boolean deletFiles(String filePath) {
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

    /**
     * 把第一个字母转换为大写
     *
     * @param src
     * @return
     */
    private static String initCap(String src) {
        if (src == null) {
            return null;
        }
        if (src.length() > 1) {
            return src.substring(0, 1).toUpperCase() + src.substring(1);
        } else {
            return src.toUpperCase();
        }
    }

    public static boolean isDebug() {
        return debug;
    }

    public static boolean isDevModel() {
        return devModel;
    }

    public static String getTmpDirectory() {
        return tmpDirectory;
    }

    public static String getServerRuntimeRegion() {
        return serverRuntimeRegion;
    }

    public static String getConfigValue(String key) {
        try {
            return properties.getProperty(key);
        } catch (Exception e) {
            log.error("getConfigValue method Exception ---->" + e);
            return null;
        }
    }

    public static String getRebuildWxAppCfgCacheUrl() {
        return rebuildWxAppCfgCacheUrl;
    }

    public static String getRedBaseUrl() {
        return redBaseUrl;
    }

}
