package main;

import java.io.File;

import org.apache.commons.logging.LogFactory;

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

	static{
		CliUtils.setOption("c", true, "config file path", new CliOptionHandler() {

			@Override
			public void call(String[] cliArgs) throws CliException {
				SystemInfo.config_path = cliArgs[0];
			}
		});

		CliUtils.setOption("h", false, "for help", new CliOptionHandler() {

			@Override
			public void call(String[] cliArgs) throws CliException {
				CliUtils.help();
			}
		});
	}

	public static void main(String[] args) {

		LogFactory.getLog(ChaosBlog.class).info("start...");
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
		LogFactory.getLog(ChaosBlog.class).info("output finish");

	}

}
