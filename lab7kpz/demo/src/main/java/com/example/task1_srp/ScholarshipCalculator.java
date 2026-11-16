package com.example.task1_srp;

import java.math.BigDecimal;
import java.util.List;

public class ScholarshipCalculator {
    public double calculateGpa(List<CourseGrade> grades) {
        double totalWeightedScore = 0;
        int totalCredits = 0;

        for (CourseGrade g : grades) {
            totalWeightedScore += g.getScore() * g.getEctsCredits();
            totalCredits += g.getEctsCredits();
        }

        return totalCredits == 0 ? 0 : totalWeightedScore / totalCredits;
    }

    public BigDecimal calculateScholarship(List<CourseGrade> grades, double gpa) {
        boolean hasFailedCourses = grades.stream().anyMatch(g -> g.getScore() < 60);

        if (hasFailedCourses) {
            return BigDecimal.ZERO;
        }

        if (gpa >= 90.0) {
            return new BigDecimal("2910.00");
        } else if (gpa >= 75.0) {
            return new BigDecimal("2000.00");
        }

        return BigDecimal.ZERO;
    }
}