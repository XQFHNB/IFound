package com.example.anif.beans;

import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;

/**
 * @author XQF
 * @created 2017/4/17
 */
public class MyUser extends AVUser {


    /**
     * 因为是继承类，所以使用一个类的引用
     *
     * @return
     */
    public static MyUser getCurrentUser() {
        return getCurrentUser(MyUser.class);
    }

    /**
     * 拿到当前对象的ID.因为AVUser是继承于AVObject中，都可以使用getObject()方法，其实也是相当于一个toString().
     *
     * @return
     */
    public static String getCurrentUserId() {
        MyUser mUser = getCurrentUser();
        return mUser == null ? mUser.getObjectId() : null;
    }

    /**
     * 根据用户的昵称电话和密码注册进用户体系
     *
     * @param phone    电话
     * @param name     昵称
     * @param password 密码
     * @param callback 注册成功的回调，也就是注册成功的反馈信息
     */
    public static void signUpByNameAndPwd(String phone, String name, String password, SignUpCallback callback) {
        AVUser user = new AVUser();
        user.setMobilePhoneNumber(phone);
        user.setUsername(name);
        user.setPassword(password);
        user.signUpInBackground(callback);
    }

}
