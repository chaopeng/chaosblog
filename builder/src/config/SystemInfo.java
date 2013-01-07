package config;

/**
 * @author chao
 */
public class SystemInfo {
	public static String os = System.getProperty("os.name");
	public static String chatset = os.indexOf("Windows") == -1 ? "UTF-8": "gbk";
	public static String line_separator = System.getProperty("line.separator");
}
