package com.eventmanager.util;

import java.util.UUID;

public class IdGenerator {
    public static String generateCertificateId() {
        return "C-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
