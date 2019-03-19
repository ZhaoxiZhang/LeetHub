package zhaoxizhang.github.io.leethub.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;



import zhaoxizhang.github.io.leethub.App;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.model.HtmlData;
import zhaoxizhang.github.io.leethub.model.dao.Question;


public class QuestionDetailActivity extends AppCompatActivity{
    private static final String TAG = "QuestionDetailActivity";
    private Toolbar toolbar;
    private TextView mTvTitle;
    private TextView mTvQustionTags;
    private WebView mWvQuestionContent;
    private Button mBtnQuestionShowSolution;
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
            mTvTitle.setText(mQuestion.getTitle());
            mTvQustionTags.setText(mQuestion.getTags());
            String mQuestionContent = mQuestion.getContent();
            String html = HtmlData.QuestionHTMLFirst + mQuestionContent + HtmlData.QuestionHTMLLast;
            mWvQuestionContent.loadDataWithBaseURL("file:///android_asset/", html,"text/html","utf-8",null);
            mBtnQuestionShowSolution.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mQuestion.getArticle_live()){
                        Intent intent = new Intent(App.getApplication(), ArticleActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("questionFrontendID",mQuestion.getFrontend_question_id());
                        bundle.putString("questionTitle", mQuestion.getTitle());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }
            });
        }
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

    private void initView() {
        initToolbar();
        mTvTitle = findViewById(R.id.tv_title);
        mTvQustionTags = findViewById(R.id.tv_question_detial_tags);
        mWvQuestionContent = findViewById(R.id.wv_question_detail_content);
        mBtnQuestionShowSolution = findViewById(R.id.btn_question_detail_solution);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initData() {

    }

}
