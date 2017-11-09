package com.envolope.oss.model;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-11-09
 */
public class ReRecommendMissionRequire implements java.io.Serializable {

    // Fields

    // id，自增长
    private Long requireId;
    // 要求名字
    private String requireName;

    // Constructors

    /**
     * default constructor
     */
    public ReRecommendMissionRequire() {
    }

    /**
     * full constructor
     */
    public ReRecommendMissionRequire(String requireName) {
        this.requireName = requireName;
    }

    // Property accessors

    /**
     * id，自增长
     */
    public Long getRequireId() {
        return this.requireId;
    }

    /**
     * id，自增长
     */
    public void setRequireId(Long requireId) {
        this.requireId = requireId;
    }

    /**
     * 要求名字
     */
    public String getRequireName() {
        return this.requireName;
    }

    /**
     * 要求名字
     */
    public void setRequireName(String requireName) {
        this.requireName = requireName;
    }

}