package zhaoxizhang.github.io.leethub.model.db;


import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import zhaoxizhang.github.io.leethub.App;
import zhaoxizhang.github.io.leethub.model.dao.Article;
import zhaoxizhang.github.io.leethub.model.dao.ArticleDao;
import zhaoxizhang.github.io.leethub.model.dao.DaoMaster;
import zhaoxizhang.github.io.leethub.model.dao.DaoSession;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.dao.QuestionDao;

public class DBHelper {
    private final String DB_NAME = "leethub.db";

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private static volatile DBHelper dbHelper;

    private DBHelper() {

    }

    public static DBHelper getSingleton() {
        DBHelper result = dbHelper;
        if (result == null) {
            synchronized (DBHelper.class) {
                result = dbHelper;
                if (result == null) {
                    dbHelper = result = new DBHelper();
                }
            }
        }

        return result;
    }

    private void initDB() {
        mHelper = new DaoMaster.DevOpenHelper(App.getApplication(), DB_NAME, null);
        sqLiteDatabase = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(sqLiteDatabase);
        mDaoSession = mDaoMaster.newSession();
    }

    private DaoSession getDaoSession() {
        if (mDaoSession == null) {
            initDB();
        }
        return mDaoSession;
    }

    // --------insert--------

    public static void insertDataToQuestionTBL(Question question) {
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        questionDao.insert(question);
    }

    public static void insertDataToArticleTBL(Article article) {
        ArticleDao articleDao = DBHelper.getSingleton().getDaoSession().getArticleDao();
        articleDao.insert(article);
    }

    // --------query--------

    public static List<Question> queryAllDataOfQuestion() {
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        QueryBuilder<Question> queryBuilder = questionDao.queryBuilder();
        return queryBuilder.orderAsc(QuestionDao.Properties.Frontend_question_id).list();
    }

    // query questions of specific difficulty
    public static List<Question> queryAllQuestionOfSpecificDifficulty(int difficulty) {
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        QueryBuilder<Question> queryBuilder = questionDao.queryBuilder();
        return queryBuilder.where(QuestionDao.Properties.Difficulty.eq(difficulty)).orderAsc(QuestionDao.Properties.Frontend_question_id).list();
    }

    public static List<Question> queryAllQuestionOfSpecificTopic(String topicName) {
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();
        QueryBuilder<Question> queryBuilder = questionDao.queryBuilder();
        return queryBuilder.where(QuestionDao.Properties.Tags.like(topicName)).orderAsc(QuestionDao.Properties.Frontend_question_id).list();
    }

    public static List<Question> queryQuestions(String query) {
        HashSet<Question> set = new HashSet<>();
        QuestionDao questionDao = DBHelper.getSingleton().getDaoSession().getQuestionDao();

        List<String> queryKeyWords = queryKeyWords(query);

        for (String keyWord : queryKeyWords) {
            QueryBuilder<Question> queryBuilder = questionDao.queryBuilder();
            List<Question> list = queryBuilder.whereOr(QuestionDao.Properties.Frontend_question_id.eq(keyWord), QuestionDao.Properties.Title.like("%" + keyWord + "%")).list();
            set.addAll(list);
        }

        return new ArrayList<>(set);
    }

    public static List<Article> queryArticleContent(int id) {
        ArticleDao articleDao = DBHelper.getSingleton().getDaoSession().getArticleDao();
        QueryBuilder<Article> queryBuilder = articleDao.queryBuilder();
        return queryBuilder.where(ArticleDao.Properties.Frontend_article_id.eq(id)).list();
    }

    // divide query sentence to key words by space
    private static List<String> queryKeyWords(String query) {
        List<String> queryKeyWords = new ArrayList<>();

        if (query == null) return queryKeyWords;

        int size = query.length();
        int beginIndex = 0;
        int lastIndex = 0;
        for (; lastIndex < size; ) {
            while (lastIndex < size && query.charAt(lastIndex) != ' ') {
                lastIndex++;
            }
            queryKeyWords.add(query.substring(beginIndex, lastIndex));

            lastIndex++;
            beginIndex = lastIndex;
        }

        return queryKeyWords;
    }
}
