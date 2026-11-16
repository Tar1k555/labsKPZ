using System;

namespace PosylkaPlus.Task2
{
    public class Task2Runner
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            Run();
        }

        public static void Run()
        {
            Console.WriteLine("--- ЗАВАНТАЖЕННЯ ЗАВДАННЯ 2 (BRIDGE) ---");

            INotificationChannel sms = new SmsChannel();
            INotificationChannel viber = new ViberChannel();
            INotificationChannel email = new EmailChannel();

            Notification arrivalSms = new ArrivalNotification(sms);
            arrivalSms.Notify("+380501234567", "TRK-10025");

            Notification arrivalEmail = new ArrivalNotification(email);
            arrivalEmail.Notify("client@example.com", "TRK-10025");

            Notification delayViber = new DelayNotification(viber, "складні погодні умови на трасі");
            delayViber.Notify("+380679876543", "TRK-10089");

            INotificationChannel telegram = new TelegramChannel();
            Notification paymentTelegram = new PaymentNotification(telegram, 450.00m);
            paymentTelegram.Notify("@olena_k", "TRK-10112");

            Console.WriteLine();
        }
    }
}
