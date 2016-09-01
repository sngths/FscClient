package com.tianxing.entity.info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianxing on 16/7/13.
 */
public class ClassInfo implements InfoEntity {

    private ArrayList<UserInfo> userInfos;

    public ClassInfo(){
        userInfos = new ArrayList<>();
    }


    /**
     * 取得学生总数
     * */
    public Integer getStudentCount(){
        return userInfos.size();
    }

    /**
     * 取得学生信息列表
     * */
    public List<UserInfo> getStudentList(){
        return userInfos;
    }

    /**
     * 取得对应位置的一个学生信息
     * */
    public UserInfo getStudentInfo(Integer position){
        return userInfos.get(position);
    }


    /**
     * 添加一个学生信息
     * */
    public void putUserInfo(UserInfo userInfo){
        userInfos.add(userInfo);
    }
}
