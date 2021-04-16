package com.threego.loginactivity;

public class NoticeVO {
    private int n_postnum; // 시퀀스
    private String n_title; // 제목
    private String n_date; // 날짜
    private String n_content; // 내용
    private String r_id; // 라이더 아이디

    public int getN_postnum() {
        return n_postnum;
    }

    public void setN_postnum(int n_postnum) {
        this.n_postnum = n_postnum;
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

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    @Override
    public String toString() {
        return "NoticeVO{" +
                "n_postnum=" + n_postnum +
                ", n_title='" + n_title + '\'' +
                ", n_date='" + n_date + '\'' +
                ", n_content='" + n_content + '\'' +
                ", r_id='" + r_id + '\'' +
                '}';
    }
}
