package com.marek.smoker;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PacketDao {

    @Query("SELECT * FROM Packet")
    List<Packet> getAllEvents();

    @Insert
    void insertAll(Packet... packets);

    @Query("DELETE FROM Packet")
    void deleteAll();

    @Query("UPDATE Packet SET packet_available=:amount WHERE packet_brand = :brand")
    void update(String amount, String brand);

    @Query("DELETE FROM Packet WHERE packet_brand = :brand")
    void deletePacket(String brand);

}
