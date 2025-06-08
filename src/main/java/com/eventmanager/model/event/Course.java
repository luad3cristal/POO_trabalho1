package com.eventmanager.model.event;

public class Course extends Event implements HybridEvent {

    public Course (String title, String date, String location, int capacity, String description) {
        super(title, date, location, capacity, description);
    }

}