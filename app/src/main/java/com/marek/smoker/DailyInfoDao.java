package com.marek.smoker;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DailyInfoDao {

    @Query("SELECT * FROM DailyInfo")
    List<DailyInfo> getAllEvents();

    @Insert
    void insertAll(DailyInfo... dailyInfos);

    @Query("DELETE FROM DailyInfo")
    void deleteAll();

    @Query("UPDATE DailyInfo SET daily_amount=:amount WHERE daily_day = :day")
    void updateAmount(String amount, String day);

    @Query("UPDATE DailyInfo SET daily_money=:money WHERE daily_day = :day")
    void updateMoney(String money, String day);

    @Query("SELECT daily_amount FROM DailyInfo WHERE daily_day = :day")
    String getAmount(String day);

    @Query("SELECT daily_money FROM DailyInfo WHERE daily_day = :day")
    String getMoney(String day);

}

