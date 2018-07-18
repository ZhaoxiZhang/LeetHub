package com.zxzhang.leethub.model.db;


import android.database.sqlite.SQLiteDatabase;

import com.zxzhang.leethub.App;
import com.zxzhang.leethub.model.dao.DaoMaster;
import com.zxzhang.leethub.model.dao.DaoSession;
import com.zxzhang.leethub.model.dao.Question;
import com.zxzhang.leethub.model.dao.QuestionDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DBHelper {
    private static String DB_NAME = "leethub.db";

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static volatile DBHelper dbHelper;

    private DBHelper(){

    }

    public static DBHelper getSingleton(){
        DBHelper result = dbHelper;
        if (result == null){
            synchronized (DBHelper.class){
                result = dbHelper;
                if (result == null){
                    dbHelper = result = new DBHelper();
                }
            }
        }

        return result;
    }

    private void initDB(){
        mHelper = new DaoMaster.DevOpenHelper(App.getApplication(),DB_NAME,null);
        sqLiteDatabase = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(sqLiteDatabase);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession(){
        if (mDaoSession == null){
            initDB();
        }
        return mDaoSession;
    }

    public static void insertDataToQuestionTBL(Question question){
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        questionDao.insert(question);
    }

    public static List<Question>queryAllDataOfQuestion(){
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        QueryBuilder queryBuilder = questionDao.queryBuilder();
        return queryBuilder.orderAsc(QuestionDao.Properties.Frontend_question_id).list();
    }
}
