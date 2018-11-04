package com.zcf.music.high.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @Author zcf
 * @Create 2018/8/5  1:17
 * @Desc 音乐歌单
 */
@Entity
public class Special {

    private String specialName;

    private Long collectCount;

    @Id
    private Long specialId;

    private Long playCount;

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public Long getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Long specialId) {
        this.specialId = specialId;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Special special = (Special) o;
        return Objects.equals(specialId, special.specialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialId);
    }
}
