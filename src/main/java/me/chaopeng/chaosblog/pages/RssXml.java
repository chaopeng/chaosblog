package me.chaopeng.chaosblog.pages;

import me.chaopeng.chaosblog.config.Blog;
import me.chaopeng.chaosblog.model.Article;
import me.chaopeng.chaosblog.model.ChannelItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.chaopeng.chaosblog.utils.DateUtils;
import me.chaopeng.chaosblog.utils.RssBuildFactory;

import java.io.File;
import java.util.Date;

public class RssXml {

    private static final Logger logger = LoggerFactory.getLogger(RssXml.class);

    public static void print() {
        Article[] articles = ArticlePages.getIns().articles;
        RssBuildFactory builder = new RssBuildFactory();

        int len = articles.length > 20 ? 20 : articles.length;
        for (int i = 0; i < len; ++i) {
            ChannelItem item = new ChannelItem();
            item.setAuthor(Blog.getIns().getAuthor());
            item.setCategory(articles[i].getMeta().category);
            item.setDescription(articles[i].getMeta().description);
            item.setLink("http://" + articles[i].getAbsolutelink());
            item.setPubDate(DateUtils.parseDate(articles[i].getDate()));
            item.setTitle(articles[i].getMeta().title);
            builder.buildItems(item);
        }

        builder.buildChannel(Blog.getIns().getBlogname()
                , "http://", IndexPage.description, "zh-cn"
                , new Date()
                , "Copyright " + Blog.getIns().getBlogname());

        try {
            builder.buildChannel(Blog.getIns().getOutputpath()
                    + Blog.getIns().getPath()
                    + File.separator + "rss.xml");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
