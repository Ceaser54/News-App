package com.example.hp.appstage1;
/**
 * Created by hp on 05/02/2018.
 */

public class News {
    private String webtitle, weburl, section, web_date,webtitletags;

    public News(String webtitle, String weburl, String section, String web_date,String webtitletags) {
        this.weburl = weburl;
        this.webtitle = webtitle;
        this.section = section;
        this.web_date = web_date;
        this.webtitletags=webtitletags;
    }

    public void setAuthorname(String authorname) {
        this.webtitle = authorname;
    }

    public String getAuthorname() {
        return webtitle;
    }

    public void setWebUrl(String webUrl) {
        this.weburl = webUrl;
    }

    public String getWebUrl() {
        return weburl;
    }

    public void setsection(String section) {
        this.section = section;
    }

    public String getsection() {
        return section;
    }

    public void set_webdate(String web_date) {
        this.web_date = web_date;
    }

    public String getWeb_date() {
        return web_date;
    }

    public void setwebtitle(String webtitletags) {
        this.webtitletags = webtitletags;
    }

    public String getwebtitle() {
        return webtitletags;
    }
}