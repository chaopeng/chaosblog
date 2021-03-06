package me.chaopeng.chaosblog.pages;

import me.chaopeng.chaosblog.config.Author;
import me.chaopeng.chaosblog.config.Blog;
import me.chaopeng.chaosblog.model.Article;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.chaopeng.chaosblog.pages.navibar.Navibar;
import me.chaopeng.chaosblog.utils.BeetlUtils;

import java.io.File;
import java.io.FileWriter;

/**
 * @author chao
 */
public class IndexPage {

    private static final Logger logger = LoggerFactory.getLogger(IndexPage.class);

    public static String description;

    public static void print() {
        Article article = new Article();
        article.readArticle(Blog.getIns().getInputpath() + File.separator + "index.html");
        description = article.getMeta().description;

        try {
            GroupTemplate group = BeetlUtils.getGroup();
            Template template = group.getTemplate("_template" + File.separator + article.getMeta().layout + ".html");
            template.binding("config", Blog.getIns());
            template.binding("author", Author.getIns());
            template.binding("article", article);
            template.binding("naviLs", Navibar.getNavibarLs());
            String s = template.render();

            String tempPath = File.separator + ".temp" + File.separator + article.getName();

            FileWriter fw = new FileWriter(Blog.getIns().getInputpath() + tempPath);
            fw.write(s);
            fw.close();

            template = group.getTemplate(tempPath);
            template.binding("articles", ArticlePages.getIns().articles);
            template.binding("config", Blog.getIns());
            s = template.render();

            fw = new FileWriter(Blog.getIns().getOutputpath() + article.getRelativelink());
            fw.write(s);
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
