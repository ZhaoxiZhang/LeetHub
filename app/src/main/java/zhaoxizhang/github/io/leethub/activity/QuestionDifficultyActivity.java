package zhaoxizhang.github.io.leethub.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;




import java.util.ArrayList;
import java.util.List;

import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.FragmentPagerAdapter;
import zhaoxizhang.github.io.leethub.fragment.TabFragment;

public class QuestionDifficultyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView mTvTitle;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_difficulty);

        initView();
        initViewPager();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void initView(){
        initToolbar();
        mTvTitle = findViewById(R.id.tv_title);

        mTvTitle.setText(getString(R.string.app_name));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initViewPager(){
        mTabLayout = findViewById(R.id.tl_question_difficulty_main);
        mViewPager = findViewById(R.id.vp_question_difficulty_main);

        List<String> titles = new ArrayList<>(3);
        titles.add(getString(R.string.tab_question_difficulty_1));
        titles.add(getString(R.string.tab_question_difficulty_2));
        titles.add(getString(R.string.tab_question_difficulty_3));

        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(2)));

        List<Fragment>fragments = new ArrayList<>();
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());
        fragments.add(new TabFragment());

        FragmentPagerAdapter mFragmentAdapter = new FragmentPagerAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(mFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
