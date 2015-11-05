package pages;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.Article;

import org.apache.commons.logging.LogFactory;
import org.bee.tl.core.GroupTemplate;
import org.bee.tl.core.Template;

import pages.navibar.Navibar;

import utils.BeetlUtils;
import utils.DirUtils;
import config.AuthorInfo;
import config.BlogConfig;

/**
 * @author chao
 */
public class ArticlePages {
	public Article[] articles;
	public Map<String, List<Article>> tagsArticle;
	public Map<String, List<Article>> catalogueArticle;
	public Map<String, List<Article>> dateArticle;
	
	private ArticlePages(){
		// 遍历post文件夹所有md/html后缀的文件
		File[] files = DirUtils.recursive(BlogConfig.getIns().inputpath+File.separator+"post", "\\.(md|html)$");
		articles = new Article[files.length];
		for (int i = 0; i < files.length; i++) {
			articles[i] = new Article();
			articles[i].readArticle(files[i].getAbsolutePath());
		}
		
		Arrays.sort(articles,  new Comparator<Article>() {
			@Override
			public int compare(Article o1, Article o2) {
				return 0 - o1.date.compareTo(o2.date);
			}
		});
		
		tagsArticle = new TreeMap<String, List<Article>>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});		
		
		catalogueArticle = new TreeMap<String, List<Article>>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		dateArticle = new TreeMap<String, List<Article>>(new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return 0-o1.compareTo(o2);
			}
		});
		
		for (Article article : articles) {
			// 按标签加入
			for (String tag : article.meta.tags) {
				List<Article> ls = tagsArticle.get(tag);
				if(ls == null){
					ls = new ArrayList<Article>();
					tagsArticle.put(tag, ls);
				}
				ls.add(article);
			}
			// 按分类加入
			List<Article> catLs = catalogueArticle.get(article.meta.category);
			if(catLs == null){
				catLs = new ArrayList<Article>();
				catalogueArticle.put(article.meta.category, catLs);
			}
			catLs.add(article);
			// 按时间加入
			String yyyymm = article.date.substring(0, 7);
			List<Article> dateLs = dateArticle.get(yyyymm);
			if(dateLs == null){
				dateLs = new ArrayList<Article>();
				dateArticle.put(yyyymm, dateLs);
			}
			dateLs.add(article);
		}
	}
	
	public void print(){
		for (int i = 0; i < articles.length; i++) {
			// 确保目录存在
			DirUtils.mkdir(BlogConfig.getIns().outputpath
					+BlogConfig.getIns().path
					+File.separator+articles[i].date.replace("-", File.separator));
			// 生成网页
			try{
				GroupTemplate group = BeetlUtils.getGroup();
				Template template = group.getFileTemplate(
						"_template"+File.separator
						+articles[i].getMeta().layout+".html");
				template.set("config", BlogConfig.getIns());
				template.set("author", AuthorInfo.getIns());
				template.set("article", articles[i]);
				template.set("naviLs", Navibar.getNavibarLs());
				
				// 输出文件
				FileWriter fw = new FileWriter(BlogConfig.getIns().outputpath + articles[i].relativelink);
				fw.append(template.getTextAsString());
				fw.close();
			} catch(Exception e){
				LogFactory.getLog(getClass()).error("!!!", e);
			}
		}
	}
	
	private static ArticlePages ins = new ArticlePages();
	public static ArticlePages getIns(){
		return ins;
	}
}
