package com.example.system.base.config;


import com.example.system.pojo.UserInfo;

public class LoginUser {

    private static final ThreadLocal<UserInfo> threadLocal = new ThreadLocal<>();

    public static void saveUser(UserInfo userInfo) {
        threadLocal.set(userInfo);
    }

    public static UserInfo getUser() {
        return threadLocal.get();
    }

    public static void removeUser() {
        threadLocal.remove();
    }

}
