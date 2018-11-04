package com.zcf.music.high.service;

import com.zcf.music.high.domain.Rank;
import com.zcf.music.high.web.utils.APIUtils;
import com.zcf.music.high.web.utils.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/5 20:32
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RankMangerTest {

    @Autowired
    private RankManager rankManager;

    @Test
    public void testSave() {
        List<Rank> ranks = HttpRequestUtil.getRankList();
        System.out.println("size: " + ranks.size());
        this.rankManager.save(ranks);
    }

    @Test
    public void testGetAllRankId() {
        List<Integer> ids = this.rankManager.getAllRankId();
        System.out.println(ids.size());
    }

}
