package com.zcf.music.high.service.impl;

import com.zcf.music.high.domain.Rank;
import com.zcf.music.high.repository.RankRepository;
import com.zcf.music.high.service.RankManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/4 23:16
 * @Desc
 */
@Service
public class RankManagerImpl implements RankManager {

    @Autowired
    private RankRepository rankRepository;

    @Override
    public Rank save(Rank rank) {
        return this.save(rank);
    }

    @Override
    public List<Rank> save(List<Rank> ranks) {
        return this.rankRepository.save(ranks);
    }

    @Override
    public List<Integer> getAllRankId() {
        return this.rankRepository.getAllRankId();
    }
}
