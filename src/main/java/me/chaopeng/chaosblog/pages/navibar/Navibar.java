package me.chaopeng.chaosblog.pages.navibar;

import me.chaopeng.chaosblog.config.Blog;
import me.chaopeng.chaosblog.model.Article;
import me.chaopeng.chaosblog.utils.DirUtils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chao
 */
public class Navibar {
    private static Article[] NavibarLs;

    static {
        File[] files = DirUtils.ls(Blog.getIns().getInputpath() + "/navibar");
        NavibarLs = new Article[files.length];

        Arrays.sort(files, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        int i = -1;
        for (File file : files) {
            Article article = new Article();
            article.readArticle(file.getAbsolutePath());
            NavibarLs[++i] = article;
        }
    }

    public static Article[] getNavibarLs() {
        return NavibarLs;
    }
}
