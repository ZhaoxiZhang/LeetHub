package zhaoxizhang.github.io.leethub.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.model.bean.LeetTopicsBean;
import zhaoxizhang.github.io.leethub.ui.activity.QuestionTopicActivity;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.ViewHolder> {
    private static final String TAG = "TopicsAdapter";
    private List<LeetTopicsBean.TopicsBean> mTopicsList;
    private Context mContext;


    static class ViewHolder extends RecyclerView.ViewHolder {
        View topicView;
        TextView topicName;
        TextView questionAmount;

        public ViewHolder(View view) {
            super(view);
            topicView = view;
            topicName = view.findViewById(R.id.tv_topic_name);
            questionAmount = view.findViewById(R.id.tv_question_amount);
        }
    }

    public TopicsAdapter(Context context, List<LeetTopicsBean.TopicsBean> topicsList) {
        mContext = context;
        mTopicsList = topicsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.topicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                LeetTopicsBean.TopicsBean topic = mTopicsList.get(position);
                Log.d(TAG, "onClick: topicName = " + topic.getName());
                Intent intent = new Intent(mContext, QuestionTopicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("topicName", topic.getName());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        LeetTopicsBean.TopicsBean topic = mTopicsList.get(position);
        holder.topicName.setText(topic.getName());
        holder.questionAmount.setText(String.valueOf(topic.getQuestions().size()));
        Log.d(TAG, "onBindViewHolder: topic Name = " + topic.getName());
    }

    @Override
    public int getItemCount() {
        return mTopicsList == null ? 0 : mTopicsList.size();
    }

}
