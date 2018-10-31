package com.zxzhang.leethub.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zxzhang.leethub.App;
import com.zxzhang.leethub.R;
import com.zxzhang.leethub.adapter.TopicsAdapter;
import com.zxzhang.leethub.model.bean.LeetTopicsBean;
import com.zxzhang.leethub.util.JsonUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopicsActivity extends AppCompatActivity {
    private static final String TAG = "TopicsActivity";
    private RecyclerView mRvTopicsView;
    private Toolbar toolbar;
    private List<LeetTopicsBean.TopicsBean> mTopicsList;
    private TopicsAdapter topicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        initData();
        initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(App.getApplication());
        mRvTopicsView.setLayoutManager(layoutManager);
        topicsAdapter = new TopicsAdapter(App.getApplication(), mTopicsList);
        mRvTopicsView.setAdapter(topicsAdapter);
    }

    private void initData(){
        String topicsString = JsonUtil.getJson(App.getApplication(), "topics.json");

        LeetTopicsBean topicsBean = JsonUtil.generateObjectFromJson(topicsString, LeetTopicsBean.class);
        mTopicsList = topicsBean.getTopics();

        Comparator<LeetTopicsBean.TopicsBean>topicComparator = new Comparator<LeetTopicsBean.TopicsBean>() {
            @Override
            public int compare(LeetTopicsBean.TopicsBean o1, LeetTopicsBean.TopicsBean o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(mTopicsList, topicComparator);
    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setTitle("Topics");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        initToolbar();
        mRvTopicsView = (RecyclerView)findViewById(R.id.rv_topics);
    }
}
