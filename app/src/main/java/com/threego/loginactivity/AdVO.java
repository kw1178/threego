package com.threego.loginactivity;

public class AdVO {

    private String src;
    private String adTitle;
    private String adContent;
    private String onGoing;

    public AdVO() {
    }

    public AdVO(String src, String adTitle, String adContent, String onGoing) {
        this.src = src;
        this.adTitle = adTitle;
        this.adContent = adContent;
        this.onGoing = onGoing;
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

    public String getOnGoing() {
        return onGoing;
    }

    public void setOnGoing(String onGoing) {
        this.onGoing = onGoing;
    }

    @Override
    public String toString() {
        return "AdVO{" +
                "src='" + src + '\'' +
                ", adTitle='" + adTitle + '\'' +
                ", adContents='" + adContent + '\'' +
                ", onGoing='" + onGoing + '\'' +
                '}';
    }
}
