package pages;

import config.Author;
import config.Blog;
import model.Article;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.navibar.Navibar;
import utils.BeetlUtils;

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
        article.readArticle(Blog.getIns().inputpath + File.separator + "index.html");
        description = article.meta.description;

        try {
            GroupTemplate group = BeetlUtils.getGroup();
            Template template = group.getTemplate("_template" + File.separator + article.meta.layout + ".html");
            template.binding("config", Blog.getIns());
            template.binding("author", Author.getIns());
            template.binding("article", article);
            template.binding("naviLs", Navibar.getNavibarLs());
            String s = template.render();

            String tempPath = File.separator + ".temp" + File.separator + article.name;

            FileWriter fw = new FileWriter(Blog.getIns().inputpath + tempPath);
            fw.write(s);
            fw.close();

            template = group.getTemplate(tempPath);
            template.binding("articles", ArticlePages.getIns().articles);
            template.binding("config", Blog.getIns());
            s = template.render();

            fw = new FileWriter(Blog.getIns().outputpath + article.relativelink);
            fw.write(s);
            fw.close();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
