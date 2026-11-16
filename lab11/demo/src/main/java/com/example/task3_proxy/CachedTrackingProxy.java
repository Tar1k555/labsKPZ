package com.example.task3_proxy;

import com.example.util.ConsoleLogger;
import java.util.HashMap;
import java.util.Map;

public class CachedTrackingProxy implements TrackingService {
    private final RealPartnerTrackingService realService;
    private final long cacheTtlMillis;
    private final Map<String, CacheEntry> cache = new HashMap<>();

    private static class CacheEntry {
        String status;
        long timestamp;

        CacheEntry(String status, long timestamp) {
            this.status = status;
            this.timestamp = timestamp;
        }
    }

    public CachedTrackingProxy(RealPartnerTrackingService realService, long cacheTtlMillis) {
        this.realService = realService;
        this.cacheTtlMillis = cacheTtlMillis;
    }

    @Override
    public String getStatus(String trackingNumber) {
        long currentTime = System.currentTimeMillis();

        if (cache.containsKey(trackingNumber)) {
            CacheEntry entry = cache.get(trackingNumber);
            if (currentTime - entry.timestamp < cacheTtlMillis) {
                ConsoleLogger.log("CachedTrackingProxy", "[КЕШ] Статус отримано з кешу для " + trackingNumber + " (без платного запиту)");
                return entry.status;
            } else {
                ConsoleLogger.log("CachedTrackingProxy", "[TTL] Термін дії кешу вичерпано для " + trackingNumber);
            }
        }

        String freshStatus = realService.getStatus(trackingNumber);
        cache.put(trackingNumber, new CacheEntry(freshStatus, currentTime));
        return freshStatus;
    }

    public int getTotalPaidRequests() {
        return realService.getPaidRequestsCount();
    }
}