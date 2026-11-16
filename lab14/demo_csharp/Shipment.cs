using System;
using System.Collections.Generic;

namespace PosylkaPlus.Task1_Observer
{
    public class Shipment : ISubject
    {
        private readonly List<IObserver> _observers = new List<IObserver>();
        public string TrackingNumber { get; }
        private string _status;

        public string Status
        {
            get => _status;
            set
            {
                _status = value;
                Console.WriteLine($"[12:00:00] [Shipment-{TrackingNumber}] Статус змінився на: «{_status}»");
                Notify();
            }
        }

        public Shipment(string trackingNumber, string initialStatus)
        {
            TrackingNumber = trackingNumber;
            _status = initialStatus;
        }

        public void Attach(IObserver observer)
        {
            _observers.Add(observer);
            Console.WriteLine($"[System] Додано спостерігача: {observer.Name} до відправлення {TrackingNumber}");
        }

        public void Detach(IObserver observer)
        {
            _observers.Remove(observer);
            Console.WriteLine($"[System] Видалено спостерігача: {observer.Name} з відправлення {TrackingNumber}");
        }

        public void Notify()
        {
            foreach (var observer in _observers)
            {
                observer.Update(TrackingNumber, _status);
            }
        }
    }
}
