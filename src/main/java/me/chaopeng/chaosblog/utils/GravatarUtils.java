package me.chaopeng.chaosblog.utils;


import com.google.common.base.Strings;

/**
 * @author chao
 * @see <a href="http://en.gravatar.com/site/implement/images/">Gravatar头像url参数</a>
 */
public class GravatarUtils {
    /**
     * @param email
     * @param d     - 留空 显示gravatar官方图形
     *              - 404 直接返回404错误状态
     *              - mm 神秘人(一个灰白头像)
     *              - identicon 抽象几何图形
     *              - monsterid 小怪物
     *              - wavatar 用不同面孔和背景组合生成的头像
     *              - retro 八位像素复古头像
     * @param size  (1~512)
     * @param rate  (g, pg, r, x) 默认g
     * @server
     */
    public static String getAvatar(String server, String email, String size, String d, String rate) {
        StringBuilder sb = new StringBuilder(server).append("/avatar/");
        sb.append(Md5Utils.getMD5(email.toLowerCase()));
        sb.append("?s=").append(size);
        if (!Strings.isNullOrEmpty(d)) {
            sb.append("&d=").append(d);
        }
        if (!Strings.isNullOrEmpty(rate)) {
            sb.append("&r=").append(rate);
        }
        return sb.toString();
    }

    public static String getAvatar(String server, String email, String size) {
        return getAvatar(server, email, size, "", "");
    }
}
