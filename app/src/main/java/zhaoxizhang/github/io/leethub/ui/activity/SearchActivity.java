package zhaoxizhang.github.io.leethub.ui.activity;

import android.app.SearchManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.QuestionAdapter;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.db.DBHelper;
import zhaoxizhang.github.io.leethub.ui.DividerItemDecoration;
import zhaoxizhang.github.io.leethub.util.ImeUtils;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_background)
    View searchBackground;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.search_toolbar)
    ViewGroup searchToolbar;
    @BindView(R.id.searchback)
    ImageButton searchBack;
    @BindView(R.id.results_container)
    ViewGroup resultsContainer;
    @BindView(android.R.id.empty)
    ProgressBar progress;
    @BindView(R.id.rv_search_results)
    RecyclerView rvSearchResults;
    @BindView(R.id.results_scrim)
    View resultScrim;
    private TextView noResults;
    private QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setupSearchView();

        questionAdapter = new QuestionAdapter(this);
        rvSearchResults.setAdapter(questionAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvSearchResults.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, new ColorDrawable(getResources().getColor(R.color.md_dark_dividers)));
        dividerItemDecoration.setHeight(1);
        rvSearchResults.addItemDecoration(dividerItemDecoration);
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setImeOptions(searchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchFor(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    clearResults();
                }
                return true;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @OnClick({R.id.scrim, R.id.searchback})
    protected void dismiss() {
        searchBack.setBackground(null);
        finishAfterTransition();
    }

    void searchFor(String query) {
        clearResults();
        progress.setVisibility(View.VISIBLE);
        ImeUtils.hideIme(searchView);
        searchView.clearFocus();

        List<Question> questionList = DBHelper.queryQuestions(query);
        if (questionList.isEmpty()) {
            progress.setVisibility(View.GONE);
            setNoResultsVisibility(View.VISIBLE);
        } else {
            if (rvSearchResults.getVisibility() != View.VISIBLE) {
                progress.setVisibility(View.GONE);
                rvSearchResults.setVisibility(View.VISIBLE);
            }
            questionAdapter.setQuestionList(questionList);
        }
    }

    void clearResults() {
        questionAdapter.clear();
        rvSearchResults.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        setNoResultsVisibility(View.GONE);
    }

    void setNoResultsVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            if (noResults == null) {
                noResults = (TextView) ((ViewStub)
                        findViewById(R.id.stub_no_search_results)).inflate();
                noResults.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchView.setQuery("", false);
                        searchView.requestFocus();
                        ImeUtils.showIme(searchView);
                    }
                });
            }
            String message = String.format(getString(R.string.no_search_results), searchView.getQuery().toString());
            noResults.setText(message);
        }
        if (noResults != null) {
            noResults.setVisibility(visibility);
        }
    }
}
