package com.tianxing.model.communication;

import com.tianxing.entity.assignment.Assignment;

import java.util.List;

/**
 * Created by tianxing on 16/7/5.
 */
public interface FscHttpClient {

    /**
     * 请求班级作业数据
     * */
    List<Assignment> requestAssignment(String classid);
}
