package utils;


/**
 * @author chao
 */
public class FileUtils {
    /**
     * 获取文件的文件名，不含后缀
     */
    public static String getFileName(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1) {
            return filename;
        }
        return filename.substring(0, dotIndex);
    }

    /**
     * 获取文件后缀类型
     */
    public static String getFileType(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1) {
            return "";
        }
        return filename.substring(dotIndex + 1);
    }

    /**
     * 获取文件的文件名，用于文件名格式为yyyy-mm-dd-filename.type
     *
     * @return String[]{yyyy-mm-dd, filename, type}
     */
    public static String[] getFileNameEx(String filename) {
        String name = getFileName(filename);
        String type = getFileType(filename);
        return new String[]{name.substring(0, 10), name.substring(11), type};
    }

}
