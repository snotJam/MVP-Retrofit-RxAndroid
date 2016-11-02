package com.welfarelottery.newnet.rxtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import presenter.BasePresenter;
import presenter.ViewPresenter;
import view.BaseView;

/**
 * Created by NewNet on 2016/11/1.
 */
public class ViewActivity extends Activity implements BaseView {
    private TextView tv_btn;
    private TextView show1;
    private TextView show2;
    private ListView lv;
    private ViewPresenter presenter = new ViewPresenter();
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"welcome",Toast.LENGTH_SHORT).show();
        presenter.setView(this);
        setPresenter(presenter);
        tv_btn = (TextView) findViewById(R.id.tv_btn);
        show1 = (TextView) findViewById(R.id.tv_show1);
        show2 = (TextView) findViewById(R.id.tv_show2);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);
        tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getData();
            }
        });
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        this.presenter = (ViewPresenter) presenter;
    }

    public void setData(String show1Text) {
        show1.setText(show1Text);
    }

    public void setList(ArrayList<String> data){
        adapter.addAll(data);
    }

    public void addItem(String item){
        adapter.add(item);
    }

}
