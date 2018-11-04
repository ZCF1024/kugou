package com.zcf.music.high.service.impl;

import com.zcf.music.high.domain.Singer;
import com.zcf.music.high.domain.SingerClass;
import com.zcf.music.high.repository.SingerClassRepository;
import com.zcf.music.high.repository.SingerRepository;
import com.zcf.music.high.service.SingerClassManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/5 0005 下午 20:21
 * @Desc
 */
@Service
public class SingerClassManagerImpl implements SingerClassManager {

    @Autowired
    private SingerClassRepository singerClassRepository;

    @Override
    public SingerClass save(SingerClass singerClass) {
        return this.singerClassRepository.save(singerClass);
    }

    @Override
    public List<SingerClass> save(List<SingerClass> singerClasses) {
        return this.singerClassRepository.save(singerClasses);
    }

    @Override
    public List<Integer> getAllClassId() {
        return this.singerClassRepository.getAllClassId();
    }
}
