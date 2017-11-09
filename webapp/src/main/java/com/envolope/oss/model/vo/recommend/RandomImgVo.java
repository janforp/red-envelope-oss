package com.envolope.oss.model.vo.recommend;

import java.io.Serializable;

/**
 * Created by Jan on 16/11/1.
 */
public class RandomImgVo  implements Serializable{


    private String imgUrl;
    private String randomString;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRandomString() {
        return randomString;
    }

    public void setRandomString(String randomString) {
        this.randomString = randomString;
    }
}
