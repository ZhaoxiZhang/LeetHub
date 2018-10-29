package com.zxzhang.leethub.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zxzhang.leethub.App;
import com.zxzhang.leethub.R;
import com.zxzhang.leethub.adapter.QuestionAdapter;
import com.zxzhang.leethub.model.dao.Question;
import com.zxzhang.leethub.model.db.DBHelper;

import java.util.List;

public class TabFragment extends Fragment {
    private static final String TAB_POSITION = "tab_position";
    private int tabPosition;
    private RecyclerView mRvTabView;
    private List<Question> mQuestionList;
    private QuestionAdapter questionAdapter;

    public static TabFragment newInstance(int tabPosition){
        TabFragment tabFragment = new TabFragment();

        Bundle args = new Bundle();
        args.putInt(TAB_POSITION, tabPosition);
        tabFragment.setArguments(args);

        return tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabPosition = getArguments().getInt(TAB_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        mRvTabView = view.findViewById(R.id.rv_tab_question_item);
        mRvTabView.setLayoutManager(new LinearLayoutManager(App.getApplication()));

        mQuestionList = DBHelper.queryAllQuestionOfSpecificDifficulty(tabPosition + 1);
        questionAdapter = new QuestionAdapter(App.getApplication(),mQuestionList);
        mRvTabView.setAdapter(questionAdapter);

    }

}
