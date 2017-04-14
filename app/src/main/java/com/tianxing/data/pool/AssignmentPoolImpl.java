package com.tianxing.data.pool;


import android.util.SparseArray;

import com.tianxing.pojo.assignment.AssignmentAssigned;
import com.tianxing.pojo.assignment.AssignmentFeedback;
import com.tianxing.pojo.assignment.AssignmentReply;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tianxing on 2017/4/6.
 * 保存在内存中的所有作业相关的数据
 */

class AssignmentPoolImpl extends AbstractDataPool implements AssignmentPool {


    //班级ID列表
    private List<Integer> classIdList;

    //班级作业ID列表列表 K:班级ID  V:作业ID列表
    private SparseArray<LinkedList<Integer>> assignmentIdArray;

    //作业集合 K：作业ID V：作业内容
    private SparseArray<AssignmentAssigned> assignmentArray;

    /**
     * 回复集合 K：作业ID V：作业回复列表
     * 对于学生用户只有一条回复 对于老师用户 列表中包含一条作业下所有学生的回复
     */
    private SparseArray<LinkedList<AssignmentReply>> replyArray;


    /**
     * 批阅集合 K：作业回复ID V：作业批阅
     */
    private SparseArray<AssignmentFeedback> feedbackArray;


    private int maxAssignmentId = 0;

    //每个班级的作业列表的容量
    private int capability = -1;


    public AssignmentPoolImpl() {
        classIdList = new ArrayList<>();
        assignmentIdArray = new SparseArray<>();
        assignmentArray = new SparseArray<>();
        replyArray = new SparseArray<>();
        feedbackArray = new SparseArray<>();
    }


    /**
     * 调整作业池大小
     *
     * @param capability 每个班级的作业列表缓存数目
     */
    @Override
    public void resize(int capability) {
        if (capability <= 0) {
            throw new RuntimeException("capability <= 0");
        }
        for (int id : classIdList) {
            int removeCount = assignmentIdArray.get(id).size() - capability;
            for (int i = 0; i < removeCount; i++) {
                removeLastAssignment(id);
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
        return assignmentIdArray.size();
    }

    /**
     * 指定位置作业条数
     *
     * @param classId
     */
    @Override
    public int assignmentCount(int classId) {
        return assignmentIdArray.get(classId).size();
    }

    /**
     * 取得作业ID
     *
     * @param classId
     * @param idPosition
     */
    @Override
    public int assignmentId(int classId, int idPosition) {
        return assignmentIdArray.get(classId).get(idPosition);
    }

    @Override
    public AssignmentAssigned assignment(int id) {
        return assignmentArray.get(id);
    }

    /**
     * 取得0位置的回复
     *
     * @param id
     */
    @Override
    public AssignmentReply reply(int id) {
        return replyArray.get(id).get(0);
    }

    @Override
    public AssignmentReply reply(int id, int position) {
        return replyArray.get(id).get(position);
    }

    @Override
    public AssignmentFeedback feedback(int id) {
        return feedbackArray.get(id);
    }

    /**
     * 存放
     *
     * @param classID
     */
    @Override
    public void createClass(int classID) {
        if (classIdList.contains(classID)) {
            return;
        }
        classIdList.add(classID);
        //创建作业ID列表
        if (assignmentIdArray.get(classID) == null) {
            assignmentIdArray.put(classID, new LinkedList<Integer>());
        }
    }


    @Override
    public int putAssignment(AssignmentAssigned assignmentAssigned) {
        if (assignmentAssigned == null) {
            return -1;
        }
        int classID = assignmentAssigned.getClassID();
        if (!classIdList.contains(classID)) {
            createClass(classID);
        }
        int assignmentID = assignmentAssigned.getAssignmentID();
        LinkedList<Integer> assignmentList = assignmentIdArray.get(classID);
        if (assignmentList == null){

        }
        //保存ID
        int insertPosition = -1;
        assignmentList.addFirst(assignmentID);
        for (int i = 1; i < assignmentList.size(); i++) {
            if (assignmentID > assignmentList.get(i)) {
                if (i == 1) {
                    insertPosition = 0;
                    break;
                } else {
                    assignmentList.remove(0);
                    assignmentList.add(i, assignmentID);
                    insertPosition = i;
                    break;
                }
            } else if (assignmentID == assignmentList.get(i)) {
                assignmentList.remove(0);
                insertPosition = i - 1;
                break;
            }
        }
        assignmentArray.put(assignmentID, assignmentAssigned);
        return insertPosition;
    }

    @Override
    public void putReply(AssignmentReply reply) {
        if (reply == null) {
            return;
        }
        int id = reply.getAssignmentID();
        LinkedList<AssignmentReply> replyList = replyArray.get(id);
        if (replyList == null) {
            replyList = new LinkedList<>();
            replyList.addLast(reply);
            replyArray.put(id, replyList);
        } else {
            for (int i = 0; i < replyList.size(); i++) {
                if (replyList.get(i).getStudentId() == reply.getStudentId()) {
                    replyList.remove(i);
                    replyList.addLast(reply);
                    return;
                }
            }
            replyList.addLast(reply);
        }

    }

    @Override
    public void putReplyList(int assignmentId, LinkedList<AssignmentReply> replyList) {
        if (replyList == null) {
            return;
        }
        //检查回复列表中所有回复 是否属于该条作业 否则删除
        for (int i = 0; i < replyList.size(); i++) {
            if (replyList.get(i).getAssignmentID() != assignmentId) {
                replyList.remove(i);
            }
        }
        replyArray.put(assignmentId, replyList);
    }

    @Override
    public void putFeedback(AssignmentFeedback feedback) {
        if (feedback == null) {
            return;
        }
        feedbackArray.put(feedback.getReplyId(), feedback);
    }


    private void removeFirstAssignment(int classId) {
        List<Integer> assignmentIdList = assignmentIdArray.get(classId);
        if (assignmentIdList.isEmpty()) {
            return;
        }
        int assignmentId = assignmentIdList.remove(0);
        assignmentArray.remove(assignmentId);
        List<AssignmentReply> replies = replyArray.get(assignmentId);
        replyArray.remove(assignmentId);
        for (AssignmentReply reply : replies) {
            feedbackArray.remove(reply.getReplyId());
        }
    }


    private void removeLastAssignment(int classId) {
        List<Integer> assignmentIdList = assignmentIdArray.get(classId);
        if (assignmentIdList.isEmpty()) {
            return;
        }
        int assignmentId = assignmentIdList.remove(assignmentIdList.size() - 1);
        assignmentArray.remove(assignmentId);
        List<AssignmentReply> replies = replyArray.get(assignmentId);
        replyArray.remove(assignmentId);
        for (AssignmentReply reply : replies) {
            feedbackArray.remove(reply.getReplyId());
        }
    }


}
