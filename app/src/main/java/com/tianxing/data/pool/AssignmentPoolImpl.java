package com.tianxing.data.pool;


import com.tianxing.entity.assignment.AssignmentAssigned;
import com.tianxing.entity.assignment.AssignmentFeedback;
import com.tianxing.entity.assignment.AssignmentReply;

import java.util.List;
import java.util.Map;

/**
 * Created by tianxing on 2017/4/6.
 * 保存在内存中的所有作业相关的数据
 */

class AssignmentPoolImpl extends AbstractDataPool implements AssignmentPool {

    //班级列表 K:班级ID  V:作业ID列表
    private Map<Integer, List<Integer>> integerMap;

    //作业集合 K：作业ID V：作业内容
    private Map<Integer, AssignmentAssigned> assignmentMap;

    /**
     * 回复集合 K：作业ID V：作业回复列表
     * 对于学生用户只有一条回复 对于老师用户 列表中包含一条作业下所有学生的回复
     * */
    private Map<Integer, List<AssignmentReply>> replyMap;


    /**
     * 批阅集合 K：作业回复ID V：作业批阅
     * */
    private Map<Integer, AssignmentFeedback> feedbackMap;

    //每个班级的作业列表的容量
    private int capability = 0;


    public AssignmentPoolImpl() {

    }


    /**
     * 调整作业池大小
     *
     * @param capability 每个班级的作业列表缓存数目
     */
    @Override
    public void resize(int capability) {

    }
}
