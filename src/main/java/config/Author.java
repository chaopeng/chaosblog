package config;

import utils.GravatarUtils;

/**
 * @author chao
 */
public class Author {

    private static Author ins;

    public String mail;
    public String gravatar;
    public String gravatarserver;
    public String weibo;
    public String github;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public void setGravatarserver(String gravatarserver) {
        this.gravatarserver = gravatarserver;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getAvatar() {
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

    public static Author getIns() {
        return ins;
    }

    public static void setIns(Author ins) {
        Author.ins = ins;
    }
}
