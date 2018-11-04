package com.zcf.music.high.web.utils;

/**
 * @Author Administrator
 * @Create 2018/8/4 0004 下午 23:19
 * @Desc
 */
public class APIUtils {

    /**
     * 获取最新歌曲的 API
     */
    public static String LAST_SONG_URL = "http://m.kugou.com/?json=true";

    /**
     * 获取所有音乐排行版的信息 API
     */
    public static String RANK_LIST_URL = "http://m.kugou.com/rank/list&json=true";

    /**
     * 获取单个排行版下所有信息API
     * 参数 rankid, page, json=true
     */
    public static String RANK_SONG_LIST_URL = "http://m.kugou.com/rank/info/?json=true&rankid=";

    /**
     * 获取所有歌单的信息 API
     * 参数 page
     */
    public static String SPECIAL_LIST_URL = "http://m.kugou.com/plist/index?json=true&page=";

    /**
     * 获取单个歌单中的所有歌曲信息 API
     * 参数 specialid, json=true
     */
    public static String SPECIAL_SONG_LIST_URL = "http://m.kugou.com/plist/list/";

    /**
     * 获取歌手分类 API
     * 参数
     */
    public static String CLASS_OF_SINGER_LIST_URL = "http://m.kugou.com/singer/class&json=true";

    /**
     * 获取单个类别下所有的歌手信息 API
     * 参数 classid, json=true
     */
    public static String SINGER_OF_CLASS_LIST_URL = "http://m.kugou.com/singer/list/";

    /**
     * 获取单个歌手信息 API
     * 参数 singerid, json=true
     */
    public static String SINGER_INFO_URL = "http://m.kugou.com/singer/info/";

    /**
     * 获取歌曲信息(不带歌词)
     * 歌曲的hash
     */
    public static String SONG_INFO_URL1 = "http://m.kugou.com/app/i/getSongInfo.php?cmd=playInfo&hash=";

    /**
     * 获取歌曲信息(带歌词)
     * 歌曲的hash
     */
    public static String SONG_INFO_URL2 = "http://www.kugou.com/yy/index.php?r=play/getdata&hash=";

    /**
     * 获取 热门搜索列表
     * 必选参数: plat :开始数 count : 热门搜索关键字返回
     */
    public static String HOT_KEYWORD_LIST_URL = "http://mobilecdn.kugou.com/api/v3/search/hot?format=json&plat=";

    /**
     * 音乐搜索
     * 参数: keyword : 关键字, page
     */
    public static String SONG_SEARCH_URL = "http://mobilecdn.kugou.com/api/v3/search/song?format=json&pagesize=30&showtype=1&keyword=";

    /**
     * 获取mv搜索列表
     * 参数：keyword, page
     */
    public static String MV_SEARCH_URL = "http://mvsearch.kugou.com/mv_search?userid=-1&clientver=&platform=WebFilter&tag=em&filter=2&iscorrection=1&privilege_filter=0&_=1515052279917&pagesize=30&keyword=";

    /**
     * 获取mv详情、包括MP4文件
     * 参数： hash
     */
    public static String MV_INFO_URL = "http://m.kugou.com/app/i/mv.php?cmd=100&ismp3=1&ext=mp4&hash=";
}
