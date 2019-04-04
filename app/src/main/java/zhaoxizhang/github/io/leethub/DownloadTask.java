package zhaoxizhang.github.io.leethub;


import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;


import java.util.List;

import zhaoxizhang.github.io.leethub.ui.activity.HomeActivity;
import zhaoxizhang.github.io.leethub.model.bean.QuestionBean;
import zhaoxizhang.github.io.leethub.model.dao.Article;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.db.DBHelper;
import zhaoxizhang.github.io.leethub.util.JsonUtil;

public class DownloadTask extends AsyncTask<Void,Integer,Void> {
    private static final String TAG = "DownloadTask";
    private NumberProgressBar numberProgressBar;

    public DownloadTask(NumberProgressBar numberProgressBar) {
        super();
        this.numberProgressBar = numberProgressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        numberProgressBar.setVisibility(View.VISIBLE);
    }

    /*
    @Override
    protected Void doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .followRedirects(false)
                .followSslRedirects(false)
                .build();
        Request request = new Request.Builder()
                .url("https://leetcode.com/api/problems/algorithms/")
                .build();
        Response response = null;
        String responseData = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        LeetQuestionBean questions = gson.fromJson(responseData, LeetQuestionBean.class);
        int size = questions.getStat_status_pairs().size();

        for (int i = 0; i < size; i++) {
            Question question = new Question();
            Article article = new Article();

            LeetQuestionBean.StatStatusPairsBean questionStatStatus = questions.getStat_status_pairs().get(i);
            LeetQuestionBean.StatStatusPairsBean.StatBean questionStat = questionStatStatus.getStat();
            LeetQuestionBean.StatStatusPairsBean.DifficultyBean difficultyBean = questionStatStatus.getDifficulty();

            question.setFrontend_question_id(questionStat.getFrontend_question_id());
            question.setArticle_live(questionStat.isQuestion__article__live());
            question.setArticle_slug(questionStat.getQuestion__article__slug());
            question.setTitle(questionStat.getQuestion__title());
            question.setTitle_slug(questionStat.getQuestion__title_slug());
            question.setDifficulty(difficultyBean.getLevel());

            article.setFrontend_article_id(questionStat.getFrontend_question_id());

            try {
                //获取得到题目的content
                Connection.Response questionResponse = Jsoup.connect("https://leetcode.com/problems/" + questionStat.getQuestion__title_slug())
                        .method(Connection.Method.GET)
                        .execute();

                String csrftoken = questionResponse.cookie("csrftoken");
                String __cfduid = questionResponse.cookie("__cfduid");
                //Log.d(TAG, "doInBackground: zyzhang " + csrftoken);
                //Log.d(TAG, "doInBackground: zyzhang " + __cfduid);

                String postBody = "query{question(titleSlug:\"" + questionStat.getQuestion__title_slug() + "\") {content topicTags {name}}}";
                Request graphqlRequest = new Request.Builder()
                        .addHeader("Content-Type","application/graphql")
                        .addHeader("Referer","https://leetcode.com/problems/" + questionStat.getQuestion__title_slug())
                        .addHeader("Cookie","__cfduid=" + __cfduid + ";" + "csrftoken=" + csrftoken)
                        .addHeader("x-csrftoken",csrftoken)
                        .url(URL.graphqlUrl)
                        .post(RequestBody.create(MediaType.parse("application/graphql; charset=utf-8"),postBody))
                        .build();

                Response graphqlResponse = client.newCall(graphqlRequest).execute();

                String graphqlResponseData = graphqlResponse.body().string();

                LeetGraphqlQuestionBean leetGraphqlQuestionBean = gson.fromJson(graphqlResponseData,LeetGraphqlQuestionBean.class);

                question.setContent(leetGraphqlQuestionBean.getData().getQuestionGraphql().getContent());
                List<LeetGraphqlQuestionBean.DataBean.QuestionGraphqlBean.TopicTagsBean> topicTagsBeanList = leetGraphqlQuestionBean.getData().getQuestionGraphql().getTopicTags();
                StringBuilder tags = new StringBuilder();
                for (int j = 0;j < topicTagsBeanList.size();j++){
                    if (j != topicTagsBeanList.size() - 1){
                        tags.append(topicTagsBeanList.get(j).getName() + ", ");
                    }else{
                        tags.append(topicTagsBeanList.get(j).getName());
                    }
                }

                question.setTags(tags.toString());

                Document doc = Jsoup.connect("https://leetcode.com/articles/" + questionStat.getQuestion__article__slug()).get();
                String articleBody = doc.getElementsByClass("article-body").outerHtml();
                article.setContent(articleBody);
//                String description = doc.select("meta[name=description]").first().attr("content");
//                question.setDescription(description);
            } catch (IOException e) {
                e.printStackTrace();
            }

            DBHelper.insertDataToQuestionTBL(question);
            DBHelper.insertDataToArticleTBL(article);
            publishProgress((int)((i / (float)size) * 100));

            Log.d(TAG, "doInBackground: " + i);
        }


        return null;
    }
    */

    @Override
    protected Void doInBackground(Void... params) {

        String questionDetails = JsonUtil.getJson(App.getApplication(), "questions.json");
        Log.d(TAG, "doInBackground zyzhang: hello world" + questionDetails);
        List<QuestionBean>questionsList = JsonUtil.generateObjectFromJsonArray(questionDetails, QuestionBean[].class);

        int size = questionsList.size();
        for (int i = 0; i < size; i++){
            QuestionBean questionBean = questionsList.get(i);
            Question question = new Question();
            Article article = new Article();

            question.setQuestion_id(questionBean.getQuestion_id());
            question.setFrontend_question_id(questionBean.getFrontend_question_id());
            question.setArticle_live(questionBean.isArticle_live());
            question.setArticle_slug(questionBean.getArticle_slug());
            question.setTitle(questionBean.getTitle());
            question.setTitle_slug(questionBean.getTitle_slug());
            question.setDifficulty(questionBean.getDifficulty());
            question.setTags(questionBean.getTags());
            question.setContent(questionBean.getContent());


            article.setFrontend_article_id(questionBean.getFrontend_article_id());
            article.setContent(questionBean.getArticle_content());

            DBHelper.insertDataToQuestionTBL(question);
            DBHelper.insertDataToArticleTBL(article);
            publishProgress((int)((i / (float)size) * 100));
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        numberProgressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        numberProgressBar.setVisibility(View.GONE);
        Intent intent = new Intent(App.getApplication(), HomeActivity.class);
        App.getApplication().startActivity(intent);
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }
}