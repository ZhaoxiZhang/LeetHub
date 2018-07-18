package com.zxzhang.leethub;


import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.google.gson.Gson;
import com.zxzhang.leethub.activity.MainActivity;
import com.zxzhang.leethub.api.Url;
import com.zxzhang.leethub.model.bean.LeetGraphqlQuestionBean;
import com.zxzhang.leethub.model.bean.LeetQuestionBean;
import com.zxzhang.leethub.model.dao.Question;
import com.zxzhang.leethub.model.db.DBHelper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        String responseDate = null;
        try {
            response = client.newCall(request).execute();
            responseDate = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        LeetQuestionBean questions = gson.fromJson(responseDate, LeetQuestionBean.class);
        int size = questions.getStat_status_pairs().size();

        Document doc = null;
        for (int i = 0; i < size; i++) {
            Question question = new Question();

            LeetQuestionBean.StatStatusPairsBean questionStatStatus = questions.getStat_status_pairs().get(i);
            LeetQuestionBean.StatStatusPairsBean.StatBean questionStat = questionStatStatus.getStat();
            LeetQuestionBean.StatStatusPairsBean.DifficultyBean difficultyBean = questionStatStatus.getDifficulty();

            question.setFrontend_question_id(questionStat.getFrontend_question_id());
            question.setArticle_live(questionStat.isQuestion__article__live());
            question.setArticle_slug(questionStat.getQuestion__article__slug());
            question.setTitle(questionStat.getQuestion__title());
            question.setTitle_slug(questionStat.getQuestion__title_slug());
            question.setDifficulty(difficultyBean.getLevel());


            try {
                //获取得到题目的content
                Connection.Response questionResponse = Jsoup.connect("https://leetcode.com/problems/" + questionStat.getQuestion__title_slug())
                        .method(Connection.Method.GET)
                        .execute();

                String csrftoken = questionResponse.cookie("csrftoken");
                String __cfduid = questionResponse.cookie("__cfduid");
                //Log.d(TAG, "doInBackground: zyzhang " + csrftoken);
                //Log.d(TAG, "doInBackground: zyzhang " + __cfduid);

                String postBody = "query{question(titleSlug:\"" + questionStat.getQuestion__title_slug() + "\") {content}}";
                Request graphqlRequest = new Request.Builder()
                        .addHeader("Content-Type","application/graphql")
                        .addHeader("Referer","https://leetcode.com/problems/" + questionStat.getQuestion__title_slug())
                        .addHeader("Cookie","__cfduid=" + __cfduid + ";" + "csrftoken=" + csrftoken)
                        .addHeader("x-csrftoken",csrftoken)
                        .url(Url.graphqlUrl)
                        .post(RequestBody.create(MediaType.parse("application/graphql; charset=utf-8"),postBody))
                        .build();

                Response graphqlResponse = client.newCall(graphqlRequest).execute();

                String graphqlResponseData = graphqlResponse.body().string();

                //Log.d(TAG, "doInBackground: zyzhang " + graphqlResponseData);

                LeetGraphqlQuestionBean leetGraphqlQuestionBean = gson.fromJson(graphqlResponseData,LeetGraphqlQuestionBean.class);

                //Log.d(TAG, "doInBackground: zyzhang " + leetGraphqlQuestionBean.getData().getQuestionGraphql().getContent());

                question.setContent(leetGraphqlQuestionBean.getData().getQuestionGraphql().getContent());

//                String description = doc.select("meta[name=description]").first().attr("content");
//                question.setDescription(description);
            } catch (IOException e) {
                e.printStackTrace();
            }

            DBHelper.insertDataToQuestionTBL(question);
            publishProgress((int)((i / (float)size) * 100));

            Log.d(TAG, "doInBackground: " + i);
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
        Intent intent = new Intent(App.getApplication(),MainActivity.class);
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
