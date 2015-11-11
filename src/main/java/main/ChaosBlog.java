package main;

import java.io.File;

import org.apache.commons.logging.LogFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ArticlePages;
import pages.IndexPage;
import pages.RssXml;
import pages.navibar.NaviPages;
import utils.DirUtils;
import utils.cli_utils.CliException;
import utils.cli_utils.CliOptionHandler;
import utils.cli_utils.CliUtils;
import config.BlogConfig;
import config.SystemInfo;

/**
 * @author chao
 */
public class ChaosBlog {

	private static final Logger logger = LoggerFactory.getLogger(ChaosBlog.class);

	private static void setupCli(){
		CliUtils.setOption("c", true, "config file path", cliArgs -> SystemInfo.config_path = cliArgs[0]);

		CliUtils.setOption("h", false, "for help", cliArgs -> CliUtils.help());
	}

	public static void main(String[] args) {

		setupCli();

		logger.info("start...");
		CliUtils.parser(args);

		DirUtils.rm(BlogConfig.getIns().outputpath + File.separator
				+ BlogConfig.getIns().path + File.separator, "^[^\\.]");

		DirUtils.mkdir(BlogConfig.getIns().inputpath + File.separator + ".temp"
				+ File.separator);
		IndexPage.print();
		NaviPages.print();
		ArticlePages.getIns().print();

		DirUtils.rm(BlogConfig.getIns().inputpath + File.separator + ".temp"
				+ File.separator);
		DirUtils.rmdir(BlogConfig.getIns().inputpath + File.separator + ".temp"
				+ File.separator);

		RssXml.print();
		logger.info("output finish");

	}

}
