package com.marek.events;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface EventDao {

    @Query("SELECT * FROM event")
    List<Event> getAllEvents();

    @Insert
    void insertAll(Event... events);

    @Query("DELETE FROM event")
    void deleteAll();
}
