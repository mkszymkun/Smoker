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
}
