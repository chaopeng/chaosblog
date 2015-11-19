package me.chaopeng.chaosblog.config;

import me.chaopeng.chaosblog.utils.GravatarUtils;

/**
 * @author chao
 */
public class Author {

    private static Author ins;

    private String mail;
    private String gravatar;
    private String gravatarserver;
    private String weibo;
    private String github;
    private String wechat;


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGravatar() {
        return gravatar;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public String getGravatarserver() {
        return gravatarserver;
    }

    public void setGravatarserver(String gravatarserver) {
        this.gravatarserver = gravatarserver;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public static Author getIns() {
        return ins;
    }

    public static void setIns(Author ins) {
        Author.ins = ins;
    }
}
