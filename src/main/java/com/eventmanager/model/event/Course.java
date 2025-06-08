package com.eventmanager.model.event;

import  com.eventmanager.model.participant.Participant;
import  com.eventmanager.model.participant.Student;

public class Course extends Event implements HybridEvent {
    private boolean  online;
    private boolean  inPerson;
    private String onlineLink;

    public Course (String title, String date, String location, int capacity, String description, boolean online, boolean inPerson) {
        super(title, date, location, capacity, "Course");
        this.online = online;
        this.inPerson = inPerson;
    }

    @Override
    public void registerParticipant (Participant p) {
        if(!(p instanceof Student)) {
            System.out.println("Only students can subscribe to course");
            return;
        }
        if (participants.size() > capacity) {
            System.out.println("Course is full. Cannot register: " + p.getName());
            return;
        }

        participants.add(p);
    }



    @Override
    public void setOnline(boolean online) {this.online = online;}
    @Override
    public void setInPerson(boolean inPerson) {this.inPerson = inPerson;}
    @Override
    public boolean isOnline() {return online;}
    @Override
    public boolean isInPerson() {return inPerson;}

    @Override
    public void setOnlineLink(String link) {this.onlineLink = link;}
    @Override
    public void setLocation(String location) {this.location = location;}
    @Override
    public String getOnlineLink() {return onlineLink;}
    @Override
    public String getLocation() {return location;}

}