package com.zxzhang.leethub.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
            Log.d(TAG, "onCreate: " + "inzxhznag");
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
            Log.d(TAG, "onCreate: " + mQuestionList.size());
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRvQuestionView.setLayoutManager(layoutManager);
            questionAdapter = new QuestionAdapter(App.getApplication(),mQuestionList);
            mRvQuestionView.setAdapter(questionAdapter);
        }

    }

    private void initView(){
        initToolbar();
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mRvQuestionView = (RecyclerView)findViewById(R.id.rv_question);
    }

    private void initToolbar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    private void loadToMainActivity(){

    }
}
