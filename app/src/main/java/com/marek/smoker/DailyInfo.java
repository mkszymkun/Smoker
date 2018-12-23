package com.marek.smoker;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DailyInfo {

    @ColumnInfo(name = "daily_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "daily_amount")
    private String dailyAmount;

    @ColumnInfo(name = "daily_money")
    private String dailyMoney;

    @ColumnInfo(name = "daily_day")
    private String dailyDay;

    @ColumnInfo(name = "daily_date")
    private String dailyDate;

    public DailyInfo(String dailyDate, String dailyDay, String dailyAmount, String dailyMoney) {
        this.dailyDate = dailyDate;
        this.dailyDay = dailyDay;
        this.dailyAmount = dailyAmount;
        this.dailyMoney = dailyMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(String dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public String getDailyMoney() {
        return dailyMoney;
    }

    public void setDailyMoney(String dailyMoney) {
        this.dailyMoney = dailyMoney;
    }

    public String getDailyDay() {
        return dailyDay;
    }

    public void setDailyDay(String dailyDay) {
        this.dailyDay = dailyDay;
    }

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        Packet packetCompare = (Packet) obj;
        if(packetCompare.getPacketDate().equals(this.getDailyDate()))
            return true;

        return false;
    }
}
