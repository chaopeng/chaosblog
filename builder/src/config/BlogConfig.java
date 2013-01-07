package config;

import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import utils.JsonUtils;

/**
 * @author chao
 */
public class BlogConfig {
	public String site;
	public String path;
	public String markdownengine;
	public String author;
	public String blogname;
	public String inputpath = System.getProperty("user.dir");
	public String outputpath;
	
	private BlogConfig(){
		try {
			JSONObject jo = JsonUtils.readMergerFile("_config.json").get("BlogConfig");
			JsonUtils.parseJson(jo, this);
		} catch (Exception e) {
			LogFactory.getLog(getClass()).error("!!!", e);
		}
	}
	
	private static BlogConfig ins = new BlogConfig();
	public static BlogConfig getIns(){
		return ins;
	}
	public String getSite() {
		return site;
	}
	public String getPath() {
		return path;
	}
	public String getMarkdownengine() {
		return markdownengine;
	}
	public String getAuthor() {
		return author;
	}
	public String getBlogname() {
		return blogname;
	}
	public String getInputpath() {
		return inputpath;
	}
	public String getOutputpath() {
		return outputpath;
	}
	
}
