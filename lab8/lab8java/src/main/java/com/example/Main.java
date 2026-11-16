package com.example;

import com.example.domain.Member;
import com.example.domain.MembershipPlan;
import com.example.domain.Subscription;
import com.example.domain.strategy.IPricingStrategy;
import com.example.domain.strategy.PlanPricingFactory;
import com.example.infrastructure.notification.INotificationService;
import com.example.infrastructure.notification.PushNotificationService;
import com.example.infrastructure.persistence.InMemorySubscriptionRepository;
import com.example.infrastructure.persistence.ISubscriptionRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  ДЕМОНСТРАЦІЯ GRASP: ЗАВДАННЯ 4 та 5 (Варіант 3) ");
        System.out.println("=================================================\n");

        // --- ДЕМОНСТРАЦІЯ ЗАВДАННЯ 4 (Protected Variations) ---
        ISubscriptionRepository repository = new InMemorySubscriptionRepository();
        INotificationService notificationService = new PushNotificationService();

        // Доменні об'єкти
        Member member = new Member(101, "Іван Петренко", "+380501234567", true);
        MembershipPlan plan = new MembershipPlan("VIP", "Річний VIP", 1200.0, 12);

        // --- ДЕМОНСТРАЦІЯ ЗАВДАННЯ 4 (Polymorphism) ---
        IPricingStrategy strategy = PlanPricingFactory.getStrategy(plan.getCode());

        // Розрахунок логіки всередині сутності (Information Expert)
        Subscription subscription = new Subscription(1002, member, plan, true, "2026-11-01");
        subscription.calculateAndFinalize(strategy);

        // Виведення договору
        System.out.println(subscription.generateContract());
        System.out.println();

        // Збереження та сповіщення через абстракції
        repository.save(subscription);
        notificationService.sendNotification(member.getName(), "Ваш абонемент #" + subscription.getId() + " активовано!");

        System.out.println("\n=================================================");
        System.out.println("  МЕТРИКИ ЗАВДАННЯ 5:");
        System.out.println("  * CBO (Subscription) = 3 (Норма <= 4)");
        System.out.println("  * LCOM4 (Subscription) = 1 (Ідеальна згуртованість)");
        System.out.println("  * Instability I (Domain) = 0.0 (Абсолютно стабільний)");
        System.out.println("=================================================");
    }
}