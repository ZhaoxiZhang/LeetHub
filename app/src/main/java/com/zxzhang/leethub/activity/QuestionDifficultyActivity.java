package com.zxzhang.leethub.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zxzhang.leethub.R;
import com.zxzhang.leethub.adapter.FragmentPagerAdapter;
import com.zxzhang.leethub.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

public class QuestionDifficultyActivity extends AppCompatActivity {
    private Toolbar toolbar;
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
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setTitle("Difficulty");
    }

    private void initView(){
        initToolbar();
    }

    private void initViewPager(){
        mTabLayout = (TabLayout)findViewById(R.id.tl_question_difficulty_main);
        mViewPager = (ViewPager)findViewById(R.id.vp_question_difficulty_main);

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
