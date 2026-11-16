using System;

namespace Variant3_NotificationService.Task2_OCP
{
    public class PushNotificationSender : INotificationSender
    {
        public bool Send(NotificationMessage message)
        {
            Console.WriteLine($"[PUSH] DeviceToken: {message.Recipient}");
            Console.WriteLine($"[PUSH] Payload: {{ \"title\": \"{message.Subject}\", \"body\": \"{message.Body}\" }}");
            Console.WriteLine($"[PUSH] Статус: Доставлено через Firebase Cloud Messaging.");
            return true;
        }
    }
}
