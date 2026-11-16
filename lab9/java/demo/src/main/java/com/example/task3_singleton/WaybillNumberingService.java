package com.example.task3_singleton;

import com.example.util.ConsoleLogger;
import java.util.concurrent.atomic.AtomicLong;

public class WaybillNumberingService {
    private static volatile WaybillNumberingService instance;
    private final AtomicLong counter;

    private WaybillNumberingService() {
        ConsoleLogger.log("WaybillService", "Службу нумерації ініціалізовано.");
        this.counter = new AtomicLong(1000);
    }

    public static WaybillNumberingService getInstance() {
        if (instance == null) {
            synchronized (WaybillNumberingService.class) {
                if (instance == null) {
                    instance = new WaybillNumberingService();
                }
            }
        }
        return instance;
    }

    public String generateNextNumber(String sourceName) {
        long nextId = counter.incrementAndGet();
        String waybill = "WB-" + nextId;
        ConsoleLogger.log(sourceName, "Згенеровано накладну: " + waybill);
        return waybill;
    }
}