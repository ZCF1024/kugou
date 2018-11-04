package com.zcf.music.high.service;

import com.zcf.music.high.domain.MV;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/7 11:22
 * @Desc
 */
public interface MVManager {

    MV save(MV mv);

    List<MV> save(List<MV> mvs);
}
