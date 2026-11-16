package com.example.task1_srp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScholarshipCsvExporter {
    public void exportToCsv(String studentId, String studentName, double gpa, BigDecimal scholarshipAmount) {
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String csvLine = String.format("%s;%s;%.2f;%s;%s%n", studentId, studentName, gpa, scholarshipAmount, dateStr);
        try (FileOutputStream fos = new FileOutputStream("scholarship_registry.csv", true)) {
            fos.write(csvLine.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("Помилка експорту у CSV: " + e.getMessage());
        }
    }
}