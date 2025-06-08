package com.eventmanager.util;

import java.util.UUID;

public class IdGenerator {

    public static String generateParticipantId() {
        return "P-" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String generateEventId() {
        return "E-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
