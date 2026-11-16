using System;

namespace PosylkaPlus.Task1_Observer
{
    public class Sender : IObserver
    {
        public string Name => "Відправник";

        public void Update(string trackingNumber, string status)
        {
            Console.WriteLine($"[12:00:01] [{Name}] Отримано сповіщення: посилка {trackingNumber} змінила статус на «{status}».");
        }
    }
}
