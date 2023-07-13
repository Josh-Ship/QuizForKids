package com.Joshua.quizforkids.Scores;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class QuizScore{



    private String username;
    private String topic;
    private String date;
    private Date dateUsedForComparison;
    private int score;


    public QuizScore(String username, String topic) {
        this.username = username;
        this.topic = topic;
        this.date = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        this.dateUsedForComparison = new Date();
        this.score = 0;
    }

    public void incrementScore() {
        this.score++;
    }

    public void decrementScore() {
        if (this.score - 1 >= 0)
            this.score--;
    }

    public int compareWithTopic(QuizScore other) {
        if (this.topic.compareTo("Animals") == 0)
            if (other.topic.compareTo("Cartoons") == 0)
                return 1;
            else
                return 0;
        else
            if (other.topic.compareTo("Animals") == 0)
                return -1;
        return 0;
    }

    public Date getDateUsedForComparison(){ return this.dateUsedForComparison; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

}
