package zhaoxizhang.github.io.leethub.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import zhaoxizhang.github.io.leethub.R;
import zhaoxizhang.github.io.leethub.model.dao.Question;
import zhaoxizhang.github.io.leethub.model.graphql.Discuss;
import zhaoxizhang.github.io.leethub.ui.activity.DiscussActivity;

import static android.support.constraint.Constraints.TAG;

public class DiscussAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final static int TYPE_CONTENT = 0;
    private final static int TYPE_LOADING_MORE = 1;

    private List<Discuss.DataBean.QuestionTopicsListBean.EdgesBean>mDiscussList;
    private Context mContext;
    private boolean showLoadingMore = false;
    private volatile boolean isDataLoading;
    private Question question;

    static class ContentViewHolder extends RecyclerView.ViewHolder{
        View discussView;
        CircleImageView civAvatar;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvCreateDate;
        TextView tvDropUp;
        TextView tvViewEye;

        public ContentViewHolder(View view){
            super(view);
            discussView = view;
            civAvatar = view.findViewById(R.id.civ_problem_discuss_avatar);
            tvTitle = view.findViewById(R.id.tv_problem_discuss_title);
            tvAuthor = view.findViewById(R.id.tv_problem_discuss_author);
            tvCreateDate = view.findViewById(R.id.tv_problem_discuss_createDate);
            tvDropUp = view.findViewById(R.id.tv_problem_discuss_drop_up);
            tvViewEye = view.findViewById(R.id.tv_problem_discuss_view_eye);
        }
    }

    static class LoadingMoreHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public LoadingMoreHolder(View view){
            super(view);
            progressBar = (ProgressBar)view;
        }
    }

    public DiscussAdapter(Context context, List<Discuss.DataBean.QuestionTopicsListBean.EdgesBean>discussList){
        mContext = context;
        mDiscussList = discussList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < getDataItemCount()
            && getDataItemCount() > 0){
            return TYPE_CONTENT;
        }
        return TYPE_LOADING_MORE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADING_MORE){
            return new LoadingMoreHolder(LayoutInflater.from(mContext).inflate(R.layout.infinite_loading, parent, false));
        }else{
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_discuss, parent, false);
            final ContentViewHolder contentViewHolder = new ContentViewHolder(view);
            contentViewHolder.discussView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = contentViewHolder.getAdapterPosition();
                    Discuss.DataBean.QuestionTopicsListBean.EdgesBean discuss = mDiscussList.get(position);
                    Intent intent = new Intent(mContext, DiscussActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("discuss", discuss);
                    bundle.putString("title", question.getTitle());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
            return contentViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_LOADING_MORE){
            bindLoadingViewHolder((LoadingMoreHolder)viewHolder, position);
        }else{
            Discuss.DataBean.QuestionTopicsListBean.EdgesBean.NodeBean nodeBean = mDiscussList.get(position).getNode();
            ContentViewHolder contentViewHolder = (ContentViewHolder)viewHolder;
            contentViewHolder.tvAuthor.setText(nodeBean.getPost().getAuthor().getUsername());
            contentViewHolder.tvTitle.setText(nodeBean.getTitle());
            contentViewHolder.tvDropUp.setText(String.valueOf(nodeBean.getPost().getVoteCount()));

            int viewCount = nodeBean.getViewCount();
            if (viewCount >= 1000){
                contentViewHolder.tvViewEye.setText(viewCount / 1000 + "." + viewCount / 100 % 10 + "k");
            }else{
                contentViewHolder.tvViewEye.setText(String.valueOf(viewCount));
            }

            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(nodeBean.getPost().getCreationDate() * 1000L);
            Date date = null;
            try {
                date = format.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Locale englishUS = Locale.US;
            String dateStr = String.format(englishUS, "%tB %<te,  %<tY  %<tT %<Tp%n", date);
            contentViewHolder.tvCreateDate.setText(dateStr);

            Glide.with(mContext)
                    .load(nodeBean.getPost().getAuthor().getProfile().getUserAvatar())
                    .into(contentViewHolder.civAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return getDataItemCount() + (showLoadingMore ? 1 : 0);
    }

    public int getDataItemCount(){
        return mDiscussList == null ? 0 : mDiscussList.size();
    }

    public void insertDiscuss(List<Discuss.DataBean.QuestionTopicsListBean.EdgesBean>discussList){
        Log.d(TAG, "insertDiscuss: " + discussList.size());
        mDiscussList.addAll(discussList);
        notifyDataSetChanged();
    }

    private void bindLoadingViewHolder(LoadingMoreHolder holder, int position){
        Log.d(TAG, "bindLoadingViewHolder: " + "isDataLoading = "  + isDataLoading);
        holder.progressBar.setVisibility(position > 0
                && isDataLoading
                ? View.VISIBLE : View.INVISIBLE);
    }

    public boolean isDataLoading() {
        return isDataLoading;
    }

    public void setDataLoading(boolean dataLoading) {
        isDataLoading = dataLoading;
    }

    public boolean isShowLoadingMore() {
        return showLoadingMore;
    }

    public void setShowLoadingMore(boolean showLoadingMore) {
        this.showLoadingMore = showLoadingMore;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}


