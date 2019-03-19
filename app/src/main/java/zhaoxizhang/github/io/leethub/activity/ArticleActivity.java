package zhaoxizhang.github.io.leethub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;


import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.api.Url;
import zhaoxizhang.github.io.leethub.model.HtmlData;
import zhaoxizhang.github.io.leethub.model.db.DBHelper;


public class ArticleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView mTvTitle;
    private WebView mWvArticleSolution;
    private Intent mIntent;
    private String mArticleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);


        mIntent = this.getIntent();
        int articleFrontendID = mIntent.getIntExtra("questionFrontendID",0);
        mArticleTitle = mIntent.getStringExtra("questionTitle");

        initView();

        String articleContent = DBHelper.queryArticleContent(articleFrontendID).get(0).getContent();
        String articleHTML = HtmlData.ArticleHTMLFirst + articleContent + HtmlData.ArticleHTMLLast;

        WebSettings webSettings = mWvArticleSolution.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能

        mWvArticleSolution.loadDataWithBaseURL(Url.leetcodeUrl, articleHTML,"text/html","utf-8",null);

    }

    private void initData(){

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        initToolbar();
        mWvArticleSolution = findViewById(R.id.wv_article_solution);
        mTvTitle = findViewById(R.id.tv_title);

        mTvTitle.setText(mArticleTitle);
    }

}
