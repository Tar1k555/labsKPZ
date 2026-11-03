package com.example.task2_factorymethod;

import com.example.util.ConsoleLogger;

public abstract class DeliveryProcess {

    // Фабричний метод
    protected abstract Delivery createDelivery();

    // Загальний процес оформлення, який ніколи не змінюється
    public void registerDelivery(String shipmentId, String destinationAddress, double weightKg) {
        ConsoleLogger.log("DeliveryProcess", "Початок оформлення відправлення " + shipmentId);
        ConsoleLogger.log("DeliveryProcess", "1. Перевірка адреси: " + destinationAddress);

        // Виклик Фабричного Методу
        Delivery delivery = createDelivery();

        double cost = delivery.calculateCost(weightKg);
        int days = delivery.calculateDeliveryDays();
        String executor = delivery.getExecutorType();

        ConsoleLogger.log("DeliveryProcess", "2. Розраховано вартість: " + String.format("%.2f", cost) + " грн, строк: " + days + " дн.");
        ConsoleLogger.log("DeliveryProcess", "3. Призначено виконавця: " + executor);
        ConsoleLogger.log("DeliveryProcess", "4. Успішно зареєстровано тип: [" + delivery.getTypeName() + "]\n");
    }
}
