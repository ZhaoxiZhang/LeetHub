package zhaoxizhang.github.io.leethub.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.api.URL;
import zhaoxizhang.github.io.leethub.model.HtmlData;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.db.DBHelper;


public class ProblemSolutionFragment extends Fragment {
    @BindView(R.id.srl_problem_solution_container) SwipeRefreshLayout mSrlProblemSolutionContainer;
    @BindView(R.id.wv_problem_solution) WebView mWvArticleSolution;
    @Nullable @BindView(R.id.no_solution) TextView mTvNoSolution;

    private Intent mIntent;
    private Question mQuestion;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_problem_solution, container, false);
        ButterKnife.bind(this, view);

        mIntent = getActivity().getIntent();
        mQuestion = (Question) mIntent.getSerializableExtra("question");

        boolean articleLive = mQuestion.getArticle_live();
        if (articleLive){
            String articleContent = DBHelper.queryArticleContent(mQuestion.getFrontend_question_id()).get(0).getContent();
            final String articleHTML = HtmlData.ArticleHTMLFirst + articleContent + HtmlData.ArticleHTMLLast;


            WebSettings webSettings = mWvArticleSolution.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
            webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
            webSettings.setAppCacheEnabled(true);   //开启 Application Caches 功能

            mSrlProblemSolutionContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mWvArticleSolution.loadDataWithBaseURL(URL.leetcodeUrl, articleHTML, "text/html", "utf-8", null);
                }
            });
            mSrlProblemSolutionContainer.post(new Runnable() {
                @Override
                public void run() {
                    mSrlProblemSolutionContainer.setRefreshing(true);
                    mWvArticleSolution.loadDataWithBaseURL(URL.leetcodeUrl, articleHTML, "text/html", "utf-8", null);
                }
            });

            mWvArticleSolution.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    if (newProgress == 100){
                        mSrlProblemSolutionContainer.setRefreshing(false);
                    }else if (!mSrlProblemSolutionContainer.isRefreshing()){
                        mSrlProblemSolutionContainer.setRefreshing(true);
                    }
                }
            });

            mWvArticleSolution.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mWvArticleSolution.getParent().requestDisallowInterceptTouchEvent(true);
                    int x = (int) event.getRawX();
                    int y = (int) event.getRawY();
                    int lastX = 0;
                    int lastY = 0;
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            lastX = x;
                            lastY = y;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            int deltaY = y - lastY;
                            int deltaX = x - lastX;
                            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                                mWvArticleSolution.getParent().requestDisallowInterceptTouchEvent(false);
                            }
                        default:
                            break;
                    }
                    return false;
                }
            });
        }else{
            if (mTvNoSolution == null){
                final ViewStub stub = view.findViewById(R.id.stub_no_solution);
                mTvNoSolution = (TextView)stub.inflate();
            }
        }

        return view;
    }

}
