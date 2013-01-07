package model;

import java.io.BufferedReader;

import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import utils.JsonUtils;

/**
 * 文章的meta信息
 * @author chao
 */
public class MetaInfo {
	public String layout;
	public String title;
	public String category;
	public String description;
	public String[] tags;
	
	public void readMeta(BufferedReader br){
		JSONObject jo;
		try {
			jo = JsonUtils.readSingleJson(br);
			JsonUtils.parseJson(jo, this);
		} catch (Exception e) {
			LogFactory.getLog(getClass()).error("!!!", e);
		}
	}

	public String getLayout() {
		return layout;
	}

	public String getTitle() {
		return title;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String[] getTags() {
		return tags;
	}
	
	
}
