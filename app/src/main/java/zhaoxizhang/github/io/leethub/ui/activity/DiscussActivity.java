package zhaoxizhang.github.io.leethub.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ru.noties.markwon.Markwon;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.api.URL;
import zhaoxizhang.github.io.leethub.api.graphql.GraphQL;
import zhaoxizhang.github.io.leethub.model.graphql.Discuss;
import zhaoxizhang.github.io.leethub.model.graphql.DiscussItem;
import zhaoxizhang.github.io.leethub.util.JsonUtils;

public class DiscussActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.civ_discuss_avatar)
    CircleImageView mCivAvatar;
    @BindView(R.id.tv_discuss_author_name)
    TextView mTvAuthorName;
    @BindView(R.id.tv_discuss_update_date)
    TextView mTvUpdateDate;
    @BindView(R.id.tv_discuss_last_edit)
    TextView mTvLastEdit;
    @BindView(R.id.tv_discuss_topic_title)
    TextView mTvTopicTitle;
    @BindView(R.id.tv_discuss_topic_content)
    TextView mTvTopicContent;
    @BindView(android.R.id.empty)
    ProgressBar mPbLoading;
    @BindView(R.id.discuss_divider)
    View discussDivider;

    private Intent mIntent;
    private Discuss.DataBean.QuestionTopicsListBean.EdgesBean discuss;
    private Markwon markwon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discuss);
        ButterKnife.bind(this);

        mIntent = getIntent();
        discuss = (Discuss.DataBean.QuestionTopicsListBean.EdgesBean) mIntent.getSerializableExtra("discuss");
        int topicId = discuss.getNode().getId();
        markwon = Markwon.create(this);

        initView();

        loadData(topicId);
    }

    private void initView() {
        mPbLoading.setVisibility(View.VISIBLE);
        mTvLastEdit.setVisibility(View.INVISIBLE);
        discussDivider.setVisibility(View.INVISIBLE);

        initToolbar();

        String title = mIntent.getStringExtra("title");
        tvTitle.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initToolbar() {
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

    private void loadData(int topicId) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String postBody = String.format(GraphQL.DISCUSS_ITEM_FORM, topicId);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), postBody);
        Headers headers = new Headers.Builder()
                .add("Content-Type", "application/json")
                .build();
        Request request = new Request.Builder()
                .headers(headers)
                .post(requestBody)
                .url(URL.graphqlUrl)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final DiscussItem discussItem = JsonUtils.generateObjectFromJson(response.body().string(), DiscussItem.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPbLoading.setVisibility(View.GONE);
                        mTvLastEdit.setVisibility(View.VISIBLE);
                        discussDivider.setVisibility(View.VISIBLE);

                        Glide.with(getApplicationContext())
                                .load(discussItem.getData().getTopic().getPost().getAuthor().getProfile().getUserAvatar())
                                .into(mCivAvatar);
                        mTvAuthorName.setText(discussItem.getData().getTopic().getPost().getAuthor().getUsername());

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String time = format.format(discussItem.getData().getTopic().getPost().getUpdationDate() * 1000L);
                        Date date = null;
                        try {
                            date = format.parse(time);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Locale englishUS = Locale.US;
                        String dateStr = String.format(englishUS, "%tB %<te,  %<tY  %<tT %<Tp%n", date);
                        mTvUpdateDate.setText(dateStr);

                        mTvTopicTitle.setText(discussItem.getData().getTopic().getTitle());

                        String content = discussItem.getData().getTopic().getPost().getContent();
                        content = content.replace("\\n", "\n");
                        content = content.replace("\\t", "\t");
                        markwon.setMarkdown(mTvTopicContent, content);
                    }
                });
            }
        });
    }
}
