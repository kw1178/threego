package com.threego.loginactivity;

public class AdVO {

    private String src;
    private String adTitle;
    private String adContent;
    private String adType;
    private int cnt;

    public AdVO() {
    }

    //일반광고 - 경로, 이름, 내용, 배달중 여부
    public AdVO(String src, String adTitle, String adContent, String adType) {
        this.src = src;
        this.adTitle = adTitle;
        this.adContent = adContent;
        this.adType = adType;
    }

    //영상광고 - 경로, 이름, 내용, 횟수
    public AdVO(String src, String adTitle, String adContent, int cnt) {
        this.src = src;
        this.adTitle = adTitle;
        this.adContent = adContent;
        this.cnt = cnt;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    @Override
    public String toString() {
        return "AdVO{" +
                "src='" + src + '\'' +
                ", adTitle='" + adTitle + '\'' +
                ", adContents='" + adContent + '\'' +
                ", onGoing='" + adType + '\'' +
                '}';
    }
}
