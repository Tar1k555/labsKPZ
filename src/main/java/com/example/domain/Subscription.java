package com.example.domain;

import com.example.domain.strategy.IPricingStrategy;

public class Subscription {
    private final int id;
    private final Member member;
    private final MembershipPlan plan;
    private final boolean personalTrainerIncluded;
    private final String startDate;
    
    private double baseAmount;
    private double discountAmount;
    private double finalCost;
    private String status;

    public Subscription(int id, Member member, MembershipPlan plan, boolean personalTrainerIncluded, String startDate) {
        this.id = id;
        this.member = member;
        this.plan = plan;
        this.personalTrainerIncluded = personalTrainerIncluded;
        this.startDate = startDate;
        this.status = "Draft";
    }

    public void calculateAndFinalize(IPricingStrategy strategy) {
        this.baseAmount = strategy.calculateBaseAmount(
            plan.getBaseMonthlyRate(), 
            plan.getMonthsDuration(), 
            personalTrainerIncluded
        );

        this.discountAmount = member.hasDiscountCard() ? this.baseAmount * 0.10 : 0.0;
        this.finalCost = this.baseAmount - this.discountAmount;
        this.status = "Active";
    }

    public String generateContract() {
        return "Договір #" + id + " з " + member.getName() +
               "\nТариф: " + plan.getCode() +
               "\nБазова вартість: " + baseAmount + " грн" +
               "\nЗнижка: " + discountAmount + " грн" +
               "\nДо сплати: " + finalCost + " грн";
    }

    public int getId() { return id; }
    public Member getMember() { return member; }
    public MembershipPlan getPlan() { return plan; }
    public double getFinalCost() { return finalCost; }
    public String getStatus() { return status; }
}