package com.zcf.music.high.service.impl;

import com.zcf.music.high.domain.Special;
import com.zcf.music.high.repository.SpecialRepository;
import com.zcf.music.high.service.SpecialManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/5 20:26
 * @Desc
 */
@Service
public class SpecialManagerImpl implements SpecialManager {

    @Autowired
    private SpecialRepository specialRepository;

    @Override
    public Special save(Special special) {
        return this.specialRepository.save(special);
    }

    @Override
    public List<Special> save(List<Special> specials) {
        return this.specialRepository.save(specials);
    }

    public List<Long> getAllSpecialId() {
        return this.specialRepository.getAllSpecialId();
    }
}
