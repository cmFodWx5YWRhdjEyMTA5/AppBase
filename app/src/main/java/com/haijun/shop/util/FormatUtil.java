package com.haijun.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @anthor haijun
 * @project name: Shop
 * @class name：com.haijun.shop.util
 * @time 2018-02-28 2:01 PM
 * @describe
 */
public class FormatUtil {

    //判断字符串是否为数字的方法:
    public static boolean isNumeric(String str){
        for (int i = 0; i < str.length(); i++){
            System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    //默认的数据格式
    public static String getDefaultFormatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);  //2018-03-02
        return format.format(date);
    }

    //完整的默认的数据格式，带时分秒毫秒
    public static String getFullDefaultFormatTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);  //2018-03-02 14:56:28.636
        return format.format(date);
    }

    //自定义数据格式
    public static String getSpecialFormatTime(String stringFormat,Date date){
        SimpleDateFormat format = new SimpleDateFormat(stringFormat, Locale.CHINA);
        return format.format(date);
    }

}
