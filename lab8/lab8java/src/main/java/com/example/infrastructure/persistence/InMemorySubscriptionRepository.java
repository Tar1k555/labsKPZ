package com.example.infrastructure.persistence;

import com.example.domain.Subscription;
import java.util.HashMap;
import java.util.Map;

public class InMemorySubscriptionRepository implements ISubscriptionRepository {
    private final Map<Integer, Subscription> storage = new HashMap<>();

    @Override
    public void save(Subscription subscription) {
        storage.put(subscription.getId(), subscription);
        System.out.println("[База даних] Абонемент #" + subscription.getId() + " додано до таблиці subscriptions.");
    }
}