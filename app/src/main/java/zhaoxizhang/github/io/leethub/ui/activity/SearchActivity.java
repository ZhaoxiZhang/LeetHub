package zhaoxizhang.github.io.leethub.ui.activity;

import android.app.SearchManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhaoxizhang.github.io.leethub.R;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_background) View searchBackground;
    @BindView(R.id.search_view) SearchView searchView;
    @BindView(R.id.search_toolbar) ViewGroup searchToolbar;
    @BindView(R.id.searchback) ImageButton searchBack;
    @BindView(R.id.results_container) ViewGroup resultsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setupSearchView();
    }

    private void setupSearchView(){
        SearchManager searchManager = (SearchManager)getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @OnClick({R.id.scrim, R.id.searchback})
    protected void dismiss(){
        searchBack.setBackground(null);
        finishAfterTransition();
    }
}
