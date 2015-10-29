package com.example.abner.stickerdemo.utils;

/**
 * Created by Abner on 15/2/12.
 * QQ 230877476
 * Email nimengbo@gmail.com
 */
public class CommonUtils {


    private static String TAG = "CommonUtils";
    private static long lastClickTime;
    private static int lastClickViewId;
    private static final int KEY_PREVENT_TS = -20000;


    /**
     * 计算分享内容的字数，一个汉字=两个英文字母，一个中文标点=两个英文标点 注意：该函数的不适用于对单个字符进行计算，因为单个字符四舍五入后都是1
     *
     * @param c
     * @return
     */
    public static long calculateLength(CharSequence c) {
        double len = 0;
        for (int i = 0; i < c.length(); i++) {
            int tmp = (int) c.charAt(i);
            if (tmp > 0 && tmp < 127) {
                len += 0.5;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }

}
