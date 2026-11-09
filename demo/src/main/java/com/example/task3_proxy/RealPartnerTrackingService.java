package com.example.task3_proxy;

import com.example.util.ConsoleLogger;

public class RealPartnerTrackingService implements TrackingService {
    private int paidRequestsCount = 0;

    @Override
    public String getStatus(String trackingNumber) {
        paidRequestsCount++;
        ConsoleLogger.log("RealPartnerService", "[$] ПЛАТНИЙ ЗАПИТ до сервісу партнера №" + paidRequestsCount + " для " + trackingNumber);
        return "В дорозі (Склад Київ-Центральний)";
    }

    public int getPaidRequestsCount() {
        return paidRequestsCount;
    }
}