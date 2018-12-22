package com.marek.smoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddPacket extends AppCompatActivity {

    private static final String TAG = "AddPacket";

    EditText packetName;
    String packetDate;
    EditText packetPrice;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_packet);

        packetName = findViewById(R.id.packet_name);

        packetPrice = findViewById(R.id.packet_price);
        button = findViewById(R.id.button);

        DateFormat date = new SimpleDateFormat("MMM dd yyyy, h:mm");
        packetDate = date.format(Calendar.getInstance().getTime());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: eventName: " + packetName.getText().toString());
                saveEvent();

                //get current, add price, update

                startActivity(new Intent(AddPacket.this, MainActivity.class));
            }
        });
    }

    private void saveEvent() {

        Packet packet = new Packet(packetName.getText().toString(), packetDate, packetPrice.getText().toString(), "20");
        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.insertAll(packet);
    }
}
