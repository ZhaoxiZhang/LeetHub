package com.zxzhang.leethub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zxzhang.leethub.R;
import com.zxzhang.leethub.api.Url;
import com.zxzhang.leethub.model.HtmlData;
import com.zxzhang.leethub.model.db.DBHelper;

public class ArticleActivity extends AppCompatActivity {
    private static final String TAG = "ArticleActivity";
    private Toolbar toolbar;
    private WebView mWvArticleSolution;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        initView();

        mIntent = this.getIntent();
        int articleFrontendID = mIntent.getIntExtra("questionFrontendID",0);
        String articleContent = DBHelper.queryArticleContent(articleFrontendID).get(0).getContent();
        Log.d(TAG, "onCreate: zyzhang \r\n" + articleContent);
        String articleHTML = HtmlData.ArticleHTMLFirst + articleContent + HtmlData.ArticleHTMLLast;

        WebSettings webSettings = mWvArticleSolution.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能

        mWvArticleSolution.loadDataWithBaseURL(Url.leetcodeUrl, articleHTML,"text/html","utf-8",null);

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initView(){
        initToolbar();
        mWvArticleSolution = (WebView)findViewById(R.id.wv_article_solution);
    }
}
