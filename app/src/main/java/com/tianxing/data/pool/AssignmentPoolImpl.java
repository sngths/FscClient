package com.tianxing.data.pool;


import android.util.SparseArray;

import com.tianxing.entity.assignment.AssignmentAssigned;
import com.tianxing.entity.assignment.AssignmentFeedback;
import com.tianxing.entity.assignment.AssignmentReply;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tianxing on 2017/4/6.
 * 保存在内存中的所有作业相关的数据
 */

class AssignmentPoolImpl extends AbstractDataPool implements AssignmentPool {

    //班级列表 K:班级ID  V:作业ID列表
    private Map<Integer, LinkedList<Integer>> classMap;

    //作业集合 K：作业ID V：作业内容
    private Map<Integer, AssignmentAssigned> assignmentMap;

    /**
     * 回复集合 K：作业ID V：作业回复列表
     * 对于学生用户只有一条回复 对于老师用户 列表中包含一条作业下所有学生的回复
     * */
    private Map<Integer, LinkedList<AssignmentReply>> replyMap;


    /**
     * 批阅集合 K：作业回复ID V：作业批阅
     * */
    private Map<Integer, AssignmentFeedback> feedbackMap;


    private int maxAssignmentId = 0;

    //每个班级的作业列表的容量
    private int capability = 0;


    public AssignmentPoolImpl() {
        //classMap = new SparseArray<>();
    }


    /**
     * 调整作业池大小
     *
     * @param capability 每个班级的作业列表缓存数目
     */
    @Override
    public void resize(int capability) {
        if (capability <= 0){
            throw new RuntimeException("capability <= 0");
        }
        for (List<Integer> assignmentIdList:classMap.values()){
            int removeCount = assignmentIdList.size() - capability;
            if (removeCount <= 0){
                return;
            }else {

            }
        }
    }

    /**
     * 检查当前容量 超过指定容量则做相应调整
     */
    @Override
    public void checkCapability() {

    }

    /**
     * 取得班级数目
     */
    @Override
    public int classCount() {
        return classMap.size();
    }

    /**
     * 指定位置作业条数
     *
     * @param classId
     */
    @Override
    public int assignmentCount(int classId) {
        return classMap.get(classId).size();
    }

    /**
     * 取得作业ID
     *
     * @param classPosition
     * @param idPosition
     */
    @Override
    public int assignmentId(int classPosition, int idPosition) {
        return classMap.get(classPosition).get(idPosition);
    }

    @Override
    public AssignmentAssigned assignment(int id) {
        return assignmentMap.get(id);
    }

    /**
     * 取得0位置的回复
     *
     * @param id
     */
    @Override
    public AssignmentReply reply(int id) {
        return replyMap.get(id).get(0);
    }

    @Override
    public AssignmentReply reply(int id, int position) {
        return replyMap.get(id).get(position);
    }

    @Override
    public AssignmentFeedback feedback(int id) {
        return feedbackMap.get(id);
    }

    /**
     * 存放
     *
     * @param assignmentAssigned
     */
    @Override
    public void putAssignment(AssignmentAssigned assignmentAssigned) {
        int id = assignmentAssigned.getAssignmentID();
        //判断存放位置
        if (id > maxAssignmentId){


            maxAssignmentId = id;
        }else if (id == maxAssignmentId){

        }else {

        }
    }

    @Override
    public void putReply(AssignmentReply reply) {

    }

    @Override
    public void putFeedback(AssignmentFeedback feedback) {

    }


    /**
     * 移除指定班级 顶部的一条作业
     * */
    private void removeFirstElement(int classId){

    }

    /**
     * 移除指定班级 底部一条作业
     * */
    private void removeLastElement(int classId){

    }


}
