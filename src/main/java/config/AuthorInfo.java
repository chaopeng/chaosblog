package config;

import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

import utils.GravatarUtils;
import utils.JsonUtils;

/**
 * @author chao
 */
public class AuthorInfo {
	public String mail;
	public String gravatar;
	public String gravatarserver = "http://www.gravatar.com";
	public String weibo;
	public String github;
	
	private AuthorInfo(){
		try {
			JSONObject jo = JsonUtils.readMergerFile(SystemInfo.config_path).get("AuthorInfo");
			JsonUtils.parseJson(jo, this);
		} catch (Exception e) {
			LogFactory.getLog(getClass()).error("!!!", e);
		}
	}
	
	private static AuthorInfo ins = new AuthorInfo();
	public static AuthorInfo getIns(){
		return ins;
	}

	public String getAvatar(){
		return GravatarUtils.getAvatar(gravatarserver, gravatar, "80");
	}

	public String getMail() {
		return mail;
	}

	public String getGravatar() {
		return gravatar;
	}

	public String getGravatarserver() {
		return gravatarserver;
	}

	public String getWeibo() {
		return weibo;
	}

	public String getGithub() {
		return github;
	}
	
	
}
