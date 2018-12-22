package com.marek.smoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class ListPacketsActivity extends AppCompatActivity {

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

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        List<Packet> packets = packetDao.getAllEvents();



//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new PacketAdapter(packets);
//        recyclerView.setAdapter(adapter);

    }
}