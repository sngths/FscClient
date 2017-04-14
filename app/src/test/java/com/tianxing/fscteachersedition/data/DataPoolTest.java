package com.tianxing.fscteachersedition.data;

import com.tianxing.data.DataManager;
import com.tianxing.data.pool.AssignmentPool;
import com.tianxing.data.pool.DataPoolManager;
import com.tianxing.pojo.assignment.AssignmentAssigned;
import com.tianxing.pojo.assignment.AssignmentFeedback;
import com.tianxing.pojo.assignment.AssignmentReply;
import com.tianxing.pojo.file.ImageFile;
import com.tianxing.fscteachersedition.BuildConfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by tianxing on 2017/4/10.
 *
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class DataPoolTest {


    @Test
    public void assignmentPool(){
        DataManager manager = DataPoolManager.getInstance();
        manager.initialize();
        AssignmentPool pool = manager.getDataPool(AssignmentPool.class);
        Assert.assertEquals(pool.classCount(), 0);
        pool.createClass(1);
        pool.createClass(2);
        pool.createClass(3);
        int assignmentId = random.nextInt(9999);
        int replyId = random.nextInt(9999);
        AssignmentAssigned assignmentAssigned = createAssignment(assignmentId);
        AssignmentReply reply = createReply(assignmentId, replyId);
        AssignmentFeedback feedback = createFeedback(assignmentId, replyId);

        pool.putAssignment(assignmentAssigned);
        pool.putReply(reply);
        pool.putFeedback(feedback);

        System.out.println(pool.assignmentCount(1) +" " + pool.classCount()+" "+ pool.assignmentId(1, 0));
        System.out.println(pool.reply(pool.assignmentId(1, 0)).getReplyId());
        System.out.println(pool.reply(pool.assignmentId(1, 0)) + " " + pool.feedback(pool.reply(pool.assignmentId(1, 0)).getReplyId()));
    }

    @Test
    public  void test(){

    }















    private Random random = new Random();

    public AssignmentAssigned createAssignment(int id){
        AssignmentAssigned assignmentAssigned = new AssignmentAssigned();
        assignmentAssigned.setAssignmentID(id);
        assignmentAssigned.setTeacherID(1);
        assignmentAssigned.setTeacherName("name");
        assignmentAssigned.setClassID(1);
        assignmentAssigned.setClassName("className");
        assignmentAssigned.setTimestamp(11111);
        assignmentAssigned.setModificationTime(111111);

        assignmentAssigned.setTitle("title");
        assignmentAssigned.setContent("content");
        assignmentAssigned.setImages(new LinkedList<ImageFile>());

        return assignmentAssigned;
    }


    public AssignmentReply createReply(int assignmentId, int replyId){
        AssignmentReply reply = new AssignmentReply();
        reply.setAssignmentID(assignmentId);
        reply.setTeacherID(1);
        reply.setTeacherName("name");
        reply.setClassID(1);
        reply.setClassName("className");
        reply.setTimestamp(11111);
        reply.setModificationTime(111111);

        reply.setReplyId(replyId);
        reply.setStudentId(replyId);
        reply.setStudentName("name");

        reply.setTitle("title");
        reply.setContent("content");
        reply.setImages(new LinkedList<ImageFile>());

        return reply;
    }

    public AssignmentFeedback createFeedback(int assignmentId, int replyID){
        AssignmentFeedback feedback = new AssignmentFeedback();
        feedback.setAssignmentID(assignmentId);
        feedback.setTeacherID(1);
        feedback.setTeacherName("name");
        feedback.setClassID(1);
        feedback.setClassName("className");
        feedback.setTimestamp(11111);
        feedback.setModificationTime(111111);

        feedback.setFeedbackId(random.nextInt(9999));
        feedback.setReplyId(replyID);
        feedback.setContent("content");
        feedback.setScore(99);
        return feedback;
    }
}
