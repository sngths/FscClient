package com.tianxing.model.communication.http.api;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by tianxing on 2017/3/31.
 * 子类接口返回Call类型
 */

public interface RetrofitApi extends Api<Call<ResponseBody>>{

}
