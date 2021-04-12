package com.threego.loginactivity;

public class MoneyVO {
    private String m_date;
    private int m_money;
    private String m_status;

    public MoneyVO(String m_date, int m_money, String m_status) {
        this.m_date = m_date;
        this.m_money = m_money;
        this.m_status = m_status;
    }

    public String getM_date() {
        return m_date;
    }

    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    public int getM_money() {
        return m_money;
    }

    public void setM_money(int m_money) {
        this.m_money = m_money;
    }

    public String getM_status() {
        return m_status;
    }

    public void setM_status(String m_status) {
        this.m_status = m_status;
    }

    @Override
    public String toString() {
        return "MoneyVO{" +
                "m_date='" + m_date + '\'' +
                ", m_money=" + m_money +
                ", m_status='" + m_status + '\'' +
                '}';
    }
}
