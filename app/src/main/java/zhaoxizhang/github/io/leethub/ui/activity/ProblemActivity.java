package zhaoxizhang.github.io.leethub.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.FragmentPagerAdapter;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.ui.fragment.ProblemDescriptionFragment;
import zhaoxizhang.github.io.leethub.ui.fragment.ProblemDiscussFragment;
import zhaoxizhang.github.io.leethub.ui.fragment.ProblemSolutionFragment;
import zhaoxizhang.github.io.leethub.ui.fragment.ProblemSubmissionsFragment;

public class ProblemActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.vp_problem)
    ViewPager mVpProblem;
    @BindView(R.id.bnv_problem)
    BottomNavigationView mBnvProblem;

    private Intent mIntent;
    private Question mQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        ButterKnife.bind(this);

        initView();
        initViewPager();

        mIntent = this.getIntent();
        mQuestion = (Question) mIntent.getSerializableExtra("question");

        if (mQuestion != null) {
            mTvTitle.setText(mQuestion.getTitle());
        }

        mBnvProblem.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.description:
                    mVpProblem.setCurrentItem(0);
                    return true;
                case R.id.solution:
                    mVpProblem.setCurrentItem(1);
                    return true;
                case R.id.discuss:
                    mVpProblem.setCurrentItem(2);
                    return true;
                case R.id.submissions:
                    mVpProblem.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private void initView() {
        initToolbar();

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

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ProblemDescriptionFragment());
        fragments.add(new ProblemSolutionFragment());
        fragments.add(new ProblemDiscussFragment());
        fragments.add(new ProblemSubmissionsFragment());

        FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments);
        mVpProblem.setAdapter(mFragmentPagerAdapter);

        mVpProblem.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                mBnvProblem.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
