using System;
using System.Collections.Generic;
using System.Threading;
using DeliverySystem.Models;

namespace DeliverySystem.Pools
{
    public class VanPool
    {
        private readonly List<DeliveryVan> _availableVans;
        private readonly List<DeliveryVan> _inUseVans;
        private readonly int _maxSize;
        private int _totalCreated = 0;

        public VanPool(int maxSize)
        {
            _maxSize = maxSize;
            _availableVans = new List<DeliveryVan>();
            _inUseVans = new List<DeliveryVan>();

            // Початкове створення фіксованої кількості об'єктів
            for (int i = 1; i <= maxSize; i++)
            {
                var van = new DeliveryVan($"F-{i}");
                _availableVans.Add(van);
                _totalCreated++;
                Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [VanPool] Підготовка та реєстрація фургона {van.VanId}");
                Thread.Sleep(300); // Емуляція підготовки
            }
        }

        public DeliveryVan AcquireVan(string courierName)
        {
            lock (this)
            {
                if (_availableVans.Count > 0)
                {
                    var van = _availableVans[0];
                    _availableVans.RemoveAt(0);
                    _inUseVans.Add(van);
                    return van;
                }
                else
                {
                    Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [VanPool] Немає вільних фургонів для кур'єра {courierName}! Кур'єр стає в чергу очікування...");
                    // Проста стратегія очікування (стратегія черги згідно з вимогою)
                    while (_availableVans.Count == 0)
                    {
                        Monitor.Wait(this);
                    }
                    var van = _availableVans[0];
                    _availableVans.RemoveAt(0);
                    _inUseVans.Add(van);
                    return van;
                }
            }
        }

        public void ReleaseVan(DeliveryVan van)
        {
            lock (this)
            {
                van.ResetState();
                _inUseVans.Remove(van);
                _availableVans.Add(van);
                Monitor.Pulse(this); // Сповістити потоки, що очікують
            }
        }

        public void PrintStatistics()
        {
            Console.WriteLine($"\n--- СТАТИСТИКА ПУЛУ ФУРГОНІВ ---");
            Console.WriteLine($"Загальна кількість створених фургонів (фізичний ліміт): {_totalCreated}");
            foreach (var van in _availableVans)
            {
                Console.WriteLine($"Фургон {van.VanId}: використано разів за день = {van.UsageCount}");
            }
            foreach (var van in _inUseVans)
            {
                Console.WriteLine($"Фургон {van.VanId}: використано разів за день = {van.UsageCount}");
            }
            Console.WriteLine("-------------------------------\n");
        }
    }
}
