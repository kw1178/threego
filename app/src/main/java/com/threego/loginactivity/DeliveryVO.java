package com.threego.loginactivity;

import java.util.Date;

public class DeliveryVO {
    private String dl_shop; // 가게이름
    private String dl_house; // 가게-고객 거리
    private String dl_address; // 고객주소
    private String dl_shoplocation; // 가게주소
    private String dl_food; // 음식명
    private int dl_price; // 음식가격
    private int dl_call; // 콜비
    private String dl_date; // 날짜
    private int dl_count; // 건수

    public DeliveryVO(int dl_call, String dl_date, int dl_count) {
        this.dl_call = dl_call;
        this.dl_date = dl_date;
        this.dl_count = dl_count;
    }

    public DeliveryVO(String dl_shop, String dl_house, String dl_address, String dl_shoplocation, String dl_food, int dl_price, int dl_call) {
        this.dl_shop = dl_shop;
        this.dl_house = dl_house;
        this.dl_address = dl_address;
        this.dl_shoplocation = dl_shoplocation;
        this.dl_food = dl_food;
        this.dl_price = dl_price;
        this.dl_call = dl_call;
    }

    public String getDl_shop() {
        return dl_shop;
    }

    public void setDl_shop(String dl_shop) {
        this.dl_shop = dl_shop;
    }

    public String getDl_house() {
        return dl_house;
    }

    public void setDl_house(String dl_house) {
        this.dl_house = dl_house;
    }

    public String getDl_address() {
        return dl_address;
    }

    public void setDl_address(String dl_address) {
        this.dl_address = dl_address;
    }

    public String getDl_shoplocation() {
        return dl_shoplocation;
    }

    public void setDl_shoplocation(String dl_shoplocation) {
        this.dl_shoplocation = dl_shoplocation;
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

    public int getDl_call() {
        return dl_call;
    }

    public void setDl_call(int dl_call) {
        this.dl_call = dl_call;
    }

    public String getDl_date() {
        return dl_date;
    }

    public void setDl_date(String dl_date) {
        this.dl_date = dl_date;
    }

    public int getDl_count() {
        return dl_count;
    }

    public void setDl_count(int dl_count) {
        this.dl_count = dl_count;
    }


    @Override
    public String toString() {
        return "DeliveryVO{" +
                "dl_shop='" + dl_shop + '\'' +
                ", dl_house='" + dl_house + '\'' +
                ", dl_address='" + dl_address + '\'' +
                ", dl_shoplocation='" + dl_shoplocation + '\'' +
                ", dl_food='" + dl_food + '\'' +
                ", dl_price=" + dl_price +
                ", dl_call=" + dl_call +
                ", dl_date=" + dl_date +
                ", dl_count=" + dl_count +
                '}';
    }
}
