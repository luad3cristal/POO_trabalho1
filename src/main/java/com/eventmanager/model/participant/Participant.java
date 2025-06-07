package com.eventmanager.model.participant;

import com.eventmanager.model.event.Event;
import java.util.ArrayList;
import java.util.List;

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

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public List<Event> getRegisteredEvents() { return registeredEvents; }
}
