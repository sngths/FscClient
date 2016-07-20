package com.tianxing.model.data;

import com.tianxing.entity.ClassData;
import com.tianxing.entity.ClassDataFactory;
import com.tianxing.model.AssignmentPool;

import java.util.ArrayList;

/**
 * Created by tianxing on 16/7/19.
 * 存放所有班级和作业数据信息
 */
public class AssignmentDataPool implements AssignmentPool<ClassData> {

    private ArrayList<ClassData> classDatas;




    public AssignmentDataPool(){
        classDatas = new ArrayList<>();
        init();
    }


    /**
     * 取得班级总数
     */
    @Override
    public Integer getClassCount() {
        return classDatas.size();
    }

    /**
     * 取得对应位置的一个班级数据组
     *
     * @param position
     */
    @Override
    public ClassData getClassData(int position) {
        return classDatas.get(position);
    }


    /**
     * 存入一些固定数据
     * */
    private void init(){
        classDatas.add(ClassDataFactory.getNewClassData("一年级一班"));
        classDatas.add(ClassDataFactory.getNewClassData("一年级二班"));
        classDatas.add(ClassDataFactory.getNewClassData("一年级三班"));
    }
}
