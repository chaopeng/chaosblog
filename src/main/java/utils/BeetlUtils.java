package utils;

import config.Blog;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.FileResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author chao
 */
public class BeetlUtils {

    private static final Logger logger = LoggerFactory.getLogger(BeetlUtils.class);

    private static GroupTemplate group;

    public static GroupTemplate getGroup() {
        return group;
    }

    static {
        try {
            String root = Blog.getIns().inputpath;
            FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
            Configuration cfg = Configuration.defaultConfiguration();
            group = new GroupTemplate(resourceLoader, cfg);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


}
