package com.eventmanager.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.eventmanager.model.event.Event;
import com.eventmanager.util.DateUtils;

public class ReportGenerator {

    public static String generateReportByType(List<Event> events) {
    StringBuilder sb = new StringBuilder();
    Map<String, List<Event>> grouped = events.stream()
            .collect(Collectors.groupingBy(e -> e.getClass().getSimpleName()));

    sb.append("=== Report by Type ===\n");
    for (String type : grouped.keySet()) {
        for (Event e : grouped.get(type)) {
            sb.append("- ").append(e.getTitle())
              .append(" | Date: ").append(DateUtils.formatDate(DateUtils.parseDateFlexible(e.getDate()))).append("\n");
        }
    }

    return sb.toString();
}


    public static String generateReportByDate(List<Event> events) {
        StringBuilder sb = new StringBuilder();
        List<Event> sorted = new ArrayList<>(events);
        sorted.sort(DateUtils.getDateComparator());

        sb.append("=== Report by Date ===\n");
        for (Event e : sorted) {
            sb.append("- ").append(e.getTitle())
              .append(" | Date: ").append(DateUtils.formatDate(DateUtils.parseDateFlexible(e.getDate()))).append("\n");
        }

        return sb.toString();
    }
}
