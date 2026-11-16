using System;

namespace Variant3_NotificationService.Task2_OCP
{
    public class TelegramNotificationSender : INotificationSender
    {
        public bool Send(NotificationMessage message)
        {
            Console.WriteLine($"[TELEGRAM] ChatId: {message.Recipient}");
            Console.WriteLine($"[TELEGRAM] Message: *{message.Subject}*\n{message.Body}");
            Console.WriteLine($"[TELEGRAM] Статус: Надіслано через Bot API.");
            return true;
        }
    }
}
