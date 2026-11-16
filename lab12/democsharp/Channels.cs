using System;

namespace PosylkaPlus.Task2
{
    public class SmsChannel : INotificationChannel
    {
        public string ChannelName => "SMS";

        public void SendMessage(string recipient, string subject, string body)
        {
            Console.WriteLine($"[12:05:01] [SmsGateway] Кому: {recipient} | Тема: {subject} | Текст: {body}");
        }
    }

    public class ViberChannel : INotificationChannel
    {
        public string ChannelName => "Viber";

        public void SendMessage(string recipient, string subject, string body)
        {
            Console.WriteLine($"[12:05:02] [ViberBot] Кому: {recipient} | [{subject}] {body}");
        }
    }

    public class EmailChannel : INotificationChannel
    {
        public string ChannelName => "E-mail";

        public void SendMessage(string recipient, string subject, string body)
        {
            Console.WriteLine($"[12:05:03] [MailServer] Кому: {recipient} | Subject: {subject} | Body: {body}");
        }
    }

    public class TelegramChannel : INotificationChannel
    {
        public string ChannelName => "Telegram";

        public void SendMessage(string recipient, string subject, string body)
        {
            Console.WriteLine($"[12:05:04] [TelegramAPI] Користувач: {recipient} | **{subject}**: {body}");
        }
    }
}
