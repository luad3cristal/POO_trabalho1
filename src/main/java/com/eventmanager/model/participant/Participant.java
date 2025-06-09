package com.eventmanager.model.participant;

import java.util.ArrayList;
import java.util.List;

import com.eventmanager.model.event.Event;

public abstract class Participant {
    protected String cpf;
    protected String name;
    protected String email;
    protected List<Event> registeredEvents;

    public Participant(String name, String email, String cpf) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.registeredEvents = new ArrayList<>();
    }

    public abstract String getIdentifier(); 

    public void enroll(Event event) {
        registeredEvents.add(event);
    }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public String getCpf() { return cpf; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<Event> getRegisteredEvents() { return registeredEvents; }
}
