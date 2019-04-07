package zhaoxizhang.github.io.leethub.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.DiscussAdapter;
import zhaoxizhang.github.io.leethub.api.URL;
import zhaoxizhang.github.io.leethub.api.graphql.GraphQL;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.graphql.Discuss;
import zhaoxizhang.github.io.leethub.ui.EndlessRecyclerOnScrollListener;
import zhaoxizhang.github.io.leethub.util.JsonUtil;

import static android.support.constraint.Constraints.TAG;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class ProblemDiscussFragment extends Fragment {
    @BindView(R.id.rv_problem_discuss) RecyclerView mRvProblemDiscuss;
    @BindView(android.R.id.empty) ProgressBar mPbLoading;

    private Intent mIntent;
    private Question mQuestion;
    private DiscussAdapter discussAdapter;

    private EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_problem_discuss, container, false);
        ButterKnife.bind(this, view);

        mIntent = getActivity().getIntent();
        mQuestion = (Question) mIntent.getSerializableExtra("question");

        discussAdapter = new DiscussAdapter(getContext(), new ArrayList<Discuss.DataBean.QuestionTopicsListBean.EdgesBean>(0));
        discussAdapter.setQuestion(mQuestion);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvProblemDiscuss.setLayoutManager(layoutManager);
        mRvProblemDiscuss.setAdapter(discussAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), VERTICAL);
        mRvProblemDiscuss.addItemDecoration(dividerItemDecoration);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                discussAdapter.setDataLoading(true);
                discussAdapter.setShowLoadingMore(true);
                loadData(current_page);

            }
        };
        mRvProblemDiscuss.addOnScrollListener(endlessRecyclerOnScrollListener);
        loadData(0);
        checkEmptyState();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        endlessRecyclerOnScrollListener.reset(0, true);
    }

    private void loadData(int page) {
        OkHttpClient okHttpClient = new OkHttpClient();
        String postBody = String.format(GraphQL.DISCUSS_FORM, "most_votes", page * 15, 15, mQuestion.getQuestion_id());
        Log.d(TAG, "loadData: " + postBody);
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
                final Discuss discuss = JsonUtil.generateObjectFromJson(response.body().string(), Discuss.class);
                discussAdapter.setDataLoading(false);
                discussAdapter.setShowLoadingMore(false);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        discussAdapter.insertDiscuss(discuss.getData().getQuestionTopicsList().getEdges());
                        checkEmptyState();
                    }
                });
            }
        });
    }

    private void checkEmptyState(){
        if (discussAdapter.getDataItemCount() == 0){
            mPbLoading.setVisibility(View.VISIBLE);
        }else{
            mPbLoading.setVisibility(View.GONE);
        }
    }
}
