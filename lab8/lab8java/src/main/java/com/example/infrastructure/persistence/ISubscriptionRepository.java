package com.example.infrastructure.persistence;

import com.example.domain.Subscription;

public interface ISubscriptionRepository {
    void save(Subscription subscription);
}