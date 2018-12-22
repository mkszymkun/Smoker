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

public class ListPacketsActivity extends AppCompatActivity {

    private static final String TAG = "ListPacketsActivity";

//    RecyclerView recyclerView;
//    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_packets);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        recyclerView = findViewById(R.id.recycler_view);
        SharedPreferences statsSmoked = getSharedPreferences("count", 0);
        int smoked = statsSmoked.getInt("smoked", 100);
        String text = String.valueOf(smoked);
        Log.d(TAG, text);

        TextView stats_smoked = findViewById(R.id.stats_smoked);
        stats_smoked.setText(text);

        SharedPreferences statsMoney = getSharedPreferences("count", 0);
        int price = statsMoney.getInt("money", 100);
        String text2 = String.valueOf(price);
        Log.d(TAG, text2);

        TextView stats_money = findViewById(R.id.stats_money);
        stats_money.setText(text2);


        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        List<Packet> packets = packetDao.getAllEvents();



//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new PacketAdapter(packets);
//        recyclerView.setAdapter(adapter);

    }
}