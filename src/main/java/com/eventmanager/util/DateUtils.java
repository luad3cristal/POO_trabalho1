package com.eventmanager.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;

import com.eventmanager.model.event.Event;

public class DateUtils {
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(FORMATTER);
    }

    public static boolean isBeforeToday(String dateStr) {
        LocalDate date = parseDate(dateStr);
        return date != null && date.isBefore(LocalDate.now());
    }

    public static Comparator<Event> getDateComparator() {
        return Comparator.comparing(e -> parseDate(e.getDate()));
    }
}
