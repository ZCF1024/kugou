package com.zcf.music.high.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @Author zcf
 * @Create 2018/8/5 11:08
 * @Desc
 */
@Entity
public class SingerClass {

    @Id
    private Integer classId;

    private String className;

    private String imgUrl;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerClass that = (SingerClass) o;
        return Objects.equals(classId, that.classId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }
}