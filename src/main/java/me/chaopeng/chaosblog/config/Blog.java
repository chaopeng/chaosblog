package me.chaopeng.chaosblog.config;

/**
 * @author chao
 */
public class Blog {

    private static Blog ins;

    private String site;
    private String path;
    private String author;
    private String blogname;
    private String inputpath;
    private String outputpath;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname;
    }

    public String getInputpath() {
        return inputpath;
    }

    public void setInputpath(String inputpath) {
        this.inputpath = inputpath;
    }

    public String getOutputpath() {
        return outputpath;
    }

    public void setOutputpath(String outputpath) {
        this.outputpath = outputpath;
    }

    public static Blog getIns() {
        return ins;
    }

    public static void setIns(Blog ins) {
        Blog.ins = ins;
    }
}
