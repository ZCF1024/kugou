package com.zcf.music.high.utils;

import com.zcf.music.high.domain.*;
import com.zcf.music.high.web.utils.HttpRequestUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author zcf
 * @Create 23:59
 * @Desc
 */
public class HttpUtilTest {

    public static void main(String[] args) {
        testSearch();
    }

    public static void testSearch() {
//        List<String> hashs = HttpRequestUtil.searchSong("淘汰");
        List<String> hashs = HttpRequestUtil.searchMV("海阔天空");
        Set<String> set = new HashSet<>(hashs);
        System.out.println("size: " + set.size());
    }

    public static void testGetSingerSongHash() {
        List<String> hashs = HttpRequestUtil.getSingerSongHashList((long) 3060);
        Set<String> set = new HashSet<>(hashs);
        System.out.println("size: " + set.size());

    }

    public static void testGetSingerClass() {
//        List<SingerClass> singerClasses = HttpRequestUtil.getSingerClassList();
//        Set<SingerClass> set1 = new HashSet<>(singerClasses);
//        System.out.println("size: " + set1.size());
        List<Singer> singers = HttpRequestUtil.getSingerListOfClass(1);
        Set<Singer> set2 = new HashSet<>(singers);
        System.out.println("size: " + set2.size());
    }

    public static void testGetSpecialSongHash() {
//        List<Special> specials = HttpRequestUtil.getSpecialList();
//        Set<Special> set1 = new HashSet<>(specials);
//        System.out.println("specials: " + set1.size());
        List<String> hashs = HttpRequestUtil.getSpecialSongList((long) 125032);
        Set<String> set2 = new HashSet<>(hashs);
        System.out.println(set2.size());
    }

    public static void testGetRankSongHash() {
        List<Rank> ranks = HttpRequestUtil.getRankList();
        System.out.println("ranks: " + ranks.size());
        List<String> hashs = HttpRequestUtil.getSongHashOfRankList(8888);
        Set<String> set = new HashSet<>(hashs);
        System.out.println(set.size());
    }

    public static void getTotalSinger() {
        List<SingerClass> singerClasses = HttpRequestUtil.getSingerClassList();
        for (SingerClass singerClass : singerClasses) {
            List<Singer> singers = HttpRequestUtil.getSingerListOfClass(singerClass.getClassId());
            for (Singer singer : singers) {
                System.out.println("id: " + singer.getSingerId() + " name: " + singer.getSingerName());
            }
            System.out.println("=======================================================");
            System.out.println("classId: " + singerClass.getClassId() + ", list=" + singers.size() + " set= " + "条 finished......");
            System.out.println("=======================================================");

        }
    }
}
