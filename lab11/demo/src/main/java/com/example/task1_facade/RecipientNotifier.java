package com.example.task1_facade;

import com.example.util.ConsoleLogger;

public class RecipientNotifier {
    public void sendNotification(String recipientPhone, String waybillNum) {
        ConsoleLogger.log("RecipientNotifier", "Надіслано SMS на номер " + recipientPhone + ": 'Вашу посилку " + waybillNum + " зареєстровано'");
    }
}