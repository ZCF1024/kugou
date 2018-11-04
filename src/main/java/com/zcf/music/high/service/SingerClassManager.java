package com.zcf.music.high.service;

import com.zcf.music.high.domain.SingerClass;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/5 0005 下午 20:21
 * @Desc
 */
public interface SingerClassManager {

    SingerClass save(SingerClass singerClass);

    List<SingerClass> save(List<SingerClass> singerClasses);

    List<Integer> getAllClassId();

}
