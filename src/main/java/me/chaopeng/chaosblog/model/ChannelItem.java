package me.chaopeng.chaosblog.model;

import java.util.Date;

public class ChannelItem {
    private String title;// Rss文件中Item的标题
    private String link;// Rss文件中Item对应的连接
    private String description;// Item的描述
    private Date pubDate;// Item发布的时间
    private String author;// Item作者
    private String category;// Item所属的频道范畴

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
