package com.bobo.threadlocal;

import com.bobo.threadlocal.pojo.OnlineUser;

/**
 * @author Bobo
 * @date 2021/8/11
 * @apiNote 线程本地持有者：存储当前请求线程的登录用户
 */
public class ThreadLocalHolder {
    private final static ThreadLocal<OnlineUser> ThreadLocal = new ThreadLocal<>();

    /**
     * 重写ThreadLocal的三个方法：set、get、remove
     */
    public static void set(OnlineUser user) {
        ThreadLocal.set(user);
    }

    public static OnlineUser get() {
        return ThreadLocal.get();
    }

    public static void remove() {
        ThreadLocal.remove();
    }

    /**
     * 获取登录用户的常用信息：id、姓名
     */
    public static Long getId() {
        return ThreadLocal.get().getId();
    }

    public static String getName() {
        return ThreadLocal.get().getName();
    }

}
