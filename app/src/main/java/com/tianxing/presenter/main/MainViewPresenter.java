package com.tianxing.presenter.main;

import com.tianxing.model.App;
import com.tianxing.presenter.MainPresenter;

/**
 * Created by tianxing on 16/8/19.
 */
public class MainViewPresenter implements MainPresenter {

    public MainViewPresenter(){
        App.getInstance().getXmppClient().login("user1", "123456");
    }

    @Override
    public void destroy() {

    }
}
