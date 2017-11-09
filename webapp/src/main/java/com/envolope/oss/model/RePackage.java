package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-29
 */
public class RePackage implements java.io.Serializable {

    // Fields

    // ID
    private Long id;
    // 平台 0-iOS, 1-Android
    private Integer platform;
    // 包名
    private String packageName;

    // Constructors

    /**
     * default constructor
     */
    public RePackage() {
    }

    /**
     * full constructor
     */
    public RePackage(Integer platform, String packageName) {
        this.platform = platform;
        this.packageName = packageName;
    }

    // Property accessors

    /**
     * ID
     */
    public Long getId() {
        return this.id;
    }

    /**
     * ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 平台 0-iOS, 1-Android
     */
    public Integer getPlatform() {
        return this.platform;
    }

    /**
     * 平台 0-iOS, 1-Android
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * 包名
     */
    public String getPackageName() {
        return this.packageName;
    }

    /**
     * 包名
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}