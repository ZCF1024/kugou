package com.zcf.music.high.web.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zcf.music.high.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author zcf
 * @Create 2018/8/4 17:29
 * @Desc
 */
public class HttpRequestUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    public static void main(String[] args) {
        MV mv = getMVByHash("4135FC477494AA522A85B515410C101A");
        System.out.println("name: " + mv.getSongName());
        System.out.println("time: " + mv.getUrl());
    }

    private static final int MAX_SEARCH_PAGE = 1000;

    public static List<String> searchMV(String keyword) {
        String urlStr = APIUtils.MV_SEARCH_URL + keyword + "&page=";
        List<String> hashs = new ArrayList<>();
        int total = 0;
        for (int page = 1; page < MAX_SEARCH_PAGE; page++) {
            String result = getURLContent(urlStr + page);
            JSONArray lists = null;
            try {
                JSONObject data = JSONObject.parseObject(result).getJSONObject("data");
                total = data.getInteger("total");
                lists = data.getJSONArray("lists");
            } catch (ClassCastException e) {
                logger.error("searchMV(): keyword: {}, page: {}, {}", keyword, page, e.getMessage());
            }
            if (lists == null) {
                continue;
            }
            if (total <= hashs.size() || lists.size() == 0) {
                return hashs;
            }
            for (int i = 0; i < lists.size(); i++) {
                JSONObject song = lists.getJSONObject(i);
                hashs.add(song.getString("MvHash"));
            }
        }
        return hashs;
    }

    public static List<String> searchSong(String keyword) {
        String urlStr = APIUtils.SONG_SEARCH_URL + keyword + "&page=";
        List<String> hashs = new ArrayList<>();
        for (int page = 1; page < MAX_SEARCH_PAGE; page++) {
            String result = getURLContent(urlStr + page);
            JSONArray info = null;
            try {
                JSONObject data = JSONObject.parseObject(result).getJSONObject("data");
                info = data.getJSONArray("info");
            } catch (ClassCastException e) {
                logger.error("searchSong() : keyword: {}, page: {}, {}", keyword, page, e.getMessage());
            }
            if (info == null) {
                continue;
            }
            if (info.size() == 0) {
                return hashs;
            }
            for (int i = 0; i < info.size(); i++) {
                JSONObject song = info.getJSONObject(i);
                hashs.add(song.getString("hash"));
            }
        }
        return hashs;
    }

    public static Song getSongByHash(String hash) {
        String url = APIUtils.SONG_INFO_URL1 + hash;
        String result = getURLContent(url);
        Song song = null;
        try {
            JSONObject obj = JSONObject.parseObject(result);
            song = getSongInfo(obj);
        } catch (ClassCastException e) {
            logger.error("GetSongByHash() hash: {}, {}", hash, e.getMessage());
        }
        return song;
    }

    public static List<Song> getSongByHash(List<String> hashs) {
        List<Song> songs = new ArrayList<>();
        for (String hash : hashs) {
            Song song = getSongByHash(hash);
            if (song != null && song.getSingerId() != null) {
                songs.add(song);
            }
        }
        return songs;
    }

    public static MV getMVByHash(String hash) {
        String url = APIUtils.MV_INFO_URL + hash;
        String result = getURLContent(url);
        MV mv = null;
        try {
            JSONObject obj = JSONObject.parseObject(result);
            mv = getMVInfo(obj);
        } catch (ClassCastException e) {
            logger.error("GetMVByHash() hash: {}, {}", hash, e.getMessage());
        }
        return mv;
    }

    public static List<MV> getMVByHash(List<String> hashs) {
        List<MV> mvs = new ArrayList<>();
        int i = 0;
        for (String hash : hashs) {
            // System.out.println("i: " + (i++) + " hash: " + hash);
            MV mv = getMVByHash(hash);
            if (mv != null) {
                mvs.add(mv);
            }
        }
        return mvs;
    }

    /**
     * 获取音乐排行榜
     */
    public static List<Rank> getRankList() {
        // 将 json 转换成 Json 对象, 并得到其 key 为 "rank" 的Json对象
        String reslut = getURLContent(APIUtils.RANK_LIST_URL);
        JSONArray list = null;
        try {
            JSONObject rankObj = JSON.parseObject(reslut).getJSONObject("rank");
            list = rankObj.getJSONArray("list");
        } catch (ClassCastException e) {
            logger.error("getRankList() : {}", e.getMessage());
        }
        List<Rank> ranks = new ArrayList<>();
        if (list == null) {
            return ranks;
        }
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = list.getJSONObject(i);
            ranks.add(getRankInfo(object));
        }
        return ranks;
    }

    public static List<String> getSongHashOfRankList(Integer rankid) {
        String urlStr = APIUtils.RANK_SONG_LIST_URL + rankid + "&page=";
        List<String> hashs = new ArrayList<>();
        for (int page = 1; ; page++) {
            // 得到所有页中的音乐信息
            String result = getURLContent(new StringBuilder(urlStr).append(page).toString());
            JSONArray list = null;
            try {
                JSONObject songsObj = JSONObject.parseObject(result).getJSONObject("songs");
                list = songsObj.getJSONArray("list");
            } catch (ClassCastException e) {
                logger.error("getSongHashOfRankList() rankId: {}, at page: {}, {}", rankid, page, e.getMessage());
            }
            int size;
            if (list == null || (size = list.size()) == 0) {
                return hashs;
            }
            for (int i = 0; i < size; i++) {
                hashs.add(list.getJSONObject(i).getString("hash"));
            }
        }
    }

    private static Rank getRankInfo(JSONObject object) {
        Rank rank = new Rank();
        rank.setRankId(object.getInteger("rankid"));
        rank.setUpdateFrequency(object.getString("update_frequency"));
        rank.setRankName(URLEncodeUtil.decode(object.getString("rankname")));
        return rank;
    }

    /**
     * 得到最新歌曲的 hash
     */
    public static List<String> getLastSongHashList() {
        String result = getURLContent(APIUtils.LAST_SONG_URL);
        // 将 result 转换成 Json 对象, 并得到其 key 为 "data" 的Json数组
        JSONArray data = null;
        try {
            data = JSON.parseObject(result).getJSONArray("data");
        } catch (ClassCastException e) {
            logger.error("getLastSongHashList() : {}", e.getMessage());
        }
        List<String> hashs = new ArrayList<>();
        if (data == null) {
            return hashs;
        }
        for (int i = 0; i < data.size(); i++) {
            JSONObject object = data.getJSONObject(i);
            hashs.add(object.getString("hash"));
        }
        return hashs;
    }

    private static Song getSongInfo(JSONObject object) {
        Song song = new Song();
        song.setHash(object.getString("hash"));
        song.setTime(object.getTimestamp("time"));
        song.setFileName(object.getString("fileName"));
        song.setAlbumId(object.getLong("albumid"));
        song.setDuration(object.getInteger("timeLength"));
        song.setExtName(object.getString("extName"));
        song.setSongName(object.getString("songName"));
        song.setFileSize(object.getInteger("fileSize"));
        song.setUrl(object.getString("url"));
        song.setMvHash(object.getString("mvhash"));
        song.setBitRate(object.getInteger("bitRate"));
        song.setSingerId(object.getLong("singerId"));
        return song;
    }

    /**
     * 获取音乐歌单
     */
    public static List<Special> getSpecialList() {
        String urlStr = APIUtils.SPECIAL_LIST_URL;
        List<Special> specials = new ArrayList<>();
        for (int page = 1; ; page++) {
            // 得到所有页中的歌单信息
            String result = getURLContent(new StringBuilder(urlStr).append(page).toString());
            JSONArray info = null;
            try {
                JSONObject list = JSON.parseObject(result).getJSONObject("plist").getJSONObject("list");
                info = list.getJSONArray("info");
            } catch (ClassCastException e) {
                logger.error("getSpecialList() at page: {}, {}", page, e.getMessage());
            }
            int size;
            if (info == null || (size = info.size()) == 0) {
                return specials;
            }
            for (int i = 0; i < size; i++) {
                JSONObject object = info.getJSONObject(i);
                specials.add(getSpecialInfo(object));
            }
            // System.out.println("page: " + page + " cursize: " + size + " size: " + specials.size());
        }
    }

    public static List<String> getSpecialSongList(Long specialId) {
        String url = new StringBuilder(APIUtils.SPECIAL_SONG_LIST_URL).append(specialId)
                .append("?json=true&page=").toString();
        List<String> hashs = new ArrayList<>();
        for (int page = 1; ; page++) {
            String result = getURLContent(url + page);
            JSONArray info = null;
            try {
                JSONObject object = JSON.parseObject(result);
                info = object.getJSONObject("list").getJSONObject("list").getJSONArray("info");
            } catch (ClassCastException e) {
                logger.error("getSpecialSongList() specialId: {}, at page: {}, {}", specialId, page, e.getMessage());
            }
            int size;
            if (info == null || (size = info.size()) == 0) {
                return hashs;
            }
            for (int i = 0; i < size; i++) {
                JSONObject song = info.getJSONObject(i);
                hashs.add(song.getString("hash"));
            }
        }
    }

    private static Special getSpecialInfo(JSONObject object) {
        Special special = new Special();
        special.setSpecialId(object.getLong("specialid"));
        special.setCollectCount(object.getLong("collectcount"));
        special.setSpecialName(object.getString("specialname"));
        special.setPlayCount(object.getLong("playcount"));
        return special;
    }

    /**
     * 获取所有歌手类别信息
     */
    public static List<SingerClass> getSingerClassList() {
        String result = getDataByHttpRequest(APIUtils.CLASS_OF_SINGER_LIST_URL, "GET");
        JSONArray list = null;
        try {
            list = JSONObject.parseObject(result).getJSONArray("list");
        } catch (ClassCastException e) {
            logger.error("getSingerClassList() : {}", e.getMessage());
        }
        List<SingerClass> singerClasses = new ArrayList<>();
        if (list == null) {
            return singerClasses;
        }
        for (int i = 0; i < list.size(); i++) {
            JSONObject singerClass = list.getJSONObject(i);
            singerClasses.add(getSingerClassInfo(singerClass));
        }
        return singerClasses;
    }

    public static List<Singer> getSingerListOfClass(Integer classId) {
        List<Singer> singers = new ArrayList<>();
        String url = APIUtils.SINGER_OF_CLASS_LIST_URL + classId + "?json=true&page=";
        int size;
        for (int page = 1; page < MAX_SEARCH_PAGE; page++) {
            StringBuilder pageUrl = new StringBuilder(url).append(page);
            String result = getURLContent(pageUrl.toString());
            JSONObject list = null;
            JSONArray info = null;
            try {
                list = JSONObject.parseObject(result).getJSONObject("singers").getJSONObject("list");
                info = list.getJSONArray("info");
            } catch (ClassCastException e) {
                logger.error("getSingerListOfClass() classId: {} at page: {}, {}", classId, page, e.getMessage());
            }
            if (list == null || info == null) {
                continue;
            }
            if ((size = info.size()) == 0 || list.getInteger("total") <= singers.size()) {
                return singers;
            }
            for (int i = 0; i < size; i++) {
                JSONObject singer = info.getJSONObject(i);
                singers.add(getSingerInfo(singer));
            }
        }
        return singers;
    }

    public static Set<Singer> getTotalSingerList(List<SingerClass> singerClasses) {
        Set<Singer> singers = new HashSet<>();
        for (SingerClass singerClass : singerClasses) {
            List<Singer> singer = getSingerListOfClass(singerClass.getClassId());
            singers.addAll(singer);
        }
        return singers;
    }

    private static SingerClass getSingerClassInfo(JSONObject object) {
        SingerClass singerClass = new SingerClass();
        singerClass.setClassId(object.getInteger("classid"));
        singerClass.setClassName(object.getString("classname"));
        singerClass.setImgUrl(object.getString("imgurl"));
        return singerClass;
    }

    private static Singer getSingerInfo(JSONObject object) {
        Singer singer = new Singer();
        singer.setSingerId(object.getLong("singerid"));
        singer.setImgUrl(object.getString("imgurl"));
        singer.setSingerName(object.getString("singername"));
        return singer;
    }

    private static MV getMVInfo(JSONObject object) throws ClassCastException, NullPointerException {
        MV mv = new MV();
        mv.setPlayCount(object.getLong("play_count"));
        mv.setSongName(object.getString("songname"));
        mv.setHash(object.getString("hash"));
        mv.setSinger(object.getString("singer"));
        JSONObject le = object.getJSONObject("mvdata").getJSONObject("le");
        mv.setBitRate(le.getInteger("bitrate"));
        mv.setUrl(le.getString("downurl"));
        mv.setFileSize(le.getInteger("filesize"));
        mv.setTimeLength(le.getInteger("timelength"));
        return mv;
    }

    public static List<String> getSingerSongHashList(Long singerId) {
        String urlStr = new StringBuilder(APIUtils.SINGER_INFO_URL).append(singerId)
                .append("&json=true&page=").toString();
        List<String> hashs = new ArrayList<>();
        for (int page = 1; page < MAX_SEARCH_PAGE; page++) {
            String result = getDataByHttpRequest(urlStr + page, "GET");
            JSONArray list = null;
            try {
                JSONObject songs = JSONObject.parseObject(result).getJSONObject("songs");
                list = songs.getJSONArray("list");
            } catch (ClassCastException e) {
                logger.error("getSingerSongHashList() singerId={} at page: {}, {}", singerId, page, e.getMessage());
            }
            if (list == null) {
                continue;
            }
            if (list.size() == 0) {
                return hashs;
            }
            // System.out.println("page: " + page + " size: " + list.size());
            for (int i = 0; i < list.size(); i++) {
                JSONObject song = list.getJSONObject(i);
                hashs.add(song.getString("hash"));
            }
        }
        return hashs;
    }

    public static String getURLContent(String urlStr) {
        BufferedReader in = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String str = null;
            while ((str = in.readLine()) != null) {
                buffer.append(str);
            }
        } catch (MalformedURLException e) {
            logger.error("getURLContent() : {}", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("getURLContent() : {}", e.getMessage());
        } catch (IOException e) {
            logger.error("getURLContent() : {}", e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("getURLContent() : {}", e.getMessage());
            }
        }
        return buffer.toString();
    }

    /**
     * 发送http请求， 获取返回数据
     *
     * @param url
     * @param method GET, POST, PUT, DELETE（必须全大写）
     */
    private static String getDataByHttpRequest(String url, String method) {
        String result = null;
        StringBuffer buffer = new StringBuffer();
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL httpUrl = new URL(url);
            // 打开和 URL 之间连接
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            // 设置通用属性
            connection.setUseCaches(false);  // 不允许缓存
            connection.setDoOutput(true); // 需要输入
            connection.setDoInput(true); // 需要输出
            connection.setRequestMethod(method);
            connection.setRequestProperty("accept", "*/*");
            // 模拟手机浏览器发送请求
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Linux; U; Android 4.1.2; zh-tw; GT-I9300 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.connect();
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // flush 输出流的缓冲
            out.flush();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            in = new BufferedReader(reader);
            String line;
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            result = buffer.toString();
        } catch (MalformedURLException e) {
            logger.error("getDataByHttpRequest() : {}", e.getMessage());
        } catch (IOException e) {
            logger.error("getDataByHttpRequest() : {}", e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("getDataByHttpRequest() : {}", e.getMessage());
            }
        }
        return result;
    }

}
