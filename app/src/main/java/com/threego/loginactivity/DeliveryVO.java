package com.threego.loginactivity;

import java.util.Date;

public class DeliveryVO {
    private int dl_number;  // 주문번호
    private String dl_date; // 날짜
    private String r_id;    // 라이더 id
    private String dl_shop; // 가게이름
    private String dl_food; // 음식명
    private int dl_price;   // 음식가격
    private String dl_cooktime;   // 조리시간
    private String dl_address;  // 고객 주소
    private int dl_call;    // 콜비
    private String dl_status;   // 콜 상태
    private String dl_r_lati;   // 라이더 위도
    private String dl_r_longi;  // 라이더 경도
    private String dl_s_lati;  // 가게 위치
    private String dl_s_longi;
    private String dl_distoshop;    // 현위치-가게거리
    private String dl_distoadd;     // 가게 - 고객집거리
    private String dl_dltime;   // 배달완료예상시간
    private String dl_c_lati;
    private String dl_c_longi;

    public int getDl_number() {
        return dl_number;
    }

    public void setDl_number(int dl_number) {
        this.dl_number = dl_number;
    }

    public String getDl_date() {
        return dl_date;
    }

    public void setDl_date(String dl_date) {
        this.dl_date = dl_date;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getDl_shop() {
        return dl_shop;
    }

    public void setDl_shop(String dl_shop) {
        this.dl_shop = dl_shop;
    }

    public String getDl_food() {
        return dl_food;
    }

    public void setDl_food(String dl_food) {
        this.dl_food = dl_food;
    }

    public int getDl_price() {
        return dl_price;
    }

    public void setDl_price(int dl_price) {
        this.dl_price = dl_price;
    }

    public String getDl_cooktime() {
        return dl_cooktime;
    }

    public void setDl_cooktime(String dl_cooktime) {
        this.dl_cooktime = dl_cooktime;
    }

    public String getDl_address() {
        return dl_address;
    }

    public void setDl_address(String dl_address) {
        this.dl_address = dl_address;
    }

    public int getDl_call() {
        return dl_call;
    }

    public void setDl_call(int dl_call) {
        this.dl_call = dl_call;
    }

    public String getDl_status() {
        return dl_status;
    }

    public void setDl_status(String dl_status) {
        this.dl_status = dl_status;
    }

    public String getDl_r_lati() {
        return dl_r_lati;
    }

    public void setDl_r_lati(String dl_r_lati) {
        this.dl_r_lati = dl_r_lati;
    }

    public String getDl_r_longi() {
        return dl_r_longi;
    }

    public void setDl_r_longi(String dl_r_longi) {
        this.dl_r_longi = dl_r_longi;
    }

    public String getDl_s_lati() {
        return dl_s_lati;
    }

    public void setDl_s_lati(String dl_s_lati) {
        this.dl_s_lati = dl_s_lati;
    }

    public String getDl_s_longi() {
        return dl_s_longi;
    }

    public void setDl_s_longi(String dl_s_longi) {
        this.dl_s_longi = dl_s_longi;
    }

    public String getDl_distoshop() {
        return dl_distoshop;
    }

    public void setDl_distoshop(String dl_distoshop) {
        this.dl_distoshop = dl_distoshop;
    }

    public String getDl_distoadd() {
        return dl_distoadd;
    }

    public void setDl_distoadd(String dl_distoadd) {
        this.dl_distoadd = dl_distoadd;
    }

    public String getDl_dltime() {
        return dl_dltime;
    }

    public void setDl_dltime(String dl_dltime) {
        this.dl_dltime = dl_dltime;
    }

    public String getDl_c_lati() {
        return dl_c_lati;
    }

    public void setDl_c_lati(String dl_c_lati) {
        this.dl_c_lati = dl_c_lati;
    }

    public String getDl_c_longi() {
        return dl_c_longi;
    }

    public void setDl_c_longi(String dl_c_longi) {
        this.dl_c_longi = dl_c_longi;
    }

    @Override
    public String toString() {
        return "DeliveryVO{" +
                "dl_number=" + dl_number +
                ", dl_date='" + dl_date + '\'' +
                ", r_id='" + r_id + '\'' +
                ", dl_shop='" + dl_shop + '\'' +
                ", dl_food='" + dl_food + '\'' +
                ", dl_price=" + dl_price +
                ", dl_cooktime='" + dl_cooktime + '\'' +
                ", dl_address='" + dl_address + '\'' +
                ", dl_call=" + dl_call +
                ", dl_status='" + dl_status + '\'' +
                ", dl_r_lati='" + dl_r_lati + '\'' +
                ", dl_r_longi='" + dl_r_longi + '\'' +
                ", dl_s_lati='" + dl_s_lati + '\'' +
                ", dl_s_longi='" + dl_s_longi + '\'' +
                ", dl_distoshop='" + dl_distoshop + '\'' +
                ", dl_distoadd='" + dl_distoadd + '\'' +
                ", dl_dltime='" + dl_dltime + '\'' +
                ", dl_c_lati='" + dl_c_lati + '\'' +
                ", dl_c_longi='" + dl_c_longi + '\'' +
                '}';
    }
}
