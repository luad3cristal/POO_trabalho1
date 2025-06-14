package com.eventmanager.model.event;

import java.util.ArrayList;
import java.util.List;

import com.eventmanager.model.participant.Participant;

public abstract class Event {
    protected String title;
    protected String date;
    protected String location;
    protected int capacity;
    protected String description;
    protected List<Participant> participants;

    public Event(String title, String date, String location, int capacity, String description) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
        this.participants = new ArrayList<>();
    }

    public abstract void registerParticipant(Participant p);
    public abstract String getEventDescription();

    public void setDate(String date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public int getCapacity() { return capacity; }
    public String getDescription() { return description;}
    public List<Participant> getParticipants() { return participants; }
}
