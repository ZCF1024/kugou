package com.zcf.music.high.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @Author zcf
 * @Create 2018/8/3 23:36
 * @Desc 歌手信息
 */
@Entity
public class Singer {

    private String singerName;

    @Id
    private Long singerId;

    private String imgUrl;

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
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
        Singer singer = (Singer) o;
        return Objects.equals(singerId, singer.singerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(singerId);
    }
}