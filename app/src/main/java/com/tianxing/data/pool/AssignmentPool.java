package com.tianxing.data.pool;

import com.tianxing.entity.assignment.AssignmentAssigned;
import com.tianxing.entity.assignment.AssignmentFeedback;
import com.tianxing.entity.assignment.AssignmentReply;

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
    int assignmentId(int classPosition, int idPosition);


    AssignmentAssigned assignment(int id);
    /**
     * 取得0位置的回复
     * */
    AssignmentReply reply(int id);

    AssignmentReply reply(int id, int position);

    AssignmentFeedback feedback(int id);


    /**
     * 存放
     * */
    void putAssignment(AssignmentAssigned assignmentAssigned);

    void putReply(AssignmentReply reply);

    void putFeedback(AssignmentFeedback feedback);




}
