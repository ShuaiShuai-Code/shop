package com.fenboshi.fboshi.bean;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;


//todo 没有用kotlin写bean，是因为数据库映射不出UserBeanDao
@Entity
public class HomeBanner   {
     private String desc;//Token
     private String id;
     private String imagePath;
     private int isVisible;
     private int type;
     private String title;
     private String url;

    @Generated(hash = 14440495)
    public HomeBanner(String desc, String id, String imagePath, int isVisible,
            int type, String title, String url) {
        this.desc = desc;
        this.id = id;
        this.imagePath = imagePath;
        this.isVisible = isVisible;
        this.type = type;
        this.title = title;
        this.url = url;
    }

    @Generated(hash = 2105823777)
    public HomeBanner() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}