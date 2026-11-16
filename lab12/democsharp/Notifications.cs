using System;

namespace PosylkaPlus.Task2
{
    public class ArrivalNotification : Notification
    {
        public ArrivalNotification(INotificationChannel channel) : base(channel) { }

        public override void Notify(string recipient, string trackingNumber)
        {
            Console.WriteLine($"[12:05:00] [NotificationService] Формування сповіщення про прибуття ({trackingNumber}) для каналу {Channel.ChannelName}");
            Channel.SendMessage(
                recipient,
                "Прибуття у відділення",
                $"Ваше відправлення {trackingNumber} прибуло у відділення «Посилка+» і готове до видачі."
            );
        }
    }

    public class DelayNotification : Notification
    {
        private readonly string _reason;

        public DelayNotification(INotificationChannel channel, string reason) : base(channel)
        {
            _reason = reason;
        }

        public override void Notify(string recipient, string trackingNumber)
        {
            Console.WriteLine($"[12:05:00] [NotificationService] Формування сповіщення про затримку ({trackingNumber}) для каналу {Channel.ChannelName}");
            Channel.SendMessage(
                recipient,
                "Затримка доставки",
                $"Доставка відправлення {trackingNumber} затримується. Причина: {_reason}."
            );
        }
    }

    public class PaymentNotification : Notification
    {
        private readonly decimal _amount;

        public PaymentNotification(INotificationChannel channel, decimal amount) : base(channel)
        {
            _amount = amount;
        }

        public override void Notify(string recipient, string trackingNumber)
        {
            Console.WriteLine($"[12:05:00] [NotificationService] Формування сповіщення про оплату ({trackingNumber}) для каналу {Channel.ChannelName}");
            Channel.SendMessage(
                recipient,
                "Необхідність оплати",
                $"Для отримання посилки {trackingNumber} необхідно сплатити {_amount} грн."
            );
        }
    }
}
