using System;
using System.Collections.Generic;
using System.Threading;
using DeliverySystem.Models;
using DeliverySystem.Pools;

namespace DeliverySystem
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            Console.WriteLine("=== ДЕМОНСТРАЦІЯ ЛАБОРАТОРНОЇ РОБОТИ №9 (ВАРІАНТ 3) ===");

            RunTask1Demo();
            RunTask2Demo();
            RunTask3Demo();

            Console.WriteLine("\nВсі демонстраційні сценарії успішно виконано.");
        }

        // 1/Prototype (Маршрути)
        public static void RunTask1Demo()
        {
            Console.WriteLine("\n--- ЗАВДАННЯ 1: Щоденні маршрути доставки (Prototype) ---");

            var referenceRoute = new Route("Чернівці — Хотин — Кельменці", 1500.0, new List<string> { "Склад Центр", "Хотинський ринок", "Кельменці Автостанція" });
            referenceRoute.LoadExpensiveConfiguration();

            Route routeDay1 = (Route)referenceRoute.Clone();
            routeDay1.Name = "Чернівці — Хотин — Кельменці (День 1)";

            Route routeDay2 = (Route)referenceRoute.Clone();
            routeDay2.Name = "Чернівці — Хотин — Кельменці (День 2)";

            Route routeDay3 = (Route)referenceRoute.Clone();
            routeDay3.Name = "Чернівці — Хотин — Кельменці (День 3)";

            routeDay1.Courier = "Іваненко І.І.";
            routeDay1.WeightLimitKg = 1200.0;
            routeDay1.Stops.Add("Додаткова точка: Мамаївці");

            Console.WriteLine("\n[Порівняння результатів]:");
            Console.Write("Еталон: ");
            referenceRoute.PrintInfo();
            Console.Write("Змінений: ");
            routeDay1.PrintInfo();
            Console.WriteLine("Перевірка: Еталон залишився незмінним, вкладені дані ізольовані.");
        }

        //2/Object Pool (Фургони)
        public static void RunTask2Demo()
        {
            Console.WriteLine("\n--- ЗАВДАННЯ 2: Використання службових фургонів (Object Pool) ---");

            var pool = new VanPool(3);
            var couriers = new string[] { "Іваненко", "Петренко", "Сидоренко", "Ковальчук", "Мельник" };

            List<Thread> threads = new List<Thread>();

            foreach (var courier in couriers)
            {
                threads.Add(new Thread(() =>
                {
                    Thread.Sleep(100);
                    var van = pool.AcquireVan(courier);
                    van.AssignToCourier(courier, new List<string> { $"Посилка для {courier}" });

                    Thread.Sleep(800);

                    pool.ReleaseVan(van);
                }));
            }

            foreach (var t in threads) t.Start();
            foreach (var t in threads) t.Join();

            pool.PrintStatistics();
        }

        //3/Singleton (Нумерація накладних)
        public static void RunTask3Demo()
        {
            Console.WriteLine("\n--- ЗАВДАННЯ 3: Нумерація накладних (Singleton) ---");

            var branch1 = InvoiceNumberService.Instance;
            var branch2 = InvoiceNumberService.Instance;
            var onlineCabinet = InvoiceNumberService.Instance;

            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [Test] ID служби з Відділення №1: {branch1.ServiceId}");
            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [Test] ID служби з Відділення №2: {branch2.ServiceId}");
            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [Test] ID служби з Онлайн-кабінету: {onlineCabinet.ServiceId}");
            Console.WriteLine($"Усі ідентифікатори ідентичні: {ReferenceEquals(branch1, branch2)}");

            Console.WriteLine("\nРеєстрація відправлень:");
            for (int i = 1; i <= 3; i++)
            {
                Console.WriteLine($"Відділення №1 реєструє: {branch1.GetNextInvoiceNumber("V1")}");
                Console.WriteLine($"Відділення №2 реєструє: {branch2.GetNextInvoiceNumber("V2")}");
                Console.WriteLine($"Онлайн-кабінет реєструє: {onlineCabinet.GetNextInvoiceNumber("WEB")}");
            }
        }
    }
}
