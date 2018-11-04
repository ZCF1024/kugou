package com.zcf.music.high.web.controller;

import com.zcf.music.high.domain.Song;
import com.zcf.music.high.domain.Special;
import com.zcf.music.high.service.RankManager;
import com.zcf.music.high.service.SingerManager;
import com.zcf.music.high.service.SongManager;
import com.zcf.music.high.service.SpecialManager;
import com.zcf.music.high.web.utils.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zcf
 * @Create 2018/8/6 8:59
 * @Desc
 */
@RestController
public class SongController {

    @Autowired
    private SongManager songManager;

    @Autowired
    private RankManager rankManager;

    @Autowired
    private SingerManager singerManager;

    @Autowired
    private SpecialManager specialManager;

    @GetMapping("rank")
    public String getLastSong() {
        List<Integer> rankIds = this.rankManager.getAllRankId();
        for (Integer rankId : rankIds) {
            List<String> hashs = HttpRequestUtil.getSongHashOfRankList(rankId);
            List<Song> songs = HttpRequestUtil.getSongByHash(hashs);
            this.songManager.save(songs);
        }
        return "successful";
    }

    @GetMapping("singer")
    public String getSpecialSong() {
        List<Long> singers = this.singerManager.getAllSingerId();
        for (Long singer : singers) {
            List<String> hashs = HttpRequestUtil.getSingerSongHashList(singer);
            List<Song> songs = HttpRequestUtil.getSongByHash(hashs);
            this.songManager.save(songs);
        }
        return "successful";
    }

}
