package com.softpo.listviewmultidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.softpo.listviewmultidemo.adapters.MultiAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            if(i%2==0){//左边显示数据
                list.add("左边显示");
            }else {
                list.add("右边显示");
            }
        }

        MultiAdapter adapter = new MultiAdapter(this,list);

        mListView.setAdapter(adapter);

    }

    private void initView() {
        mListView = ((ListView) findViewById(R.id.listView));
    }
}
