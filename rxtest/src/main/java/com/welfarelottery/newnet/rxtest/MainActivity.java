package com.welfarelottery.newnet.rxtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import net.HomeFragmentApi;
import net.HomeFragmentRequest;
import net.HomeFragmentResponse;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public static final String URLHOST= "http://115.182.45.37:8087/bwlcapp-bigdata/handle/";
    private static final String TAG = "MainActivity";
    private TextView tv_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_btn = (TextView) findViewById(R.id.tv_btn);

        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getData();
                toActivity();
            }
        });

    }

    /**
     * 获取数据并展示
     */
    private void getData() {
        //retrofit构建
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

        //开始请求
        api.getData(params)     //返回的是Observable
                .map(new Func1<HomeFragmentResponse, String>() {
                    @Override
                    public String call(HomeFragmentResponse homeFragmentResponse) {
                        return homeFragmentResponse.getRcode()+"";
                    }
                })
                .subscribeOn(Schedulers.newThread())    //getData方法所在进程
                .observeOn(AndroidSchedulers.mainThread())      //主线程
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String string) {
                        tv_btn.setText(string);
                    }
                });
    }

    public void toActivity(){
        Intent intent = new Intent(this, ViewActivity.class);
        startActivity(intent);
    }

}
