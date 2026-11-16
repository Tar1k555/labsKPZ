using System;

namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public class Task1Runner
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            Run();
        }

        public static void Run()
        {
            Console.WriteLine("=== ЗАВДАННЯ 1: Ланцюжок перевірок відправлення (Chain of Responsibility) ===");

            var invoiceCheck = new InvoiceCheck();
            var prohibitedCheck = new ProhibitedItemsCheck();
            var weightCheck = new WeightAndSizeCheck(30.0);
            var internationalCheck = new InternationalCheck();
            var lithiumCheck = new LithiumBatteryCheck();

            invoiceCheck.SetNext(prohibitedCheck)
                        .SetNext(weightCheck)
                        .SetNext(internationalCheck)
                        .SetNext(lithiumCheck);

            Console.WriteLine("\n--- Сценарій 1: Коректне міжнародне відправлення ---");
            var shipment1 = new Shipment
            {
                TrackingNumber = "TRK-9001",
                IsInternational = true,
                Weight = 12.5,
                IsInvoiceFilled = true,
                ContainsProhibitedItems = false,
                ContainsLithiumBatteries = false
            };
            invoiceCheck.Handle(shipment1);

            Console.WriteLine("\n--- Сценарій 2: Перевищення ваги (45 кг) ---");
            var shipment2 = new Shipment
            {
                TrackingNumber = "TRK-9002",
                IsInternational = false,
                Weight = 45.0,
                IsInvoiceFilled = true,
                ContainsProhibitedItems = false
            };
            invoiceCheck.Handle(shipment2);

            Console.WriteLine("\n--- Сценарій 3: Перевірка літієвих батарей у міжнародному відправленні ---");
            var shipment3 = new Shipment
            {
                TrackingNumber = "TRK-9003",
                IsInternational = true,
                Weight = 5.0,
                IsInvoiceFilled = true,
                ContainsProhibitedItems = false,
                ContainsLithiumBatteries = true
            };
            invoiceCheck.Handle(shipment3);

            Console.WriteLine();
        }
    }
}
