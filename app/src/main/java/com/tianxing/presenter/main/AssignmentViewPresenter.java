package com.tianxing.presenter.main;

import com.tianxing.entity.assignment.Assignment;
import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;
import com.tianxing.ui.AssingmentView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/7/15.
 * 提供作业界面数据请求接口
 */
public class AssignmentViewPresenter extends AssignmentPresenter {


    /**
     * presenter持有model的引用
     */
    private AssignmentPool assignmentPool;


    private AssingmentView view;

    public AssignmentViewPresenter(AssingmentView view) {
        this.view = view;
        assignmentPool = App.getInstance().getAssignmentPool();
    }

    /**
     * 取得单个班级作业列表中作业条数
     *
     * @param classID
     */
    @Override
    public Integer getAssignemntCount(int classID) {
        return assignmentPool.getClassData(classID).getAssigmnetsCount();
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
    public Assignment getAssignment(int classID, int position) {
        return assignmentPool.getClassData(classID).getAssignment(position);
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


    /**
     * 请求刷新作业数据
     *
     * @param classID
     */
    @Override
    public void requestAssignment(final int classID) {
        //开始从网络请求数据
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();
                subscriber.onNext(classID);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.immediate())//指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。
                .observeOn(Schedulers.io())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        //请求网络数据
                        try {
                            Thread.currentThread().sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        //在主线程中将保存请求到的数据
                        assignmentPool.getClassData(classID).putAssignment(new Assignment());
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        //请求完成 通知界面更新
                        view.refreshAssignment(classID);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer s) {

                    }
                });

    }
}
