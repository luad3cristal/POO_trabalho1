package com.eventmanager.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

import com.eventmanager.model.event.Event;

public class DateUtils {
    private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
        DateTimeFormatter.ofPattern("dd.MM.yyyy")
    };

    public static boolean isValidDate(String dateStr) {
        return parseDateFlexible(dateStr) != null;
    }

    public static LocalDate parseDateFlexible(String dateStr) {
        if (dateStr == null || dateStr.isBlank()) return null;

        for (DateTimeFormatter formatter : SUPPORTED_FORMATS) {
            try {
                return LocalDate.parse(dateStr.trim(), formatter);
            } catch (DateTimeParseException ignored) {}
        }

        return null;
    }

    public static long daysBeforeToday(String dateStr) {
        LocalDate date = parseDateFlexible(dateStr);
        if (date != null && date.isBefore(LocalDate.now())) {
            return ChronoUnit.DAYS.between(date, LocalDate.now());
        }
        return -1;
    }

    public static String formatDate(LocalDate date) {
        if (date == null) return null;
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static Comparator<Event> getDateComparator() {
        return Comparator.comparing(e -> parseDateFlexible(e.getDate()));
    }
}
