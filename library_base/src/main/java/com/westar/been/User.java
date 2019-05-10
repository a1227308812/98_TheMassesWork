package com.westar.been;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ZWP on 2019/3/25 16:56.
 * 描述：测试对象
 */
public class User extends RealmObject implements Serializable {

    @PrimaryKey
    private String id;
    /**
     * 身份id
     */
    private String cardId;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private String age;
    /**
     * 性别
     */
    private String gender;
    /**
     * 分数
     */
    private String fraction;
    /**
     * 头像地址
     */
    private String photoUrl;
    /**
     * 户籍地址
     */
    private String domicile_address;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 事项提醒通知
     */
    private String noticeDescribe;

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getCardId() {
        return cardId;
    }

    public User setCardId(String cardId) {
        this.cardId = cardId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public User setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getAge() {
        return age;
    }

    public User setAge(String age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public User setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getFraction() {
        return fraction;
    }

    public User setFraction(String fraction) {
        this.fraction = fraction;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public User setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public String getDomicile_address() {
        return domicile_address;
    }

    public User setDomicile_address(String domicile_address) {
        this.domicile_address = domicile_address;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public User setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getNoticeDescribe() {
        return noticeDescribe;
    }

    public User setNoticeDescribe(String noticeDescribe) {
        this.noticeDescribe = noticeDescribe;
        return this;
    }
}
