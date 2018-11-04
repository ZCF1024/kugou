package com.zcf.music.high.service.impl;

import com.zcf.music.high.domain.Singer;
import com.zcf.music.high.repository.SingerRepository;
import com.zcf.music.high.service.SingerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/5 0005 下午 16:57
 * @Desc
 */
@Service
public class SingerManagerImpl implements SingerManager {

    @Autowired
    private SingerRepository singerRepository;

    public Singer save(Singer singer) {
        return this.singerRepository.save(singer);
    }

    public List<Singer> save(List<Singer> singers) {
        return this.singerRepository.save(singers);
    }

    public List<Long> getAllSingerId() {
        return this.singerRepository.getAllSingerId();
    }

    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }
}
