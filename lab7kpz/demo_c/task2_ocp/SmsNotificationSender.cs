using System;

namespace Variant3_NotificationService.Task2_OCP
{
    public class SmsNotificationSender : INotificationSender
    {
        public bool Send(NotificationMessage message)
        {
            string shortText = message.Body.Length > 160 ? message.Body.Substring(0, 157) + "..." : message.Body;
            Console.WriteLine($"[SMS] Phone: {message.Recipient}");
            Console.WriteLine($"[SMS] Text: {shortText}");
            Console.WriteLine($"[SMS] Статус: Надіслано через GSM шлюз.");
            return true;
        }
    }
}
