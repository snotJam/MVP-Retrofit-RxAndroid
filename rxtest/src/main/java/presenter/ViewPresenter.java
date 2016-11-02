package presenter;

import com.google.gson.Gson;
import com.welfarelottery.newnet.rxtest.ViewActivity;

import net.HomeFragmentApi;
import net.HomeFragmentRequest;
import net.HomeFragmentResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import view.BaseView;

/**
 * Created by NewNet on 2016/11/1.
 */
public class ViewPresenter implements BasePresenter {
    private ViewActivity view;
    public static final String URLHOST = "http://115.182.45.37:8087/bwlcapp-bigdata/handle/";

    public void setView(BaseView view) {
        this.view = (ViewActivity) view;
    }

    @Override
    public void getData() {
        //创建retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLHOST)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        //关联请求接口
        HomeFragmentApi api = retrofit.create(HomeFragmentApi.class);
        //请求参数设置
        HomeFragmentRequest request = new HomeFragmentRequest();
        HomeFragmentRequest.HeadBean head = new HomeFragmentRequest.HeadBean();
        HomeFragmentRequest.MessageBean messageBean = new HomeFragmentRequest.MessageBean();
        request.setHead(head);
        request.setMessage(messageBean);
        Gson gson = new Gson();
        String params = gson.toJson(request);

        //首先获取得到的Observable
        Observable<HomeFragmentResponse> observable = api.getData(params);

        //处理Observable中需要的data1
        observable
                .flatMap(new Func1<HomeFragmentResponse, Observable<String>>() {
                    @Override
                    public Observable<String> call(HomeFragmentResponse homeFragmentResponse) {
                        return Observable.just(homeFragmentResponse.getLotjtdata().get(0).getJttitle());
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        view.setData(s);
                    }
                });
        //处理Observable中需要的data2
//        observable
//                .flatMap(new Func1<HomeFragmentResponse, Observable<HomeFragmentResponse.LdataBean>>() {
//                    @Override
//                    public Observable<HomeFragmentResponse.LdataBean> call(HomeFragmentResponse homeFragmentResponse) {
//                        return Observable.from(homeFragmentResponse.getLdata());
//                    }
//                })
//                .flatMap(new Func1<HomeFragmentResponse.LdataBean, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(HomeFragmentResponse.LdataBean ldataBean) {
//                        return Observable.just(ldataBean.getLimageurl());
//                    }
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        view.addItem(s);
//                    }
//                });

        //处理Observable中需要的data2(第二种方法)
        observable
                .flatMap(new Func1<HomeFragmentResponse, Observable<HomeFragmentResponse.LdataBean>>() {
                    @Override
                    public Observable<HomeFragmentResponse.LdataBean> call(HomeFragmentResponse homeFragmentResponse) {
                        return Observable.from(homeFragmentResponse.getLdata());
                    }
                })
                .flatMap(new Func1<HomeFragmentResponse.LdataBean, Observable<String>>() {
                    @Override
                    public Observable<String> call(HomeFragmentResponse.LdataBean ldataBean) {
                        return Observable.just(ldataBean.getLimageurl());
                    }
                })
                .buffer(20)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        view.setList((ArrayList<String>) strings);
                    }
                });
    }
}
