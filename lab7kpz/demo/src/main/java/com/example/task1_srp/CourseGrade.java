package com.example.task1_srp;

public class CourseGrade {
    private String courseName;
    private int score;
    private int ectsCredits;

    public CourseGrade(String courseName, int score, int ectsCredits) {
        this.courseName = courseName;
        this.score = score;
        this.ectsCredits = ectsCredits;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getEctsCredits() { return ectsCredits; }
    public void setEctsCredits(int ectsCredits) { this.ectsCredits = ectsCredits; }
}