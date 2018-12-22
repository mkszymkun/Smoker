package com.marek.events;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "event_name")
    private String eventName;

    @ColumnInfo(name = "event_date")
    private String eventDate;

    @ColumnInfo(name = "event_distance")
    private String eventDistance;

    public Event(String eventName, String eventDate, String eventDistance) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventDistance = eventDistance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDistance() {
        return eventDistance;
    }

    public void setEventDistance(String eventDistance) {
        this.eventDistance = eventDistance;
    }
}
