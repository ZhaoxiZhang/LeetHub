package zhaoxizhang.github.io.leethub.adapter;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.ui.activity.ProblemActivity;

import static android.content.ContentValues.TAG;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> mQuestionList;
    private Activity mHost;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View questionView;
        TextView questionNumber;
        TextView questionTitle;
        TextView questionDifficulty;
        ImageView questionSolution;

        ViewHolder(View view) {
            super(view);
            questionView = view;
            questionNumber = view.findViewById(R.id.tv_question_number);
            questionTitle = view.findViewById(R.id.tv_question_title);
            questionDifficulty = view.findViewById(R.id.tv_question_difficulty);
            questionSolution = view.findViewById(R.id.iv_question_solution);
        }
    }

    public QuestionAdapter(Activity context) {
        mHost = context;
        mQuestionList = new ArrayList<>();
    }

//    public QuestionAdapter(Context context, List<Question> questionList) {
//        mHost = context;
//        mQuestionList = questionList;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.questionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Question question = mQuestionList.get(position);
                //Intent intent = new Intent(mHost, ProblemDetailActivity.class);
                Intent intent = new Intent(mHost, ProblemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("question", question);
                bundle.putInt("questionFrontendID", question.getFrontend_question_id());
                intent.putExtras(bundle);
                mHost.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = mQuestionList.get(position);
        holder.questionNumber.setText(String.valueOf(question.getFrontend_question_id()));
        Log.d(TAG, "onBindViewHolder: " + question.getTitle());
        holder.questionTitle.setText(question.getTitle());

        boolean isArticleLive = question.getArticle_live();
        if (isArticleLive) {
            holder.questionSolution.setVisibility(View.VISIBLE);
        } else {
            holder.questionSolution.setVisibility(View.INVISIBLE);
        }

        int difficulty = question.getDifficulty();

        Resources resources = mHost.getResources();
        Drawable drawable;
        switch (difficulty) {
            case 1:
                drawable = resources.getDrawable(R.drawable.bg_item_question_tv_difficulty_easy, mHost.getTheme());
                holder.questionDifficulty.setBackground(drawable);
                holder.questionDifficulty.setText(mHost.getString(R.string.question_difficulty_easy));
                break;
            case 2:
                drawable = resources.getDrawable(R.drawable.bg_item_question_tv_difficulty_medium, mHost.getTheme());
                holder.questionDifficulty.setBackground(drawable);
                holder.questionDifficulty.setText(mHost.getString(R.string.question_difficulty_medium));
                break;
            case 3:
                drawable = resources.getDrawable(R.drawable.bg_item_question_tv_difficulty_hard, mHost.getTheme());
                holder.questionDifficulty.setBackground(drawable);
                holder.questionDifficulty.setText(mHost.getString(R.string.question_difficulty_hard));
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        return mQuestionList == null ? 0 : mQuestionList.size();
    }

    public void setQuestionList(List<Question> questionList) {
        mQuestionList = questionList;
        notifyDataSetChanged();
    }

    public void clear() {
        mQuestionList.clear();
        notifyDataSetChanged();
    }
}
