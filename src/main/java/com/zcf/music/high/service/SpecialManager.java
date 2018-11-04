package com.zcf.music.high.service;

import com.zcf.music.high.domain.Special;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/5 20:25
 * @Desc
 */
public interface SpecialManager {

    Special save(Special special);

    List<Special> save(List<Special> specials);

    List<Long> getAllSpecialId();
}
