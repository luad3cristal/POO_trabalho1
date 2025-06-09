  package com.eventmanager.service;

import java.util.ArrayList;
import java.util.List;

import com.eventmanager.model.event.Event;
import com.eventmanager.model.participant.Participant;


public class ParticipantController {
  private final List<Participant> participants = new ArrayList<>();
  
  public void addParticipant (Participant p) {
    participants.add(p);
  }

  public List<Participant> listAllParticipants() {
    return participants;
  }

  public Participant searchByCpf (String cpf) {
    return participants.stream().filter(p -> p.getCpf().equalsIgnoreCase(cpf)).findFirst().orElse(null);
  }

  public boolean removeByCpf (String cpf) {
    return participants.removeIf(p -> p.getCpf().equalsIgnoreCase(cpf));
  }

  public boolean updateParticipant (String cpf, String name, String email) {
    Participant participant = searchByCpf(cpf);

    if (participant != null) {
      participant.setName(name);
      participant.setEmail(email);
      return true;
    }

    return false;
  }

  public void subscribeInEvent (String cpf, Event event) {
    Participant participant = searchByCpf(cpf);

    if (participant != null) {
      event.registerParticipant(participant);
      participant.enroll(event);
    }
  }
}