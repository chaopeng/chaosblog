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
import me.chaopeng.chaosblog.utils.DirUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * @author chao
 */
public class ArticlePages {

    private static final Logger logger = LoggerFactory.getLogger(ArticlePages.class);

    public Article[] articles;
    public Map<String, List<Article>> tagsArticle;
    public Map<String, List<Article>> catalogueArticle;
    public Map<String, List<Article>> dateArticle;

    private ArticlePages() {
        // 遍历post文件夹所有md/html后缀的文件
        File[] files = DirUtils.recursive(Blog.getIns().inputpath + File.separator + "post", "\\.(md|html)$");
        articles = new Article[files.length];
        for (int i = 0; i < files.length; i++) {
            articles[i] = new Article();
            articles[i].readArticle(files[i].getAbsolutePath());
        }

        Arrays.sort(articles, (o1, o2) -> 0 - o1.date.compareTo(o2.date));

        tagsArticle = new TreeMap<>((Comparator<String>) String::compareTo);

        catalogueArticle = new TreeMap<>((Comparator<String>) String::compareTo);

        dateArticle = new TreeMap<>((Comparator<String>) (o1, o2) -> 0 - o1.compareTo(o2));

        for (Article article : articles) {
            // 按标签加入
            for (String tag : article.meta.tags) {
                List<Article> ls = tagsArticle.get(tag);
                if (ls == null) {
                    ls = new ArrayList<>();
                    tagsArticle.put(tag, ls);
                }
                ls.add(article);
            }
            // 按分类加入
            List<Article> catLs = catalogueArticle.get(article.meta.category);
            if (catLs == null) {
                catLs = new ArrayList<>();
                catalogueArticle.put(article.meta.category, catLs);
            }
            catLs.add(article);
            // 按时间加入
            String yyyymm = article.date.substring(0, 7);
            List<Article> dateLs = dateArticle.get(yyyymm);
            if (dateLs == null) {
                dateLs = new ArrayList<>();
                dateArticle.put(yyyymm, dateLs);
            }
            dateLs.add(article);
        }
    }

    public void print() {
        for (int i = 0; i < articles.length; i++) {
            // 确保目录存在
            DirUtils.mkdir(Blog.getIns().outputpath
                    + Blog.getIns().path
                    + File.separator + articles[i].date.replace("-", File.separator));
            // 生成网页
            try {
                GroupTemplate group = BeetlUtils.getGroup();
                Template template = group.getTemplate(
                        "_template" + File.separator
                                + articles[i].getMeta().layout + ".html");
                template.binding("config", Blog.getIns());
                template.binding("author", Author.getIns());
                template.binding("article", articles[i]);
                template.binding("naviLs", Navibar.getNavibarLs());

                // 输出文件
                FileWriter fw = new FileWriter(Blog.getIns().outputpath + articles[i].relativelink);
                fw.append(template.render());
                fw.close();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    private static ArticlePages ins = new ArticlePages();

    public static ArticlePages getIns() {
        return ins;
    }
}
