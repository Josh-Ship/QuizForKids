package com.Joshua.quizforkids.Scores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuizScoreHolder {

    public static List<QuizScore> scores;
    private QuizScore current;
    private static boolean scoreSorted = false;

    public QuizScoreHolder(QuizScore quizScore){
        if (scores == null)
            scores = new ArrayList<>();
        current = quizScore;
    }
    public QuizScoreHolder(){
        if (scores == null)
            scores = new ArrayList<>();

    }

    public int getTotal(){
        int res = 0;
        for (QuizScore item : scores){
            res += item.getScore();
        }
        return res;
    }

    public int getTotal(String username){
        int res = 0;
        for (QuizScore item : scores){
            if (item.getUsername().compareTo(username) ==0)
                res += item.getScore();
        }
        return res;
    }

    public boolean finish(){
        if (current == null)
            return false;
        scores.add(current);
        current = null;
        return true;
    }

    public List<QuizScore> getScores() {
        return scores;
    }

    public void increaseScore(){
        this.current.incrementScore();
    }

    // sort based on option
    public void sort(String option){
        if (option.compareTo("score") == 0) {
            Collections.sort(scores, new Comparator<QuizScore>() {
                @Override
                public int compare(QuizScore quizScore, QuizScore t1) {
                   return Integer.compare(quizScore.getScore(), t1.getScore());
                }
            });
        }
        else if (option.compareTo("date") == 0) {
            Collections.sort(scores, new Comparator<QuizScore>() {
                @Override
                public int compare(QuizScore quizScore, QuizScore t1) {
                   return quizScore.getDateUsedForComparison().compareTo(t1.getDateUsedForComparison());
                }
            });
        }
        else if (option.compareTo("topic") == 0 ) {
            Collections.sort(scores, new Comparator<QuizScore>() {
                @Override
                public int compare(QuizScore quizScore, QuizScore t1) {
                    return quizScore.getTopic().compareTo(t1.getTopic());
                }
            });
        }
        else if (option.compareTo("username") == 0 ) {
            Collections.sort(scores, new Comparator<QuizScore>() {
                @Override
                public int compare(QuizScore quizScore, QuizScore t1) {
                    return quizScore.getUsername().compareTo(t1.getUsername());
                }
            });
        }
    }
}
