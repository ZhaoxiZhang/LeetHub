package com.zxzhang.leethub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.zxzhang.leethub.R;
import com.zxzhang.leethub.api.Url;
import com.zxzhang.leethub.model.HtmlData;
import com.zxzhang.leethub.model.dao.Question;

public class QuestionDetailActivity extends AppCompatActivity {
    private static final String TAG = "QuestionDetailActivity";
    private Toolbar toolbar;
    private TextView mTvQuestionTitle;
    private TextView mTvQustionTags;
    private WebView mWvQuestionContent;
    private Intent mIntent;
    private Question mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        initView();

        mIntent = this.getIntent();
        mQuestion = (Question) mIntent.getSerializableExtra("question");

        WebSettings webSettings = mWvQuestionContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能

        if (mQuestion != null) {

            mTvQuestionTitle.setText(mQuestion.getTitle());
            mTvQustionTags.setText(mQuestion.getTags());
            String mQuestionContent = mQuestion.getContent();
            String html = HtmlData.QuestionHTMLFirst + mQuestionContent + HtmlData.QuestionHTMLLast;
            Log.d(TAG, "onCreate: zyzhang \r\n" + html);
            mWvQuestionContent.loadDataWithBaseURL(Url.leetcodeUrl, html,"text/html","utf-8",null);
        }
    }


    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initView() {
        initToolbar();
        mTvQuestionTitle = (TextView) findViewById(R.id.tv_question_detail_title);
        mTvQustionTags = (TextView)findViewById(R.id.tv_question_detial_tags);
        mWvQuestionContent = (WebView) findViewById(R.id.wv_question_detail_content);
    }

    private void initData() {

    }

}
