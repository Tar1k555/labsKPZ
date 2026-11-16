using System;

namespace Variant3_NotificationService.Task2_OCP
{
    public class EmailNotificationSender : INotificationSender
    {
        public bool Send(NotificationMessage message)
        {
            Console.WriteLine($"[EMAIL] To: {message.Recipient}");
            Console.WriteLine($"[EMAIL] Subject: {message.Subject}");
            Console.WriteLine($"[EMAIL] Body: {message.Body}");
            Console.WriteLine($"[EMAIL] Статус: Відправлено через SMTP сервер.");
            return true;
        }
    }
}
