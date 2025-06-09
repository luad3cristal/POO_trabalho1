package com.eventmanager.io;

import com.eventmanager.model.event.Event;
import com.eventmanager.model.participant.Participant;
import com.eventmanager.util.IdGenerator;

public class CertificateGenerator {

    public static String generateCertificate(Participant participant, Event event) {
        String certificateId = IdGenerator.generateCertificateId();

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
