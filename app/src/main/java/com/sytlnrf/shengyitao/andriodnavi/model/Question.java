package com.sytlnrf.shengyitao.andriodnavi.model;

/**
 * Created by shengyitao on 8/10/15.
 */
public class Question {
    private int mQuestion;
    private boolean mTrueQuestion;
    public Question(int question, boolean trueQuestion){
        this.mQuestion = question;
        this.mTrueQuestion = trueQuestion;
    }
    public int getmQuestion(){
        return this.mQuestion;
    }
    public void setmQuestion(int question){
        this.mQuestion = question;
    }
    public boolean ismTrueQuestion(){
        return this.mTrueQuestion;
    }
    public void setmTrueQuestion(boolean trueQuestion){
        this.mTrueQuestion = trueQuestion;
    }
}
