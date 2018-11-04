package com.zcf.music.high.service.impl;

import com.zcf.music.high.domain.MV;
import com.zcf.music.high.repository.MVRepository;
import com.zcf.music.high.service.MVManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/7 11:23
 * @Desc
 */
@Service
public class MVManagerImpl implements MVManager {

    @Autowired
    private MVRepository mvRepository;

    @Override
    public MV save(MV mv) {
        return this.mvRepository.save(mv);
    }

    @Override
    public List<MV> save(List<MV> mvs) {
        return this.mvRepository.save(mvs);
    }
}
