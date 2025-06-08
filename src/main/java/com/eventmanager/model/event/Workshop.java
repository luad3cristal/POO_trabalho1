package com.eventmanager.model.event;

public class Workshop extends Event implements HybridEvent {
    public Workshop (String title, String date, String location, int capacity, String description) {
        super(title, date, location, capacity, description);
    }

}