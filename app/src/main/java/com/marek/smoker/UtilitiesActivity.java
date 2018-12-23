package com.marek.smoker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class UtilitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btn_reset_daily_data;
        Button btn_reset_packet_db;
        Button btn_reset_alltime_amount;
        Button btn_reset_alltime_money;

        btn_reset_daily_data = findViewById(R.id.btn_reset_daily_data);
        btn_reset_daily_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetDate();
                startActivity(new Intent(UtilitiesActivity.this, MainActivity.class));
            }
        });

        btn_reset_packet_db = findViewById(R.id.btn_reset_packet_db);
        btn_reset_packet_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
                startActivity(new Intent(UtilitiesActivity.this, MainActivity.class));
            }
        });

        btn_reset_alltime_amount = findViewById(R.id.btn_reset_alltime_amount);
        btn_reset_alltime_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSmoked();
                startActivity(new Intent(UtilitiesActivity.this, MainActivity.class));
            }
        });

        btn_reset_alltime_money = findViewById(R.id.btn_reset_alltime_money);
        btn_reset_alltime_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMoney();
                startActivity(new Intent(UtilitiesActivity.this, MainActivity.class));
            }
        });


    }

    private void resetDate() {

        DailyInfoDao dailyInfoDao = AppDatabase.getDatabase(getApplicationContext()).dailyInfoDao();
        dailyInfoDao.deleteAll();

        SharedPreferences firstDate = getSharedPreferences("count", 0);
        final SharedPreferences.Editor edit = firstDate.edit();
        edit.putInt("last_date", 0);
        edit.commit();

        SharedPreferences daynum = getSharedPreferences("count", 0);
        final SharedPreferences.Editor edit2 = daynum.edit();
        edit2.putInt("day", 0);
        edit2.commit();
    }

    private void deleteAll() {

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.deleteAll();
    }

    private void resetSmoked() {

        SharedPreferences statsSmoked = getSharedPreferences("count", 0);
        final SharedPreferences.Editor edit = statsSmoked.edit();
        edit.putInt("smoked",0);
        edit.commit();
    }

    private void resetMoney() {

        SharedPreferences statsMoney = getSharedPreferences("count", 0);
        final SharedPreferences.Editor edit = statsMoney.edit();
        edit.putInt("money",0);
        edit.commit();
    }

}
