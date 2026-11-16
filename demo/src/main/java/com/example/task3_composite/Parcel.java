package com.example.task3_composite;

public class Parcel implements CargoComponent {
    private final String waybillNumber;
    private final String description;
    private final double weightKg;
    private final double declaredValue;

    public Parcel(String waybillNumber, String description, double weightKg, double declaredValue) {
        this.waybillNumber = waybillNumber;
        this.description = description;
        this.weightKg = weightKg;
        this.declaredValue = declaredValue;
    }

    @Override
    public String getName() {
        return "Посилка " + waybillNumber + " (" + description + ")";
    }

    @Override
    public double getWeight() {
        return weightKg;
    }

    @Override
    public double getDeclaredValue() {
        return declaredValue;
    }

    @Override
    public int getShipmentCount() {
        return 1;
    }

    @Override
    public void printTree(String indent) {
        System.out.printf("%s├── %s | Вага: %.2f кг | Оголошена вартість: %.2f грн%n",
                indent, getName(), getWeight(), getDeclaredValue());
    }
}