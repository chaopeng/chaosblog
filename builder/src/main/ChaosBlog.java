package main;

import java.io.File;

import org.apache.commons.logging.LogFactory;

import pages.ArticlePages;
import pages.IndexPage;
import pages.RssXml;
import pages.navibar.NaviPages;
import utils.DirUtils;
import config.BlogConfig;

/**
 * @author chao
 */
public class ChaosBlog {

	public static void main(String[] args) {
		LogFactory.getLog(ChaosBlog.class).info("start...");
		DirUtils.rm(BlogConfig.getIns().outputpath+File.separator+BlogConfig.getIns().path+File.separator, "^[^\\.]");
		
		DirUtils.mkdir(BlogConfig.getIns().inputpath+File.separator+".temp"+File.separator);
		IndexPage.print();
		NaviPages.print();
		ArticlePages.getIns().print();
		
		DirUtils.rm(BlogConfig.getIns().inputpath+File.separator+".temp"+File.separator);
		DirUtils.rmdir(BlogConfig.getIns().inputpath+File.separator+".temp"+File.separator);
		
		RssXml.print();
		LogFactory.getLog(ChaosBlog.class).info("output finish");

	}

}
