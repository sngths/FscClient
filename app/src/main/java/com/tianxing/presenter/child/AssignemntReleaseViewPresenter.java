package com.tianxing.presenter.child;

import com.tianxing.pojo.transfer.send.AssignmentUpload;
import com.tianxing.deprecated.entity.http.json.ImageFile;
import com.tianxing.deprecated.entity.info.ClassInfo;
import com.tianxing.model.App;
import com.tianxing.deprecated.data.ContactsPool;
import com.tianxing.deprecated.HttpClient;

import java.util.List;

import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tianxing on 16/10/27.
 *
 */

public class AssignemntReleaseViewPresenter implements AssignmentReleasePresenter {

    private HttpClient httpClient;
    private ContactsPool contactsPool;


    public AssignemntReleaseViewPresenter(){
        httpClient = App.getInstance().getHttpClient();
        contactsPool = App.getInstance().getContactsPool();
    }

    @Override
    public List<ClassInfo> getClassList() {
        return contactsPool.getClasses();
    }

    /**
     * 上传一张图片
     *
     * @param imagePath
     */
    @Override
    public Observable<Response<ImageFile>> uploadImage(String imagePath) {
        return httpClient.uploadImage(imagePath).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 上传一组图片
     *
     * @param imageList
     */
    @Override
    public Observable<Response<ImageFile>> uploadImageSet(List<String> imageList) {
        return httpClient.uploadImageSet(imageList).observeOn(AndroidSchedulers.mainThread());
    }

    /***
     * 上传一条作业
     *
     * @param assignment
     */
    @Override
    public Observable<Response<Void>> uploadAssignment(AssignmentUpload assignment) {
        return httpClient.uploadAssignment(assignment).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
