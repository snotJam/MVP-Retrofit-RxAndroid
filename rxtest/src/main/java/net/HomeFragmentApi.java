package net;


import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by NewNet on 2016/8/4.
 */
public interface HomeFragmentApi {
    @POST("indexData")
    Observable<HomeFragmentResponse> getData(@Query("requestFormData") String request);
}
