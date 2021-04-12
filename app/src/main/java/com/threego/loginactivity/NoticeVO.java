package com.threego.loginactivity;

public class NoticeVO {
    private int n_seq; // 시퀀스
    private String n_title; // 제목
    private String n_date; // 날짜
    private String n_content; // 내용

    public NoticeVO(int n_seq, String n_title, String n_date) {
        this.n_seq = n_seq;
        this.n_title = n_title;
        this.n_date = n_date;
    }

    public NoticeVO(String n_title, String n_date, String n_content) {
        this.n_title = n_title;
        this.n_date = n_date;
        this.n_content = n_content;
    }

    public int getN_seq() {
        return n_seq;
    }

    public void setN_seq(int n_seq) {
        this.n_seq = n_seq;
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public String getN_date() {
        return n_date;
    }

    public void setN_date(String n_date) {
        this.n_date = n_date;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

    @Override
    public String toString() {
        return "NoticeVO{" +
                "n_seq=" + n_seq +
                ", n_title='" + n_title + '\'' +
                ", n_date='" + n_date + '\'' +
                ", n_content='" + n_content + '\'' +
                '}';
    }
}
