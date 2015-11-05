package pages.navibar;

import java.io.File;
import java.io.FileWriter;

import model.Article;

import org.apache.commons.logging.LogFactory;
import org.bee.tl.core.GroupTemplate;
import org.bee.tl.core.Template;

import pages.ArticlePages;
import utils.BeetlUtils;
import utils.DirUtils;
import config.AuthorInfo;
import config.BlogConfig;

/**
 * @author chao
 */
public class NaviPages {
	public static void print() {
		File[] files = DirUtils.ls(BlogConfig.getIns().inputpath
				+ File.separator + "navibar", "\\.html$");
		for (File file : files) {
			Article article = new Article();
			article.readArticle(file.getAbsolutePath());

			try {
				GroupTemplate group = BeetlUtils.getGroup();
				Template template = group.getFileTemplate("_template"
						+ File.separator + article.meta.layout + ".html");
				template.set("config", BlogConfig.getIns());
				template.set("author", AuthorInfo.getIns());
				template.set("article", article);
				template.set("naviLs", Navibar.getNavibarLs());
				String s = template.getTextAsString();
				
				String tempPath = File.separator+".temp"+File.separator+article.name;
				
				FileWriter fw = new FileWriter(BlogConfig.getIns().inputpath+tempPath);
				fw.write(s);
				fw.close();

				template = group.getFileTemplate(tempPath);
				template.set("articles", ArticlePages.getIns().articles);
				template.set("categories", ArticlePages.getIns().catalogueArticle);
				template.set("archives", ArticlePages.getIns().dateArticle);
				template.set("tags", ArticlePages.getIns().tagsArticle);
				template.set("config", BlogConfig.getIns());
				s = template.getTextAsString();

				fw = new FileWriter(BlogConfig.getIns().outputpath
						+ article.relativelink);
				fw.write(s);
				fw.close();
			} catch (Exception e) {
				LogFactory.getLog(NaviPages.class).error("!!!", e);
			}
		}
	}
}
