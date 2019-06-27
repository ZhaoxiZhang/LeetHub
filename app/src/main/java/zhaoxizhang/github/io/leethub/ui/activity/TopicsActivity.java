package zhaoxizhang.github.io.leethub.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import zhaoxizhang.github.io.leethub.App;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.TopicsAdapter;
import zhaoxizhang.github.io.leethub.model.bean.LeetTopicsBean;
import zhaoxizhang.github.io.leethub.util.JsonUtils;

public class TopicsActivity extends AppCompatActivity {
    private static final String TAG = "TopicsActivity";
    private RecyclerView mRvTopicsView;
    private Toolbar toolbar;
    private TextView mTvTitle;
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

    private void initData() {
        String topicsString = JsonUtils.getJson(App.getApplication(), "topics.json");

        LeetTopicsBean topicsBean = JsonUtils.generateObjectFromJson(topicsString, LeetTopicsBean.class);
        mTopicsList = topicsBean.getTopics();

        Comparator<LeetTopicsBean.TopicsBean> topicComparator = new Comparator<LeetTopicsBean.TopicsBean>() {
            @Override
            public int compare(LeetTopicsBean.TopicsBean o1, LeetTopicsBean.TopicsBean o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(mTopicsList, topicComparator);
    }

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
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

        mRvTopicsView = findViewById(R.id.rv_topics);
        mTvTitle = findViewById(R.id.tv_title);

        mTvTitle.setText(getString(R.string.app_name));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
