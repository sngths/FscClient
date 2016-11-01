package com.tianxing.presenter.main;

import android.util.Log;

import com.tianxing.entity.transfer.receive.AssignmentDownload;
import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;
import com.tianxing.model.communication.HttpClient;
import com.tianxing.ui.AssignmentView;

import java.util.List;

import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/7/15.
 * 提供作业界面数据请求接口
 */
public class AssignmentViewPresenter extends AssignmentPresenter {


    private AssignmentPool assignmentPool;


    private AssignmentView view;
    private HttpClient httpClient;

    public AssignmentViewPresenter(AssignmentView view) {
        this.view = view;
        assignmentPool = App.getInstance().getAssignmentPool();
        httpClient = App.getInstance().getHttpClient();
    }

    /**
     * 取得单个班级作业列表中作业条数
     *
     * @param classPosition
     */
    @Override
    public Integer getAssignemntCount(int classPosition) {
        return assignmentPool.getClassData(classPosition).getAssigmnetsCount();
    }

    /**
     * 取得班级名称
     *
     * @param position
     */
    @Override
    public String getClassTitle(int position) {
        return assignmentPool.getClassData(position).getTitle();
    }

    @Override
    public String getClassTitle(String classID) {
        return null;
    }

    /**
     * 取得对应位置的一条作业数据
     */
    @Override
    public AssignmentDownload getAssignment(int classPosition, int position) {
        return assignmentPool.getClassData(classPosition).getAssignment(position);
    }


    /**
     * 取得对应位置的班级ID
     *
     * @param position
     */
    @Override
    public String getClassID(int position) {
        return assignmentPool.getClassData(position).getClassID();
    }

    /**
     * 取得ListFragment数目
     */
    @Override
    public Integer getClassCount() {
        return assignmentPool.getClassCount();
    }

    @Override
    public Integer getPosition(String classID) {
        return assignmentPool.getClassDataPosition(classID);
    }


    /**
     * 请求刷新作业数据
     *
     * @param classID
     * @param sid     作业中的最大id
     */
    @Override
    public void requestAssignment(final String classID, int sid) {
        httpClient.requestAssignmentList(String.valueOf(classID), String.valueOf(sid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<AssignmentDownload>>>() {

                    private int assignmentCount;//取得的作业条数
                    @Override
                    public void onCompleted() {
                        //请求数据完成 通知界面刷新
                        if (assignmentCount != 0){
                            view.refreshAssignment(classID);
                        }else {
                            view.refreshRelease(classID);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        //请求作业出错 通知界面 取消下拉状态
                        view.refreshRelease(classID);
                    }

                    @Override
                    public void onNext(Response<List<AssignmentDownload>> listResponse) {
                        Log.e("AssignmentViewPresenter", "取得请求的作业数据 条数：" + String.valueOf(listResponse.body().size()));
                        assignmentCount = listResponse.body().size();
                        for (AssignmentDownload assignment : listResponse.body()) {
                            assignmentPool.getClassData(classID).putAssignment(assignment);
                        }
                    }
                });
    }
}
