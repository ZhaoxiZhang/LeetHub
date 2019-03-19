package zhaoxizhang.github.io.leethub.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.List;

import zhaoxizhang.github.io.leethub.App;
import zhaoxizhang.github.io.leethub.DownloadTask;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.adapter.DividerItemDecoration;
import zhaoxizhang.github.io.leethub.adapter.QuestionAdapter;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.db.DBHelper;

public class MainActivity extends AppCompatActivity {
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
            mNumberProgressBar = findViewById(R.id.number_progress_bar);

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
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, new ColorDrawable(getResources().getColor(R.color.md_dark_dividers)));
            dividerItemDecoration.setHeight(1);
            mRvQuestionView.addItemDecoration(dividerItemDecoration);
            mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    item.setCheckable(false);
                    mDrawerLayout.closeDrawers();
                    Intent intent = new Intent();

                    switch (item.getItemId()){
                        case R.id.nav_all:
                            break;
                        case R.id.nav_topics:
                            intent.setClass(App.getApplication(), TopicsActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.nav_difficulty:
                            intent.setClass(App.getApplication(), QuestionDifficultyActivity.class);
                            startActivity(intent);
                            break;
                        default:


                    }
                    return true;
                }

            });
        }

    }

    private void initView(){
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mRvQuestionView = findViewById(R.id.rv_question_main);
        mNavView = findViewById(R.id.nav_view);


        mNavView.setItemIconTintList(null);


        initToolbar();
        setActionBarDrawerToggle();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }

    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.background_super_dark));
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

    /*
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarDrawerToggle.syncState();
    }
    */

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void loadToMainActivity(){

    }
}
