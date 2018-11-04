package com.zcf.music.high.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

/**
 * @Author zcf
 * @Create 2018/8/3 23:36
 * @Desc 音乐信息
 */
@Entity
public class Song {

    @Id
    private String hash;

    @Column(name = "file_size")
    private Integer fileSize;

    @Column(name = "song_name")
    private String songName;

    private Integer duration;

    @Column(name = "ext_name")
    private String extName;

    @Column(name = "album_id")
    private Long albumId;

    private Date time;

    private String url;

    @Column(name = "mv_hash")
    private String mvHash;

    @Column(name = "singer_id")
    private Long singerId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "bit_rate")
    private Integer bitRate;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMvHash() {
        return mvHash;
    }

    public void setMvHash(String mvhash) {
        this.mvHash = mvHash;
    }

    public Long getSingerId() {
        return singerId;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getBitRate() {
        return bitRate;
    }

    public void setBitRate(Integer bitRate) {
        this.bitRate = bitRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(hash, song.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash);
    }
}