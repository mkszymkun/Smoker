package com.marek.events;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEvent extends AppCompatActivity {

    private static final String TAG = "CreateEvent";

    EditText eventName;
    EditText eventDate;
    EditText eventDistance;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        eventName = findViewById(R.id.event_name);
        eventDate = findViewById(R.id.event_date);
        eventDistance = findViewById(R.id.event_distance);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: eventName: " + eventName.getText().toString());
                saveEvent();
                startActivity(new Intent(CreateEvent.this, MainActivity.class));
            }
        });
    }

    private void saveEvent() {

        Event event = new Event(eventName.getText().toString(), eventDate.getText().toString(), eventDistance.getText().toString());
        EventDao eventDao = AppDatabase.getDatabase(getApplicationContext()).eventDao();
        eventDao.insertAll(event);
    }
}
