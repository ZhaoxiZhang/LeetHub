package zhaoxizhang.github.io.leethub.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;




import java.util.List;

import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.QuestionAdapter;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.db.DBHelper;

public class QuestionTopicActivity extends AppCompatActivity {
    private RecyclerView mRvQuestionTopicView;
    private Toolbar toolbar;
    private TextView mTvTitle;
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
        mRvQuestionTopicView =  findViewById(R.id.rv_question_topic);
        mTvTitle = findViewById(R.id.tv_title);

        mTvTitle.setText(mTopicName);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initData(){
        mIntent = this.getIntent();
        mTopicName = (String) mIntent.getSerializableExtra("topicName");
        mQuestionList = DBHelper.queryAllQuestionOfSpecificTopic(mTopicName);
    }
}
