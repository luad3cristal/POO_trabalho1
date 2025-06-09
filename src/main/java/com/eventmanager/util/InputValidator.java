package com.eventmanager.util;

public class InputValidator {

    public static boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) return false;

        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');

        return atIndex > 0 &&
               dotIndex > atIndex + 1 &&
               dotIndex < email.length() - 1 &&
               !email.contains(" ");
    }

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.isBlank()) return false;
        
        String cleanCpf = cpf.replaceAll("\\D", "");

        if (cleanCpf.length() != 11) return false;
        if (cleanCpf.chars().distinct().count() == 1) return false;

        
        int sum1 = 0;
        for (int i = 0; i < 9; i++) {
            sum1 += (cleanCpf.charAt(i) - '0') * (10 - i);
        }
        int check1 = sum1 % 11 < 2 ? 0 : 11 - (sum1 % 11);
        
        int sum2 = 0;
        for (int i = 0; i < 10; i++) {
            sum2 += (cleanCpf.charAt(i) - '0') * (11 - i);
        }
        int check2 = sum2 % 11 < 2 ? 0 : 11 - (sum2 % 11);

        return check1 == (cleanCpf.charAt(9) - '0') &&
            check2 == (cleanCpf.charAt(10) - '0');
    }

    public static String formatCPF(String cpf) {
        if (cpf == null) return null;

        String clean = cpf.replaceAll("\\D", "");
        if (clean.length() != 11) return null;

        return clean.substring(0, 3) + "." +
            clean.substring(3, 6) + "." +
            clean.substring(6, 9) + "-" +
            clean.substring(9);
    }

    public static boolean isPositiveInt(String input) {
        try {
            return Integer.parseInt(input) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
