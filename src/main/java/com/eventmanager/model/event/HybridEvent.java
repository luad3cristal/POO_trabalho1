package com.eventmanager.model.event;

public interface HybridEvent {
    void setOnline(boolean online);
    void setInPerson(boolean inPerson);

    boolean isOnline();
    boolean isInPerson();

    void setOnlineLink(String link);
    void setLocation(String location);

    String getOnlineLink();
    String getLocation();
}
