package com.example.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    public static synchronized void log(String source, String event) {
        String time = LocalTime.now().format(TIME_FORMATTER);
        System.out.printf("[%s] [%s] %s%n", time, source, event);
    }
}