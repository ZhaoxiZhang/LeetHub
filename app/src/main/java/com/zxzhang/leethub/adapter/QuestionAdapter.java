package com.zxzhang.leethub.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zxzhang.leethub.R;
import com.zxzhang.leethub.activity.QuestionDetailActivity;
import com.zxzhang.leethub.model.dao.Question;

import java.util.List;

import static android.content.ContentValues.TAG;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    private List<Question> mQustionList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View questionView;
        TextView questionNumber;
        TextView questionTitle;
        public ViewHolder(View view){
            super(view);
            questionView = view;
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
        final ViewHolder holder = new ViewHolder(view);
        holder.questionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Question question = mQustionList.get(position);
                Intent intent = new Intent(mContext, QuestionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("question",question);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = mQustionList.get(position);
        holder.questionNumber.setText(String.valueOf(question.getFrontend_question_id()));
        Log.d(TAG, "onBindViewHolder: " + question.getTitle());
        holder.questionTitle.setText(question.getTitle());
    }

    @Override
    public int getItemCount() {
        return mQustionList.size();
    }
}
