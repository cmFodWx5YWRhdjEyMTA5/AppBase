package com.haijun.shop.util;

import com.google.gson.Gson;
import com.haijun.shop.bean.User;

/**
 * @anthor haijun
 * @project name: Shop
 * @class name：com.haijun.shop.util
 * @time 2018-02-02 11:29 AM
 * @describe
 */
public class UserUtil {

    public static User user;
    public static User getUserInfo() {
        if (user==null){
            user = UserUtil.getUserFromSP();
        }
        return user;
    }

    public static User getUserFromSP(){
        Gson gson = new Gson();
        return gson.fromJson("user",User.class);
    }

    public static void putUserToSP(User user){
        Gson gson = new Gson();
        String userString = gson.toJson(user);
        SPUtil.putStringValueToSP("user",userString);
    }

    public static boolean isLoginEd(){
        return getUserInfo() != null;
    }
}
