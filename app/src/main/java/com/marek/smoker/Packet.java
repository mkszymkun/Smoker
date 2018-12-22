package com.marek.smoker;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Packet {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "packet_brand")
    private String packetBrand;

    @ColumnInfo(name = "packet_date")
    private String packetDate;

    @ColumnInfo(name = "packet_price")
    private String packetPrice;

    @ColumnInfo(name = "packet_available")
    private String packetAvailable;

    public Packet(String packetBrand, String packetDate, String packetPrice, String packetAvailable) {
        this.packetBrand = packetBrand;
        this.packetDate = packetDate;
        this.packetPrice = packetPrice;
        this.packetAvailable = packetAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPacketBrand() {
        return packetBrand;
    }

    public void setPacketBrand(String packetBrand) {
        this.packetBrand = packetBrand;
    }

    public String getPacketDate() {
        return packetDate;
    }

    public void setPacketDate(String packetDate) {
        this.packetDate = packetDate;
    }

    public String getPacketPrice() {
        return packetPrice;
    }

    public void setPacketPrice(String packetPrice) {
        this.packetPrice = packetPrice;
    }

    public String getPacketAvailable() {
        return packetAvailable;
    }

    public void setPacketAvailable(String packetAvailable) {
        this.packetAvailable = packetAvailable;
    }
}
