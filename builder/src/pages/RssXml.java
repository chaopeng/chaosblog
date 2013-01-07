package pages;

import java.io.File;
import java.util.Date;

import org.apache.commons.logging.LogFactory;

import config.BlogConfig;
import model.Article;
import model.ChannelItem;
import utils.DateUtils;
import utils.RssBuildFactory;

public class RssXml {
	public static void print(){
		Article[] articles = ArticlePages.getIns().articles;
		RssBuildFactory builder = new RssBuildFactory();
		
		int len = articles.length > 20 ? 20 : articles.length;
		for(int i = 0; i< len; ++i){
			ChannelItem item = new ChannelItem();
			item.setAuthor(BlogConfig.getIns().author);
			item.setCategory(articles[i].meta.category);
			item.setDescription(articles[i].meta.description);
			item.setLink("http://"+articles[i].absolutelink);
			item.setPubDate(DateUtils.parseDate(articles[i].date));
			item.setTitle(articles[i].meta.title);
			builder.buildItems(item);
		}
		
		builder.buildChannel(BlogConfig.getIns().blogname
				, "http://", IndexPage.description, "zh-cn"
				, new Date()
				, "Copyright "+BlogConfig.getIns().blogname);  
		
		try {
			builder.buildChannel(BlogConfig.getIns().outputpath
					+ BlogConfig.getIns().path
					+ File.separator + "rss.xml");
		} catch (Exception e) {
			LogFactory.getLog(RssXml.class).error("!!!", e);
		}  
	}
}
