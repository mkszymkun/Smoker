package com.marek.smoker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class ListDailyInfoActivity extends AppCompatActivity {

    private static final String TAG = "ListDailyInfoActivity";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_daily_info_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);


        DailyInfoDao dailyInfoDao = AppDatabase.getDatabase(getApplicationContext()).dailyInfoDao();
        List<DailyInfo> dailyInfos = dailyInfoDao.getAllEvents();



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DailyInfoAdapter(dailyInfos);
        recyclerView.setAdapter(adapter);

    }
}