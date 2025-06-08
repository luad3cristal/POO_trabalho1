package com.eventmanager.model.event;

public class Fair extends Event implements HybridEvent {
    public Fair (String title, String date, String location, int capacity, String description) {
        super(title, date, location, capacity, description);
    }

}