package com.zcf.music.high.service;

import com.zcf.music.high.domain.Song;
import com.zcf.music.high.web.utils.HttpRequestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/7 14:17
 * @Desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SongManagerTest {

    @Autowired
    private SongManager songManager;

    @Test
    public void test() {
        List<String> hashs = HttpRequestUtil.searchSong("淘汰");
        List<Song> songs = HttpRequestUtil.getSongByHash(hashs);
        this.songManager.save(songs);
    }
}
