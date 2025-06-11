package com.eventmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.eventmanager.model.event.Event;
import com.eventmanager.model.event.HybridEvent;


public class EventController {
  private static final EventController instance = new EventController();
  private final List<Event> events = new ArrayList<>();

  private EventController() {}

  public static EventController getInstance() {
      return instance;
  }

  public void addEvent (Event event) {
    events.add(event);
  }

  public List<Event> listAllEvents() {
    return events;
  }

  public Event searchByTitle (String title) {
    return events.stream().filter(e -> e.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
  }

  public boolean removeByTitle (String title) {
    return events.removeIf(e -> e.getTitle().equalsIgnoreCase(title));
  }

  public boolean updateEvent(String title, String newDate, String newLocation, int newCapacity, String newLink) {
    Event event = searchByTitle(title);

    if (event != null) {
        if (newDate != null) {
            event.setDate(newDate);
        }

        if (event instanceof HybridEvent hybrid) {
            if (newLocation != null) {
                hybrid.setLocation(newLocation);
            }
            
            if (hybrid.isOnline() && newLink != null && !newLink.isBlank()) {
                hybrid.setOnlineLink(newLink);
            }
        }

        if (newCapacity > 0) {
            event.setCapacity(newCapacity);
        }

        return true;
    }

    return false;
  }
}