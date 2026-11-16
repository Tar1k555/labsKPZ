using System;

namespace PosylkaPlus.Task1_Observer
{
    public class Receiver : IObserver
    {
        public string Name => "Отримувач";

        public void Update(string trackingNumber, string status)
        {
            Console.WriteLine($"[12:00:01] [{Name}] Отримано сповіщення: ваше відправлення {trackingNumber} тепер має статус «{status}».");
        }
    }
}
