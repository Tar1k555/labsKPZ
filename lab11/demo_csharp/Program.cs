using System;
using DeliveryService.Task2_Decorator;
using DeliveryService.Task4_Adapter;

namespace DeliveryService
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            RunTask2Demo();
            Console.WriteLine("\n----------------------------------------\n");
            RunTask4Demo();
        }

        static void RunTask2Demo()
        {
            Console.WriteLine("[12:00:00] [System] Запуск демонстрації Завдання 2 (Декоратор)");

            IShipment shipment1 = new StandardShipment();
            Console.WriteLine($"[12:00:01] [Decorator] Опис: {shipment1.GetDescription()} | Вартість: {shipment1.GetCost()} грн");

            IShipment shipment2 = new StandardShipment();
            shipment2 = new InsuranceDecorator(shipment2);
            shipment2 = new PackagingDecorator(shipment2);
            Console.WriteLine($"[12:00:02] [Decorator] Опис: {shipment2.GetDescription()} | Вартість: {shipment2.GetCost()} грн");
        }

        static void RunTask4Demo()
        {
            Console.WriteLine("[12:00:03] [System] Запуск демонстрації Завдання 4 (Адаптер)");

            ILocalScales localScales = new LocalScales();
            Console.WriteLine($"[12:00:04] [System] Старі ваги - Вага: {localScales.GetWeightKg()} кг, Довжина: {localScales.GetLengthCm()} см");

            PartnerScales partnerScales = new PartnerScales();
            ILocalScales adaptedScales = new ScalesAdapter(partnerScales);

            Console.WriteLine($"[12:00:05] [System] Нові адаптовані ваги - Вага: {adaptedScales.GetWeightKg()} кг, Довжина: {adaptedScales.GetLengthCm()} см");
        }
    }
}
