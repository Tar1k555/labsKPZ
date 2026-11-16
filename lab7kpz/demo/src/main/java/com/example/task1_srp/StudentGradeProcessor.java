package com.example.task1_srp;

import java.math.BigDecimal;
import java.util.List;

public class StudentGradeProcessor {
    private final AcademicRecordValidator validator;
    private final ScholarshipCalculator calculator;
    private final TranscriptFormatter formatter;
    private final ScholarshipCsvExporter exporter;
    private final CuratorNotifier notifier;

    public StudentGradeProcessor() {
        this.validator = new AcademicRecordValidator();
        this.calculator = new ScholarshipCalculator();
        this.formatter = new TranscriptFormatter();
        this.exporter = new ScholarshipCsvExporter();
        this.notifier = new CuratorNotifier();
    }

    public void processStudentAcademicRecord(String studentId, String studentName, List<CourseGrade> grades, String curatorEmail) {
        validator.validate(studentId, studentName, grades);

        double gpa = calculator.calculateGpa(grades);
        BigDecimal scholarshipAmount = calculator.calculateScholarship(grades, gpa);

        String transcript = formatter.formatTranscript(studentId, studentName, grades, gpa, scholarshipAmount);
        exporter.exportToCsv(studentId, studentName, gpa, scholarshipAmount);
        notifier.notifyCurator(curatorEmail, studentName, transcript);
    }
}