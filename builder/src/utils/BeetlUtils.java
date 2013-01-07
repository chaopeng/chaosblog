package utils;

import java.io.File;

import org.bee.tl.core.GroupTemplate;
import org.bee.tl.ext.IncludeFileTemplateTag;
import org.bee.tl.ext.LayoutTag;

import config.BlogConfig;

/**
 * @author chao
 */
public class BeetlUtils {
	private static GroupTemplate group;
	public static GroupTemplate getGroup(){
		return group;
	}
	
	static{
		String home = BlogConfig.getIns().inputpath;
		group = new GroupTemplate(new File(home));
		group.setCharset("UTF-8");
		group.config("<!--:", "-->", "${", "}");
		registerTag();
	}
	
	private static void registerTag(){
		group.registerTag("includeFileTemplate", IncludeFileTemplateTag.class);
		group.registerTag("layout", LayoutTag.class);
	}
	
}
