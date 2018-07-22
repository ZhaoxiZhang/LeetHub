package com.zxzhang.leethub.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.zxzhang.leethub.App;
import com.zxzhang.leethub.DownloadTask;
import com.zxzhang.leethub.R;
import com.zxzhang.leethub.adapter.QuestionAdapter;
import com.zxzhang.leethub.model.dao.Question;
import com.zxzhang.leethub.model.db.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private NumberProgressBar mNumberProgressBar;
    private NavigationView mNavView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private DownloadTask downloadTask;

    private RecyclerView mRvQuestionView;
    private QuestionAdapter questionAdapter;
    private List<Question>mQuestionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getSharedPreferences("data",MODE_PRIVATE);
        editor = pref.edit();

        if (pref.getBoolean("first_launch",true)){
            setContentView(R.layout.activity_main_loading);
            mNumberProgressBar = (NumberProgressBar)findViewById(R.id.number_progress_bar);

            downloadTask = new DownloadTask(mNumberProgressBar);
            downloadTask.execute();

            editor.putBoolean("first_launch",false);
            editor.apply();

        }else{
            setContentView(R.layout.activity_main);

            initView();

            mQuestionList = DBHelper.queryAllDataOfQuestion();

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRvQuestionView.setLayoutManager(layoutManager);
            questionAdapter = new QuestionAdapter(App.getApplication(),mQuestionList);
            mRvQuestionView.setAdapter(questionAdapter);
            mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    item.setCheckable(false);
                    mDrawerLayout.closeDrawers();
                    switch (item.getItemId()){

                        default:


                    }

                    return true;
                }

            });
        }

    }

    private void initView(){
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mRvQuestionView = (RecyclerView)findViewById(R.id.rv_question);
        mNavView = (NavigationView)findViewById(R.id.nav_view);
        initToolbar();
        setActionBarDrawerToggle();
    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setActionBarDrawerToggle(){
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void loadToMainActivity(){

    }
}
