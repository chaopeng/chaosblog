package pages;

import config.Blog;
import model.Article;
import model.ChannelItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DateUtils;
import utils.RssBuildFactory;

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
            item.setAuthor(Blog.getIns().author);
            item.setCategory(articles[i].meta.category);
            item.setDescription(articles[i].meta.description);
            item.setLink("http://" + articles[i].absolutelink);
            item.setPubDate(DateUtils.parseDate(articles[i].date));
            item.setTitle(articles[i].meta.title);
            builder.buildItems(item);
        }

        builder.buildChannel(Blog.getIns().blogname
                , "http://", IndexPage.description, "zh-cn"
                , new Date()
                , "Copyright " + Blog.getIns().blogname);

        try {
            builder.buildChannel(Blog.getIns().outputpath
                    + Blog.getIns().path
                    + File.separator + "rss.xml");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
