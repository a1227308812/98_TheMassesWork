package com.westar.masseswork_98.been;

import com.alibaba.android.arouter.facade.service.SerializationService;

import java.io.Serializable;

/**
 * Created by ZWP on 2019/3/22.
 * 描述：
 */
public class TestBeen1 extends GsonServiceImpl{
    public String name;
    public String age;
    public String gender;

    public TestBeen1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public TestBeen1(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "TestBeen1{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
