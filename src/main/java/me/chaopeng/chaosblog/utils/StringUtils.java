package me.chaopeng.chaosblog.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * StringTools:字符串工具类 2011-12-25
 *
 * @author chao
 */
public class StringUtils {
    private static boolean checkStr(String str) {
        boolean bool = true;
        if (str == null || "".equals(str.trim()))
            bool = false;
        return bool;
    }

    public static int toInt(String str) {
        return checkStr(str) ? Integer.parseInt(str) : 0;
    }

    public static String UpFirst(String str) {
        return str.substring(0, 1).toUpperCase().concat(str.substring(1));
    }

    public static String concat(Object[] arr, String split) {
        StringBuilder sb = new StringBuilder(String.valueOf(arr[0]));
        for (int i = 1; i < arr.length; ++i) {
            sb.append(split);
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static int[] toIntArr(String s, String split) {
        String[] intStr = s.split(split);
        int[] res = new int[intStr.length];
        for (int i = 0; i < intStr.length; ++i) {
            res[i] = toInt(intStr[i]);
        }
        return res;
    }

    public static List<Integer> toIntLs(String s, String split) {
        String[] intStr = s.split(split);
        List<Integer> res = new ArrayList<Integer>(intStr.length);
        for (int i = 0; i < intStr.length; ++i) {
            res.add(toInt(intStr[i]));
        }
        return res;
    }
}
