package com.eventmanager.io;

import java.util.UUID;

import com.eventmanager.model.event.Event;
import com.eventmanager.model.participant.Participant;

public class CertificateGenerator {

    public static String generateCertificate(Participant participant, Event event) {
        String certificateId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return """
                ----------------------------------------
                CERTIFICATE OF PARTICIPATION
                ----------------------------------------
                Certificate ID: %s
                
                This is to certify that
                %s (CPF: %s)
                participated in the event:
                \"%s\" on %s.
                
                Mode: %s
                ----------------------------------------
                """
                .formatted(
                        certificateId,
                        participant.getName(),
                        participant.getCpf(),
                        event.getTitle(),
                        event.getDate(),
                        event instanceof com.eventmanager.model.event.HybridEvent hybrid
                                ? (hybrid.isOnline() && hybrid.isInPerson() ? "Hybrid" :
                                   hybrid.isOnline() ? "Online" : "In-person")
                                : "In-person"
                );
    }
}
