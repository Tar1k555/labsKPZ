package com.example.task1_facade;

import com.example.util.ConsoleLogger;

public class WaybillGenerator {
    public String generate(String sender, String recipient) {
        String waybillNum = "WB-" + (int)(Math.random() * 900000 + 100000);
        ConsoleLogger.log("WaybillGenerator", "Згенеровано експрес-накладну " + waybillNum + " (" + sender + " -> " + recipient + ")");
        return waybillNum;
    }
}