using System;

namespace Variant3_NotificationService.Task2_OCP
{
    public class ViberNotificationSender : INotificationSender
    {
        public bool Send(NotificationMessage message)
        {
            Console.WriteLine($"[VIBER] Phone: {message.Recipient}");
            Console.WriteLine($"[VIBER] Message: {message.Body}");
            Console.WriteLine($"[VIBER] Статус: Надіслано через Viber REST API.");
            return true;
        }
    }
}
