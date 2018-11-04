package com.zcf.music.high.service.impl;

import com.zcf.music.high.domain.Song;
import com.zcf.music.high.repository.SongRepository;
import com.zcf.music.high.service.SongManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/5 20:20
 * @Desc
 */
@Service
public class SongManagerImpl implements SongManager {

    @Autowired
    private SongRepository songRepository;

    @Override
    public Song save(Song song) {
        return this.songRepository.save(song);
    }

    @Override
    public List<Song> save(List<Song> songs) {
        return this.songRepository.save(songs);
    }
}
