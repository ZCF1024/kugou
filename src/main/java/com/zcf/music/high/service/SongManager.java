package com.zcf.music.high.service;

import com.zcf.music.high.domain.Song;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/5 0005 下午 20:20
 * @Desc
 */
public interface SongManager {

    Song save(Song song);

    List<Song> save(List<Song> songs);
}
