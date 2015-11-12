package me.chaopeng.chaosblog.model;

import me.chaopeng.chaosblog.config.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import me.chaopeng.chaosblog.utils.DirUtils;
import me.chaopeng.chaosblog.utils.ExecUtils;
import me.chaopeng.chaosblog.utils.FileUtils;
import me.chaopeng.chaosblog.utils.JsonUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Pattern;

/**
 * 文章类
 *
 * @author chao
 */
public class Article {

    private static final Logger logger = LoggerFactory.getLogger(Article.class);

    public MetaInfo meta;
    public String date;
    public String name;
    public String type;
    public String text;
    public String relativelink;
    public String absolutelink;

    private static Pattern datePattern = Pattern
            .compile("^\\d{4}-\\d{2}-\\d{2}-");
    private static Pattern numStart = Pattern
            .compile("^\\d");

    public void readArticle(String filepath) {
        try {
            // 通过文件名整理文件信息
            File file = new File(filepath);

            boolean isArticle = datePattern.matcher(file.getName()).find();
            if (isArticle) {
                String[] info = FileUtils.getFileNameEx(file.getName());
                date = info[0];
                name = info[1];
                type = info[2];

                relativelink = Blog.getIns().path + "/"
                        + date.replace("-", "/") + "/" + name + ".html";
            }
            // 类似index 那些
            else {
                name = FileUtils.getFileName(file.getName());
                type = FileUtils.getFileType(file.getName());
                while (numStart.matcher(name).find()) {
                    name = name.substring(1);
                }
                relativelink = Blog.getIns().path + "/" + name + ".html";
            }

            absolutelink = Blog.getIns().site + relativelink;
            // 读取文件信息
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();

            meta = JsonUtils.readJson(br, MetaInfo.class);

            String line = null;
            while ((line = br.readLine()) != null)
                sb.append(line + "\n");

            if (type.equals("md")) {
                FileWriter fw = new FileWriter("temp");
                fw.write(sb.toString());
                fw.close();

                text = ExecUtils.run(Blog.getIns().markdownengine
                        + " temp");

                DirUtils.rm("temp");
            } else {
                text = sb.toString();
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 获取第一个<p>...</p>
     *
     * @return
     */
    public String getFirstPart() {
        int index = text.indexOf("</p>");
        return text.substring(0, index + 4);
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public String getRelativelink() {
        return relativelink;
    }

    public String getAbsolutelink() {
        return absolutelink;
    }
}
