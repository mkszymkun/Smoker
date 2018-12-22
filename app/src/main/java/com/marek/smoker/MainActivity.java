package com.marek.smoker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Button btn_add;
    Button btn_list;
    Button btn_clrdb;
    Button btn_smoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_smoke);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCLick: smoked!");
                startActivity(new Intent(MainActivity.this, AddPacket.class));
            }
        });

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCLick: pressed!");
                startActivity(new Intent(MainActivity.this, AddPacket.class));
            }
        });

        btn_list = findViewById(R.id.btn_list);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCLick: pressed!");
                startActivity(new Intent(MainActivity.this, ListPacketsActivity.class));
            }
        });

        btn_clrdb = findViewById(R.id.btn_clrdb);
        btn_clrdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll();
            }
        });

    }

    private void deleteAll() {

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.deleteAll();
    }
}
