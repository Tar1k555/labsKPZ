using System;

namespace PosylkaPlus.Task1_Observer
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
            Console.WriteLine("=== ЗАВДАННЯ 1: Відстеження статусу відправлення (Observer) ===\n");

            var shipment = new Shipment("TRK-77788", "Прийнято");

            var sender = new Sender();
            var receiver = new Receiver();
            var accounting = new AccountingDepartment();
            var onlineStore = new OnlineStore();

            Console.WriteLine("--- Підписка базових спостерігачів ---");
            shipment.Attach(sender);
            shipment.Attach(receiver);
            shipment.Attach(accounting);

            Console.WriteLine("\n--- Зміна статусу: В дорозі ---");
            shipment.Status = "В дорозі";

            Console.WriteLine("\n--- Підписка інтернет-магазину та зміна статусу: Вручено ---");
            shipment.Attach(onlineStore);
            shipment.Status = "Вручено";

            Console.WriteLine("\n--- Відписка отримувача та подальша зміна статусу ---");
            shipment.Detach(receiver);
            shipment.Status = "Архівне";

            Console.WriteLine();
        }
    }
}
