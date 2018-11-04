package com.zcf.music.high.service;

import com.zcf.music.high.domain.Rank;

import java.util.List;

/**
 * @Author Administrator
 * @Create 2018/8/4 0004 下午 23:16
 * @Desc
 */
public interface RankManager {
    Rank save(Rank rank);

    List<Rank> save(List<Rank> ranks);

    List<Integer> getAllRankId();

}
