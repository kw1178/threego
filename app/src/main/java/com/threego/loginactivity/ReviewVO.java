package com.threego.loginactivity;

public class ReviewVO {
    private int r_num;  // 리뷰 번호
    private int dl_num; // 고객 번호
    private String r_evaluation;    // 한줄평

    private String r_name; // 라이더 이름
    private int r_age; // 라이더 나이
    private String r_phone; // 라이더 전화번호
    private String r_gender; // 라이더 성별
    private int r_box; // 배달통 번호

    public ReviewVO(String r_name, int r_age, String r_phone, String r_gender, int r_box) {
        this.r_name = r_name;
        this.r_age = r_age;
        this.r_phone = r_phone;
        this.r_gender = r_gender;
        this.r_box = r_box;
    }

    public ReviewVO(int r_num, int dl_num, String r_evaluation) {
        this.r_num = r_num;
        this.dl_num = dl_num;
        this.r_evaluation = r_evaluation;
    }

    public int getR_num() {
        return r_num;
    }

    public void setR_num(int r_num) {
        this.r_num = r_num;
    }

    public int getDl_num() {
        return dl_num;
    }

    public void setDl_num(int dl_num) {
        this.dl_num = dl_num;
    }

    public String getR_evaluation() {
        return r_evaluation;
    }

    public void setR_evaluation(String r_evaluation) {
        this.r_evaluation = r_evaluation;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public int getR_age() {
        return r_age;
    }

    public void setR_age(int r_age) {
        this.r_age = r_age;
    }

    public String getR_phone() {
        return r_phone;
    }

    public void setR_phone(String r_phone) {
        this.r_phone = r_phone;
    }

    public String getR_gender() {
        return r_gender;
    }

    public void setR_gender(String r_gender) {
        this.r_gender = r_gender;
    }

    public int getR_box() {
        return r_box;
    }

    public void setR_box(int r_box) {
        this.r_box = r_box;
    }

    @Override
    public String toString() {
        return "ReviewVO{" +
                "r_num=" + r_num +
                ", dl_num=" + dl_num +
                ", r_evaluation='" + r_evaluation + '\'' +
                ", r_name='" + r_name + '\'' +
                ", r_age=" + r_age +
                ", r_phone='" + r_phone + '\'' +
                ", r_gender='" + r_gender + '\'' +
                ", r_box=" + r_box +
                '}';
    }
}
