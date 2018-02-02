package com.haijun.shop.application;

import android.app.Application;
import android.content.Context;

import org.xutils.x;

/**
 * @anthor haijun
 * @project name: Shop
 * @class name：com.haijun.shop.application
 * @time 2018-01-31 6:18 PM
 * @describe
 */
public class MyAppliaction extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);  //xUtil初始化

        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }


}
