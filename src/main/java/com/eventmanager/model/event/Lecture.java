package com.eventmanager.model.event;

import java.time.LocalDate;

import  com.eventmanager.model.participant.Participant;
import com.eventmanager.util.DateUtils;

public class Lecture extends Event implements HybridEvent {
    private boolean online;
    private boolean inPerson;
    private String onlineLink;

    LocalDate localDate = DateUtils.parseDateFlexible(getDate());
    String formattedDate = DateUtils.formatDate(localDate);

    public Lecture (String title, String date, String location, String onlineLink, int capacity, String description, boolean online, boolean inPerson) {
        super(title, date, "-", capacity, "Lecture");
        this.online = online;
        this.inPerson = inPerson;
        this.onlineLink = onlineLink;
    }

    @Override
    public void registerParticipant(Participant p) {
        if (participants.size() > capacity) {
            System.out.println("Lecture is already full. Cannot register: " + p.getName());
        }

        System.out.println("Participant subscribed sucessfully.");
        participants.add(p);
    }
    
    @Override
    public String getEventDescription() {
        return "- " + getDescription() + ": "   
            + getTitle()
            + " | " + formattedDate
            + " | Capacity: " + getCapacity()
            + (isOnline() ? " | Online Link: " + (getOnlineLink().equals("-") ? "Not defined yet" : getOnlineLink()) : "") 
            + (isInPerson() ? " | Location: " + (getLocation().equals("-") ? "Not defined yet" : getLocation()) : "");
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