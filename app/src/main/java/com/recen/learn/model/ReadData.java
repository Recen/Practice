package com.recen.learn.model;

import java.util.List;

public class ReadData {
    public String _id;
    public String content;
    public String cover;
    public String crawled;
    public String created_at;
    public String published_at;
    public String raw;
    public String title;
    public String uid;
    public String url;
    public Site site;
    class Site{
        public String cat_cn;
        public String cat_en;
        public String desc;
        public String feed_id;
        public String icon;
        public String id;
        public String name;
        public String type;
        public String url;
        public int subscribers;
    }
}
