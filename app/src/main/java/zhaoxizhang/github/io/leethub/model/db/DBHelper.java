package zhaoxizhang.github.io.leethub.model.db;


import android.database.sqlite.SQLiteDatabase;

import zhaoxizhang.github.io.leethub.App;
import zhaoxizhang.github.io.leethub.model.dao.Article;
import zhaoxizhang.github.io.leethub.model.dao.ArticleDao;
import zhaoxizhang.github.io.leethub.model.dao.DaoMaster;
import zhaoxizhang.github.io.leethub.model.dao.DaoSession;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.dao.QuestionDao;


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

    public static void insertDataToArticleTBL(Article article){
        ArticleDao articleDao = DBHelper.getSingleton().getDaoSession().getArticleDao();
        articleDao.insert(article);
    }

    public static List<Article> queryArticleContent(int id){
        ArticleDao articleDao = DBHelper.getSingleton().getDaoSession().getArticleDao();
        QueryBuilder queryBuilder = articleDao.queryBuilder();
        return queryBuilder.where(ArticleDao.Properties.Frontend_article_id.eq(id)).list();
    }

    //查询某一难度的所有题目
    public static List<Question>queryAllQuestionOfSpecificDifficulty(int difficulty){
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        QueryBuilder queryBuilder = questionDao.queryBuilder();
        return queryBuilder.where(QuestionDao.Properties.Difficulty.eq(difficulty)).orderAsc(QuestionDao.Properties.Frontend_question_id).list();
    }

    public static List<Question>queryAllQuestionOfSpecificTopic(String topicName){
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        QueryBuilder queryBuilder = questionDao.queryBuilder();
        return queryBuilder.where(QuestionDao.Properties.Tags.like(topicName)).orderAsc(QuestionDao.Properties.Frontend_question_id).list();
    }
}
