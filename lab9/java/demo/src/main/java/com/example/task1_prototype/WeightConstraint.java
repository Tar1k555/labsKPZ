package com.example.task1_prototype;

public class WeightConstraint implements Cloneable {
    private double maxWeightKg;

    public WeightConstraint(double maxWeightKg) {
        this.maxWeightKg = maxWeightKg;
    }

    public void setMaxWeightKg(double maxWeightKg) { this.maxWeightKg = maxWeightKg; }

    @Override
    public WeightConstraint clone() {
        try {
            return (WeightConstraint) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Помилка клонування обмежень", e);
        }
    }

    @Override
    public String toString() {
        return maxWeightKg + " кг";
    }
}