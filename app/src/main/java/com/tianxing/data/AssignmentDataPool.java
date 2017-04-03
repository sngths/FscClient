package com.tianxing.data;

import com.tianxing.entity.ClassData;
import com.tianxing.entity.ClassDataFactory;
import com.tianxing.entity.info.ClassInfo;
import com.tianxing.model.AssignmentPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianxing on 16/7/19.
 * 存放所有班级和作业数据信息
 */
public class AssignmentDataPool implements AssignmentPool<ClassData> {

    private ArrayList<ClassData> classDataList;//班级数据列表
    private Map<String, Integer> classDataMap;//classID 对应的班级数据位置




    public AssignmentDataPool(){
        classDataList = new ArrayList<>();
        classDataMap = new HashMap<>();
    }


    /**
     * 取得班级总数
     */
    @Override
    public Integer getClassCount() {
        return classDataList.size();
    }

    /**
     * 创建一个班级数据
     *
     * @param classInfo
     */
    @Override
    public void createClassData(ClassInfo classInfo) {
        ClassData data = ClassDataFactory.getNewClassData(classInfo);
        classDataList.add(data);
        classDataMap.put(classInfo.getId(), classDataList.size() -1);

    }

    /**
     * 取得对应位置的一个班级数据组
     *
     * @param position
     */
    @Override
    public ClassData getClassData(int position) {
        return classDataList.get(position);
    }

    @Override
    public ClassData getClassData(String classID) {
        return classDataList.get(classDataMap.get(classID));
    }

    @Override
    public Integer getClassDataPosition(String classID) {
        return classDataMap.get(classID);
    }


}
