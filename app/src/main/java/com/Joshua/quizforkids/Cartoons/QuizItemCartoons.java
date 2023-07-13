package com.Joshua.quizforkids.Cartoons;

public class QuizItemCartoons {

    private String question;
    private String correct;
    private String answer;
    private String answer2;
    private String answer3;

    public QuizItemCartoons(String question, String correct, String answer, String answer2, String answer3) {

        this.question = question;
        this.correct = correct;
        this.answer = answer;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }

    public boolean equals(QuizItemCartoons other) {
        if (this.question.compareTo(other.getQuestion()) == 0)
            return false;
        return true;
    }

    public String getCorrect() {
        return correct;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
