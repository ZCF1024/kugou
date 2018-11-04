package com.zcf.music.high.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @Author zcf
 * @Create 2018/8/4  11:30
 * @Desc 音乐排行版信息
 */
@Entity
public class Rank {

    @Id
    private Integer rankId;

    private String rankName;

    @Column(name = "update_frequency")
    private String updateFrequency;

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(String updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return Objects.equals(rankId, rank.rankId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankId);
    }
}