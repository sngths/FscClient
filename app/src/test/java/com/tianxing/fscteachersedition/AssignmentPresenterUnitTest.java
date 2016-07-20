package com.tianxing.fscteachersedition;

import com.tianxing.model.App;
import com.tianxing.model.AssignmentPool;
import com.tianxing.model.data.Config;
import com.tianxing.presenter.main.AssignmentViewPresenter;

import org.junit.Test;

/**
 * Created by tianxing on 16/7/20.
 */
public class AssignmentPresenterUnitTest {


    @Test
    public void AssignmentPoolTest(){
        App.getInstance().initialize(new Config());
        AssignmentPool pool = App.getInstance().getAssignmentPool();
        assert pool.getClassCount() == 3;
        assert pool.getClassData(0) != null;

    }


    @Test
    public void AssignmentPresenterTest(){
        App.getInstance().initialize(new Config());
        AssignmentViewPresenter presenter = new AssignmentViewPresenter(null);
        assert presenter.getClassTitle(0).equals("一年级一班");
        assert presenter.getClassCount() == 3;
        assert presenter.getClassTitle(0).equals("一年级一班");
        assert presenter.getAssignment(1 , 1) != null;
        assert presenter.getAssignemntCount(0) >= 5;


    }
}
