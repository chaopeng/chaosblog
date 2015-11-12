package me.chaopeng.chaosblog.config;

/**
 * @author chao
 */
public class SystemInfo {
    public static String OS = System.getProperty("os.name");
    public static String CHARSET = !OS.contains("Windows") ? "UTF-8" : "gbk";
    public static String CONFIG_PATH;
}
