package com.eventmanager.model.event;

import com.eventmanager.model.participant.Participant;

public abstract class Event {
    protected String title;
    protected String date;
    protected String location;
    protected int capacity;
    protected String description;

    public Event(String title, String date, String location, int capacity, String description) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
    }

    public abstract void registerParticipant(Participant p);

    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public String getDescription() { return description; }
}
