package com.example.domain;

public class MembershipPlan {
    private final String code;
    private final String title;
    private final double baseMonthlyRate;
    private final int monthsDuration;

    public MembershipPlan(String code, String title, double baseMonthlyRate, int monthsDuration) {
        this.code = code;
        this.title = title;
        this.baseMonthlyRate = baseMonthlyRate;
        this.monthsDuration = monthsDuration;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public double getBaseMonthlyRate() { return baseMonthlyRate; }
    public int getMonthsDuration() { return monthsDuration; }
}