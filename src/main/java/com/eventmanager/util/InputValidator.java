package com.eventmanager.util;

import java.util.regex.Pattern;

public class InputValidator {
  private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");

    private static final Pattern CPF_PATTERN =
        Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

    private static final Pattern DATE_PATTERN =
        Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidCPF(String cpf) {
        return CPF_PATTERN.matcher(cpf).matches();
    }

    public static boolean isValidDate(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

    public static boolean isPositiveInt(String input) {
        try {
            return Integer.parseInt(input) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNonEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }
}
