package zhaoxizhang.github.io.leethub.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.model.HtmlData;
import zhaoxizhang.github.io.leethub.model.dao.Question;

public class ProblemDescriptionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @BindView(R.id.tv_problem_detial_tags) TextView mTvProblemTags;
    @BindView(R.id.wv_problem_detail_content) WebView mWvProblemContent;

    private Activity activity;
    private AppCompatActivity mAppCompatActivity;
    private Intent mIntent;
    private Question mQuestion;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProblemDescriptionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProblemDescriptionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProblemDescriptionFragment newInstance(String param1, String param2) {
        ProblemDescriptionFragment fragment = new ProblemDescriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problem_description, container, false);
        ButterKnife.bind(this, view);

        mIntent = getActivity().getIntent();
        mQuestion = (Question) mIntent.getSerializableExtra("question");

        WebSettings webSettings = mWvProblemContent.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能

        if (mQuestion != null) {
            mTvProblemTags.setText(mQuestion.getTags());
            String mQuestionContent = mQuestion.getContent();
            String html = HtmlData.QuestionHTMLFirst + mQuestionContent + HtmlData.QuestionHTMLLast;
            mWvProblemContent.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null);

            mWvProblemContent.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mWvProblemContent.getParent().requestDisallowInterceptTouchEvent(true);
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
                            if (Math.abs(deltaX) > Math.abs(deltaY)){
                                mWvProblemContent.getParent().requestDisallowInterceptTouchEvent(false);
                            }
                        default:
                            break;
                    }
                    return false;
                }
            });


        }

        return view;
    }

}
