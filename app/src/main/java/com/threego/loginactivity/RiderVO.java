package com.threego.loginactivity;

public class RiderVO {
    private String r_id;  // 라이더 아이디
    private String r_pw;  // 라이더 비밀번호
    private int r_box; // 배달통 번호
    private String r_name; // 라이더 이름
    private int r_age; // 라이더 나이
    private String r_gender; // 라이더 성별
    private String r_phone; // 라이더 전화번호
    private int r_money; // 예치금

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getR_pw() {
        return r_pw;
    }

    public void setR_pw(String r_pw) {
        this.r_pw = r_pw;
    }

    public int getR_box() {
        return r_box;
    }

    public void setR_box(int r_box) {
        this.r_box = r_box;
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

    public String getR_gender() {
        return r_gender;
    }

    public void setR_gender(String r_gender) {
        this.r_gender = r_gender;
    }

    public String getR_phone() {
        return r_phone;
    }

    public void setR_phone(String r_phone) {
        this.r_phone = r_phone;
    }

    public int getR_money() {
        return r_money;
    }

    public void setR_money(int r_money) {
        this.r_money = r_money;
    }

    @Override
    public String toString() {
        return "RiderVO{" +
                "r_id='" + r_id + '\'' +
                ", r_pw='" + r_pw + '\'' +
                ", r_box=" + r_box +
                ", r_name='" + r_name + '\'' +
                ", r_age=" + r_age +
                ", r_gender='" + r_gender + '\'' +
                ", r_phone='" + r_phone + '\'' +
                ", r_money=" + r_money +
                '}';
    }
}
