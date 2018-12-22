package com.marek.events;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

public class ListEventsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);

        EventDao eventDao = AppDatabase.getDatabase(getApplicationContext()).eventDao();
        List<Event> events = eventDao.getAllEvents();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);

    }
}