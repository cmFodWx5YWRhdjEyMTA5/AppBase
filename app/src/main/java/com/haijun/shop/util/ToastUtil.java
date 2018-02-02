package com.haijun.shop.util;

import android.widget.Toast;

import com.haijun.shop.application.MyAppliaction;

/**
 * @anthor haijun
 * @project name: Shop
 * @class name：com.haijun.shop.util
 * @time 2018-02-02 11:45 AM
 * @describe
 */
public class ToastUtil {
    private static Toast mToast;


    public static void showToask(String text){
        if (mToast!=null){
            mToast.cancel();
        }
        mToast = Toast.makeText(MyAppliaction.getContext(), text, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToask(int id){
        if (mToast!=null){
            mToast.cancel();
        }
        mToast = Toast.makeText(MyAppliaction.getContext(),id,Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToask(String text,int  time){
        if (mToast!=null){
            mToast.cancel();
        }
        mToast = Toast.makeText(MyAppliaction.getContext(),text,time);
        mToast.show();
    }
}
