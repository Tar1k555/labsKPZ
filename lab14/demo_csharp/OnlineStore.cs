using System;

namespace PosylkaPlus.Task1_Observer
{
    public class OnlineStore : IObserver
    {
        public string Name => "Інтернет-магазин-відправник";

        public void Update(string trackingNumber, string status)
        {
            Console.WriteLine($"[12:00:02] [{Name}] API-синхронізація: посилка {trackingNumber} оновила статус на «{status}».");
        }
    }
}
