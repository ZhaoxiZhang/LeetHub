package com.zxzhang.leethub.activity;

import android.content.Intent;
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
import com.zxzhang.leethub.adapter.QuestionAdapter;
import com.zxzhang.leethub.model.dao.Question;
import com.zxzhang.leethub.model.db.DBHelper;

import java.util.List;

public class QuestionTopicActivity extends AppCompatActivity {
    private static final String TAG = "QuestionTopicActivity";
    private RecyclerView mRvQuestionTopicView;
    private Toolbar toolbar;
    private List<Question>mQuestionList;
    private Intent mIntent;
    private String mTopicName;
    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_topic);

        initData();
        initView();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvQuestionTopicView.setLayoutManager(layoutManager);
        questionAdapter = new QuestionAdapter(this, mQuestionList);
        mRvQuestionTopicView.setAdapter(questionAdapter);
    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setTitle(mTopicName);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        initToolbar();
        mRvQuestionTopicView = (RecyclerView)findViewById(R.id.rv_question_topic);
    }

    private void initData(){
        mIntent = this.getIntent();
        mTopicName = (String) mIntent.getSerializableExtra("topicName");
        mQuestionList = DBHelper.queryAllQuestionOfSpecificTopic(mTopicName);
    }
}
