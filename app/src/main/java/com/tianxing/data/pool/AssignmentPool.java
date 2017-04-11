package com.tianxing.data.pool;

import com.tianxing.entity.assignment.AssignmentAssigned;
import com.tianxing.entity.assignment.AssignmentFeedback;
import com.tianxing.entity.assignment.AssignmentReply;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by tianxing on 2017/4/6.
 */

public interface AssignmentPool extends DataPool {

    /**
     * 调整作业池大小
     * @param capability 每个班级的作业列表缓存数目
     * */
    void resize(int capability);


    /**
     * 检查当前容量 超过指定容量则做相应调整
     * */
    void checkCapability();



    /**
     * 取得班级数目
     * */
    int classCount();

    /**
     * 指定位置作业条数
     * */
    int assignmentCount(int classId);

    /**
     * 取得作业ID
     */
    int assignmentId(int classId, int idPosition);


    AssignmentAssigned assignment(int assignmentId);
    /**
     * 取得0位置的回复
     * */
    AssignmentReply reply(int assignmentId);

    AssignmentReply reply(int assignmentId, int position);

    AssignmentFeedback feedback(int replyId);


    /**
     * 存放
     * */
    /**
     * 添加一个班级
     * */
    void createClass(int classID);

    /**
     * 放入一条作业 返回存放位置 如果对象已存在则替换对象 并返回-1
     * 传入对象 保存在
     * */
    int putAssignment(AssignmentAssigned assignmentAssigned);

    void putReply(AssignmentReply reply);

    void putReplyList(int assignmentId, LinkedList<AssignmentReply> replyList);

    void putFeedback(AssignmentFeedback feedback);




}
