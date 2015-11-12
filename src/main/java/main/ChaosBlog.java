package main;

import com.google.common.base.Preconditions;
import config.Author;
import config.Blog;
import config.Config;
import config.SystemInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ArticlePages;
import pages.IndexPage;
import pages.RssXml;
import pages.navibar.NaviPages;
import utils.DirUtils;
import utils.YamlUtils;
import utils.cli_utils.CliUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author chao
 */
public class ChaosBlog {

    private static final Logger logger = LoggerFactory.getLogger(ChaosBlog.class);

    private static void setupCli() {
        CliUtils.setOption("c", true, "config file path", cliArgs -> SystemInfo.CONFIG_PATH = cliArgs[0]);

        CliUtils.setOption("h", false, "for help", cliArgs -> CliUtils.help());
    }

    public static void main(String[] args) throws IOException {

        setupCli();

        logger.info("start...");
        CliUtils.parser(args);

        Preconditions.checkArgument(SystemInfo.CONFIG_PATH != null, "must give the path of config.yaml");

        Config config = YamlUtils.decode(new File(SystemInfo.CONFIG_PATH), Config.class);
        Author.setIns(config.getAuthor());
        Blog.setIns(config.getBlog());

        DirUtils.rm(Blog.getIns().outputpath + File.separator
                + Blog.getIns().path + File.separator, "^[^\\.]");

        DirUtils.mkdir(Blog.getIns().inputpath + File.separator + ".temp"
                + File.separator);
        IndexPage.print();
        NaviPages.print();
        ArticlePages.getIns().print();

        DirUtils.rm(Blog.getIns().inputpath + File.separator + ".temp"
                + File.separator);
        DirUtils.rmdir(Blog.getIns().inputpath + File.separator + ".temp"
                + File.separator);

        RssXml.print();
        logger.info("output finish");

    }

}
