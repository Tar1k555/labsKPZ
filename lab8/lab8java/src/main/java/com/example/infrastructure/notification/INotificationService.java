package com.example.infrastructure.notification;

public interface INotificationService {
    void sendNotification(String recipientName, String message);
}