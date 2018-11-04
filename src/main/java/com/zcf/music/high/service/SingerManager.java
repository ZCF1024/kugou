package com.zcf.music.high.service;

import com.zcf.music.high.domain.Singer;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/5 0005 下午 20:21
 * @Desc
 */
public interface SingerManager {

    Singer save(Singer singer);

    List<Singer> save(List<Singer> singers);

    List<Long> getAllSingerId();
}
