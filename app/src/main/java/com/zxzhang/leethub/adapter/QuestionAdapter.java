package com.zxzhang.leethub.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxzhang.leethub.R;
import com.zxzhang.leethub.model.dao.Question;

import java.util.List;

import static android.content.ContentValues.TAG;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    private List<Question> mQustionList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView questionNumber;
        TextView questionTitle;
        public ViewHolder(View view){
            super(view);
            questionNumber = (TextView)view.findViewById(R.id.question_number);
            questionTitle = (TextView)view.findViewById(R.id.question_title);
        }
    }

    public QuestionAdapter(Context context,List<Question>questionList){
        mContext = context;
        mQustionList = questionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = mQustionList.get(position);
        holder.questionNumber.setText(String.valueOf(question.getQuestion_id()));
        Log.d(TAG, "onBindViewHolder: " + question.getTitle());
        holder.questionTitle.setText(question.getTitle());
    }

    @Override
    public int getItemCount() {
        return mQustionList.size();
    }
}
