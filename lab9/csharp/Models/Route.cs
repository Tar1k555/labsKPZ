using System;
using System.Collections.Generic;
using System.Threading;

namespace DeliverySystem.Models
{
    public class Route : ICloneable
    {
        public string Name { get; set; }
        public string Courier { get; set; }
        public double WeightLimitKg { get; set; }
        public List<string> Stops { get; set; }

        public Route(string name, double weightLimit, List<string> stops)
        {
            Name = name;
            WeightLimitKg = weightLimit;
            Stops = new List<string>(stops);
        }

        protected Route(Route other)
        {
            Name = other.Name;
            Courier = other.Courier;
            WeightLimitKg = other.WeightLimitKg;
            Stops = new List<string>(other.Stops);
        }

        public void LoadExpensiveConfiguration()
        {
            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [RouteBuilder] Початок побудови еталонного маршруту '{Name}'...");
            Thread.Sleep(1500);
            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [RouteBuilder] Маршрут '{Name}' успішно побудовано та завантажено в пам'ять.");
        }

        public object Clone()
        {
            return new Route(this);
        }

        public void PrintInfo()
        {
            Console.WriteLine($"-> Маршрут: {Name} | Кур'єр: {(string.IsNullOrEmpty(Courier) ? "Не призначено" : Courier)} | Ліміт ваги: {WeightLimitKg} кг | Зупинки: [{string.Join(", ", Stops)}]");
        }
    }
}
