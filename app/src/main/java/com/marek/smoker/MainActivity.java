package com.marek.smoker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements SelectableViewHolder.OnItemSelectedListener{

    private static final String TAG = "MainActivity";

//    RecyclerView recyclerView;
//    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    SelectableAdapter adapter;

    Button btn_add;
    Button btn_list;
    Button btn_clrdb;
    Button btn_smoke;
    Packet current_packet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        List<Packet> packets = packetDao.getAllEvents();

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new PacketAdapter(packets);
//        recyclerView.setAdapter(adapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.selection_list);
        recyclerView.setLayoutManager(layoutManager);
        List<Packet> selectableItems = packetDao.getAllEvents();
        adapter = new SelectableAdapter(this, selectableItems,false);
        recyclerView.setAdapter(adapter);

        btn_add = findViewById(R.id.btn_smoke);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (current_packet != null) {
                    Log.d(TAG, "onCLick: smoked!");
                    String amountLeft = String.valueOf(Integer.parseInt(current_packet.getPacketAvailable()) - 1);

                    if (Integer.parseInt(amountLeft) <= 0) {
                        deletePacket(current_packet.getPacketBrand());
                    }
                    update(amountLeft, current_packet.getPacketBrand());


                    SharedPreferences statsSmoked = getSharedPreferences("count", 0);

                    int count = statsSmoked.getInt("smoked",0);
                    count++;
                    final SharedPreferences.Editor edit = statsSmoked.edit();
                    edit.putInt("smoked",count);
                    edit.commit();

                    int smoked = statsSmoked.getInt("smoked", 100);
                    String text = String.valueOf(smoked);

                    Log.d(TAG, text);

                    //get current, add 1, update

                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }
                else {
                    Snackbar.make(recyclerView, "Choose a packet", Snackbar.LENGTH_LONG).show();
                }
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
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        btn_clrdb = findViewById(R.id.btn_clrsmoked);
        btn_clrdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSmoked();
            }
        });

        btn_clrdb = findViewById(R.id.btn_clrmoney);
        btn_clrdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMoney();
            }
        });

    }

    public void onItemSelected(SelectableItem selectableItem) {

        List<Packet> selectedItems = adapter.getSelectedItems();
        current_packet = selectableItem;
    }

    private void deleteAll() {

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.deleteAll();
    }

    private void deletePacket(String brand) {

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.deletePacket(brand);
    }

    private void update(String amountLeft, String brand) {

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.update(amountLeft, brand);
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
