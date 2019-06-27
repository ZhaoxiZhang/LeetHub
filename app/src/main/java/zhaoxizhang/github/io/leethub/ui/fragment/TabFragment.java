package zhaoxizhang.github.io.leethub.ui.fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import zhaoxizhang.github.io.leethub.App;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.QuestionAdapter;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.db.DBHelper;
import zhaoxizhang.github.io.leethub.ui.DividerItemDecoration;

public class TabFragment extends Fragment {
    private static final String TAB_POSITION = "tab_position";
    private int tabPosition;
    private RecyclerView mRvTabView;
    private List<Question> mQuestionList;
    private QuestionAdapter questionAdapter;

    public static TabFragment newInstance(int tabPosition) {
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

    private void initView(View view) {
        mRvTabView = view.findViewById(R.id.rv_tab_question_item);
        mRvTabView.setLayoutManager(new LinearLayoutManager(App.getApplication()));

        mQuestionList = DBHelper.queryAllQuestionOfSpecificDifficulty(tabPosition + 1);
        questionAdapter = new QuestionAdapter(getActivity());
        questionAdapter.setQuestionList(mQuestionList);
        mRvTabView.setAdapter(questionAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST, new ColorDrawable(getResources().getColor(R.color.md_dark_dividers)));
        dividerItemDecoration.setHeight(1);
        mRvTabView.addItemDecoration(dividerItemDecoration);
    }

}
