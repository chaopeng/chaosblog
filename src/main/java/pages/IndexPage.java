package pages;

import java.io.File;
import java.io.FileWriter;

import model.Article;

import org.apache.commons.logging.LogFactory;
import org.bee.tl.core.GroupTemplate;
import org.bee.tl.core.Template;

import pages.navibar.Navibar;
import utils.BeetlUtils;
import config.AuthorInfo;
import config.BlogConfig;

/**
 * @author chao
 */
public class IndexPage {
	public static String description;
	public static void print(){
		Article article = new Article();
		article.readArticle(BlogConfig.getIns().inputpath+File.separator+"index.html");
		description = article.meta.description;
		
		try{
			GroupTemplate group = BeetlUtils.getGroup();
			Template template = group.getFileTemplate("_template"+File.separator+article.meta.layout+".html");
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
			template.set("config", BlogConfig.getIns());
			s = template.getTextAsString();
			
			fw = new FileWriter(BlogConfig.getIns().outputpath + article.relativelink);
			fw.write(s);
			fw.close();
		} catch(Exception e){
			LogFactory.getLog(IndexPage.class).error("!!!", e);
		}
	}
}
