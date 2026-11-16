package com.example.task1_srp;

import java.util.List;

public class AcademicRecordValidator {
    public void validate(String studentId, String studentName, List<CourseGrade> grades) {
        if (studentId == null || studentId.isBlank() || studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("Ідентифікатор та ім'я студента не можуть бути порожніми.");
        }

        if (grades == null || grades.isEmpty()) {
            throw new IllegalStateException("Перелік дисциплін порожній.");
        }

        for (CourseGrade g : grades) {
            if (g.getScore() < 0 || g.getScore() > 100) {
                throw new IllegalArgumentException("Неможлива оцінка " + g.getScore() + " з предмета " + g.getCourseName() + ".");
            }
        }
    }
}