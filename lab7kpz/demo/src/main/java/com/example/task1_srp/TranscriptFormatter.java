package com.example.task1_srp;

import java.math.BigDecimal;
import java.util.List;

public class TranscriptFormatter {
    public String formatTranscript(String studentId, String studentName, List<CourseGrade> grades, double gpa, BigDecimal scholarshipAmount) {
        StringBuilder transcript = new StringBuilder();
        transcript.append("==========================================\n");
        transcript.append("АКАДЕМІЧНА ДОВІДКА: ").append(studentName).append(" (ID: ").append(studentId).append(")\n");
        transcript.append("==========================================\n");
        for (CourseGrade g : grades) {
            transcript.append(String.format("%-30s | %d Кредитів | Оцінка: %d%n", g.getCourseName(), g.getEctsCredits(), g.getScore()));
        }
        transcript.append("------------------------------------------\n");
        transcript.append(String.format("Середній бал (GPA): %.2f%n", gpa));
        transcript.append("Призначена стипендія: ").append(scholarshipAmount).append(" грн\n");

        return transcript.toString();
    }
}