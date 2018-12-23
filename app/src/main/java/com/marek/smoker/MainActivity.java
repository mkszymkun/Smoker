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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements SelectableViewHolder.OnItemSelectedListener{

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    SelectableAdapter adapter;

    Button btn_add;
    Button btn_list;
    Button btn_clrdb;
    Button btn_smoke;
    Button btn_stats;
    Packet current_packet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        List<Packet> packets = packetDao.getAllEvents();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.selection_list);
        recyclerView.setLayoutManager(layoutManager);
        List<Packet> selectableItems = packetDao.getAllEvents();
        adapter = new SelectableAdapter(this, selectableItems,false);
        recyclerView.setAdapter(adapter);

        compareDate();

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

                    getAmount();

                    Log.d(TAG, text);

                    String news = getAmount();
                    Log.d(TAG, news);

                    String incremented = String.valueOf(Integer.parseInt(news) + 1);
                    String day = String.valueOf(getLastDay());

                    //get current, add 1, update

                    updateAmount(incremented, day);

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

        btn_stats = findViewById(R.id.btn_stats);
        btn_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onCLick: pressed!");
                startActivity(new Intent(MainActivity.this, ListDailyInfoActivity.class));
            }
        });

        btn_clrdb = findViewById(R.id.btn_clrdb);
        btn_clrdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UtilitiesActivity.class));
            }
        });
    }

    public void onItemSelected(SelectableItem selectableItem) {

        List<Packet> selectedItems = adapter.getSelectedItems();
        current_packet = selectableItem;
    }

    private void deletePacket(String brand) {

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.deletePacket(brand);
    }

    private void update(String amountLeft, String brand) {

        PacketDao packetDao = AppDatabase.getDatabase(getApplicationContext()).packetDao();
        packetDao.update(amountLeft, brand);
    }

    private int getDate() {

        DateFormat year = new SimpleDateFormat("YYYY");
        String year_str = year.format(Calendar.getInstance().getTime());
        int year_int = Integer.parseInt(year_str);

        DateFormat month = new SimpleDateFormat("MM");
        String month_str = month.format(Calendar.getInstance().getTime());
        int month_int = Integer.parseInt(month_str);


        DateFormat day = new SimpleDateFormat("dd");
        String day_str = day.format(Calendar.getInstance().getTime());
        int day_int = Integer.parseInt(day_str);

        int current_date = year_int*10000+month_int*100+day_int;

        return current_date;
    }

    private void updateAmount(String amount, String day) {

        DailyInfoDao dailyInfoDao = AppDatabase.getDatabase(getApplicationContext()).dailyInfoDao();
        dailyInfoDao.updateAmount(amount, day);
    }

    private String getAmount() {

        String day = String.valueOf(getLastDay());
        DailyInfoDao dailyInfoDao = AppDatabase.getDatabase(getApplicationContext()).dailyInfoDao();
        return dailyInfoDao.getAmount(day);
    }

    private int getLastDate() {

        SharedPreferences firstDate = getSharedPreferences("count", 0);
        int day = firstDate.getInt("day", 0);
            return firstDate.getInt("last_date", 0);

    }

    private int getLastDay() {

        SharedPreferences firstDate = getSharedPreferences("count", 0);
        return firstDate.getInt("day", 0);

    }

    private void compareDate() {
        int last = getLastDate();
        int current = getDate();
        if (current > last) {
            changeLastDate();
            changeLastDay();

            String date = String.valueOf(getLastDate());
            String day = String.valueOf(getLastDay());

            DailyInfo dailyInfo = new DailyInfo(date, day, "0", "0");
            DailyInfoDao dailyInfoDao = AppDatabase.getDatabase(getApplicationContext()).dailyInfoDao();
            dailyInfoDao.insertAll(dailyInfo);

        }
    }

    private void changeLastDate() {

        SharedPreferences firstDate = getSharedPreferences("count", 0);
        final SharedPreferences.Editor edit = firstDate.edit();
        int date = getDate();
        edit.putInt("last_date", date);
        edit.commit();
    }

    private void changeLastDay() {

        SharedPreferences daynum = getSharedPreferences("count", 0);
        final SharedPreferences.Editor edit = daynum.edit();
        int day = daynum.getInt("day", 0) + 1;
        edit.putInt("day", day);
        edit.commit();
    }


}
