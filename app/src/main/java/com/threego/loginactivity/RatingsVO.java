package com.threego.loginactivity;

public class RatingsVO {
    private String r_id;  // 라이더 아이디
    private int dl_number; // 고객 번호
    private String ra_evals;    // 한줄평
    private int ra_rating;  // 평점
    private int ra_reviewnum; // seq

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public int getDl_number() {
        return dl_number;
    }

    public void setDl_number(int dl_number) {
        this.dl_number = dl_number;
    }

    public String getRa_evals() {
        return ra_evals;
    }

    public void setRa_evals(String ra_evals) {
        this.ra_evals = ra_evals;
    }

    public int getRa_rating() {
        return ra_rating;
    }

    public void setRa_rating(int ra_rating) {
        this.ra_rating = ra_rating;
    }

    public int getRa_reviewnum() {
        return ra_reviewnum;
    }

    public void setRa_reviewnum(int ra_reviewnum) {
        this.ra_reviewnum = ra_reviewnum;
    }


}
