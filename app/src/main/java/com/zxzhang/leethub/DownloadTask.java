package com.zxzhang.leethub;


import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.google.gson.Gson;
import com.zxzhang.leethub.model.bean.DifficultyBean;
import com.zxzhang.leethub.model.bean.QuestionsBean;
import com.zxzhang.leethub.model.bean.StatBean;
import com.zxzhang.leethub.model.bean.StatStatusPairsBean;
import com.zxzhang.leethub.model.dao.Question;
import com.zxzhang.leethub.model.db.DBHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        OkHttpClient client = new OkHttpClient();
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
        QuestionsBean questions = gson.fromJson(responseDate, QuestionsBean.class);
        int size = questions.getStat_status_pairs().size();

        Document doc = null;
        for (int i = 0; i < size; i++) {
            Question question = new Question();

            StatStatusPairsBean questionStatStatus = questions.getStat_status_pairs().get(i);
            StatBean questionStat = questionStatStatus.getStat();
            DifficultyBean difficultyBean = questionStatStatus.getDifficulty();

            question.setQuestion_id(questionStat.getQuestion_id());
            question.setArticle_live(questionStat.isQuestion__article__live());
            question.setArticle_slug(questionStat.getQuestion__article__slug());
            question.setTitle(questionStat.getQuestion__title());
            question.setTitle_slug(questionStat.getQuestion__title_slug());
            question.setDifficylty(difficultyBean.getLevel());


            try {
                doc = Jsoup.connect("https://leetcode.com/problems/"
                        + questionStat.getQuestion__title_slug()).get();
                String description = doc.select("meta[name=description]").first().attr("content");
                question.setDescription(description);
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
