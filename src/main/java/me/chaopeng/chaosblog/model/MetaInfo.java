package me.chaopeng.chaosblog.model;

/**
 * 文章的meta信息
 *
 * @author chao
 */
public class MetaInfo {
    public String layout;
    public String title;
    public String category;
    public String description;
    public String[] tags;

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
