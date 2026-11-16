package com.example.task1_facade;

import com.example.util.ConsoleLogger;

public class DeliveryFacade {
    private final AddressChecker addressChecker;
    private final CostCalculator costCalculator;
    private final PaymentProcessor paymentProcessor;
    private final WaybillGenerator waybillGenerator;
    private final RoutePlanner routePlanner;
    private final RecipientNotifier recipientNotifier;

    public DeliveryFacade() {
        this.addressChecker = new AddressChecker();
        this.costCalculator = new CostCalculator();
        this.paymentProcessor = new PaymentProcessor();
        this.waybillGenerator = new WaybillGenerator();
        this.routePlanner = new RoutePlanner();
        this.recipientNotifier = new RecipientNotifier();
    }

    // Доступ до окремої підсистеми розрахунку
    public CostCalculator getCostCalculator() {
        return this.costCalculator;
    }

    // Єдина проста операція комплексного оформлення
    public String shipPackage(String sender, String recipient, String recipientPhone, String address, double weightKg, double distanceKm) {
        ConsoleLogger.log("DeliveryFacade", "=== Початок комплексного оформлення відправлення ===");
        
        addressChecker.verifyAddress(address);
        double cost = costCalculator.calculate(weightKg, distanceKm);
        String waybillNum = waybillGenerator.generate(sender, recipient);
        paymentProcessor.processPayment(waybillNum, cost);
        routePlanner.planRoute(waybillNum, address);
        recipientNotifier.sendNotification(recipientPhone, waybillNum);

        ConsoleLogger.log("DeliveryFacade", "=== Оформлення відправлення " + waybillNum + " успішно завершено ===\n");
        return waybillNum;
    }
}