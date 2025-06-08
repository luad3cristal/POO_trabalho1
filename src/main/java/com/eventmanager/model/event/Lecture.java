package com.eventmanager.model.event;

public class Lecture extends Event implements HybridEvent {
    public Lecture (String title, String date, String location, int capacity, String description) {
        super(title, date, location, capacity, description);
    }

}