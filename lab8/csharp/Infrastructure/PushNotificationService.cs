namespace Variant3_FitnessMembership.Infrastructure
{
    using System;
    using Variant3_FitnessMembership.Interfaces;

    public class PushNotificationService : INotificationService
    {
        public void SendPushNotification(string recipientName, string message)
        {
            Console.WriteLine($"[Push Notification] Повідомлення для {recipientName}: {message}");
        }
    }
}
