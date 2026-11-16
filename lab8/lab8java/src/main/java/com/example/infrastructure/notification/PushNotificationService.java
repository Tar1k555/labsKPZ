package com.example.infrastructure.notification;

public class PushNotificationService implements INotificationService {
    @Override
    public void sendNotification(String recipientName, String message) {
        System.out.println("[Push Notification] Повідомлення надіслано на пристрій клієнта " + recipientName + ": " + message);
    }
}