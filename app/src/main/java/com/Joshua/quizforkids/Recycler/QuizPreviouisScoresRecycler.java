package com.Joshua.quizforkids.Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Joshua.quizforkids.R;
import com.Joshua.quizforkids.Scores.QuizScoreHolder;


public class QuizPreviouisScoresRecycler extends RecyclerView.Adapter<QuizPreviouisScoresRecycler.MyViewHolder> {

    private QuizScoreHolder quizScoreHolder = new QuizScoreHolder();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView username;
        private TextView topic;
        private TextView date;
        private TextView score;

        MyViewHolder(final View view) {
            super(view);
            username = view.findViewById(R.id.QuizPreviousScoreItemUsername);
            topic = view.findViewById(R.id.QuizPreviousScoreItemTopic);
            date = view.findViewById(R.id.QuizPreviousScoreItemDate);
            score = view.findViewById(R.id.QuizPreviousScoreItemPoints);
        }
    }

    @NonNull
    @Override
    public QuizPreviouisScoresRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.previous_score_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizPreviouisScoresRecycler.MyViewHolder holder, int position) {
        holder.username.setText(quizScoreHolder.getScores().get(position).getUsername());
        holder.topic.setText(quizScoreHolder.getScores().get(position).getTopic());
        holder.date.setText(quizScoreHolder.getScores().get(position).getDate().toString());
        holder.score.setText(""+quizScoreHolder.getScores().get(position).getScore());

    }

    @Override
    public int getItemCount() {
        return quizScoreHolder.getScores().size();
    }
}
