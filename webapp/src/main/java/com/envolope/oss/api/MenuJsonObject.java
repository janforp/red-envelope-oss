package com.envolope.oss.api;

import java.util.List;

/**
 * Created by Jan on 16/8/4.
 *
 * 公众号的 菜单 json 对象
 */
public class MenuJsonObject {

    private Integer is_menu_open;

    private SelfMenuInfo selfmenu_info;



    public static class SelfMenuInfo {

        private ButtonInfo button;

    }

    public static class ButtonInfo{

        private String name;
        private SubButton sub_button;

        private List<ListText> listTexts;
        private List<ListImg> listImg;

    }

    public static class  ListText {
        private String type;
        private String name;
        private String value;
    }
    public static class  ListImg {
        private String TYPE;
        private String name;
        private String value;
    }

    public static class SubButton{

        private List<ListVo> list;
    }

    public static class  ListVo {

        private List<ListView> listView = null;
        private List<ListNews> listNews = null;

        private List<ListVideo> videos = null;
        private List<ListVoice> voices = null;

    }

    public static class ListView {

        private String type;
        private String name;
        private String url;
    }

    public static class  ListNews {
        private String type;
        private String news;
        private String value;

        private List<NewsInfo> news_info;

    }
    public static class  ListVideo {
        private String type;
        private String name;
        private String value;
    }
    public static class  ListVoice {
        private String type;
        private String name;
        private String value;
    }

    private static class NewsInfo {

        private List<ListTitle> list = null;
    }

    private static class ListTitle {
        private String title;
        private String author;
        private String digest;
        private Integer show_cover;
        private String cover_url;
        private String content_url;
        private String source_url;
    }
}
