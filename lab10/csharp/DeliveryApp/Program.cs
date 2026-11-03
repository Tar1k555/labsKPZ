using System;
using DeliveryApp.Factories;
using DeliveryApp.Builders;

namespace DeliveryApp
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            Console.WriteLine("=== ДЕМОНСТРАЦІЯ ЗАВДАНЬ (ВАРІАНТ 3: Служба доставки «Посилка+») ===\n");

            // Завдання 1: Пакування відправлень (Abstract Factory)
            RunTask1();

            Console.WriteLine("\n--------------------------------------------------\n");

            // Завдання 2: Способи доставки (Factory Method)
            RunTask2();

            Console.WriteLine("\n--------------------------------------------------\n");

            // Завдання 3: Оформлення накладної (Builder)
            RunTask3();
        }

        static void RunTask1()
        {
            Console.WriteLine("--- ЗАВДАННЯ 1: Пакування відправлень (Abstract Factory) ---");

            PackagingDepartment dept = new PackagingDepartment();

            // 1. Звичайне відправлення
            IDeliveryFactory regularFactory = new RegularPackagingFactory();
            dept.PackShipment(regularFactory, "Звичайне відправлення #101");

            // 2. Крихке відправлення
            IDeliveryFactory fragileFactory = new FragilePackagingFactory();
            dept.PackShipment(fragileFactory, "Крихке відправлення #102");

            // 3. Температурочутливе відправлення
            IDeliveryFactory coldFactory = new ColdPackagingFactory();
            dept.PackShipment(coldFactory, "Холодове відправлення #103");

            // 4. Додавання нового типу «Негабаритне» без зміни коду пакувального місця
            Console.WriteLine("\n[Додавання нового типу «Негабаритне»]:");
            IDeliveryFactory oversizedFactory = new OversizedPackagingFactory();
            dept.PackShipment(oversizedFactory, "Негабаритне відправлення #104");
        }

        static void RunTask2()
        {
            Console.WriteLine("--- ЗАВДАННЯ 2: Способи доставки (Factory Method) ---");

            // Оформлення одного відправлення трьома способами
            ShipmentProcessor courierProcessor = new CourierDeliveryCreator();
            courierProcessor.ProcessDelivery("Відправлення #201", "вул. Хрещатик, 1");

            ShipmentProcessor postmatProcessor = new PostmatDeliveryCreator();
            postmatProcessor.ProcessDelivery("Відправлення #202", "Поштомат #34");

            ShipmentProcessor branchProcessor = new BranchDeliveryCreator();
            branchProcessor.ProcessDelivery("Відправлення #203", "Відділення №5");

            // Додавання способу «Доставка на поверх» новим класом без зміни загального процесу
            Console.WriteLine("\n[Додавання нового способу «Доставка на поверх»]:");
            ShipmentProcessor floorProcessor = new FloorDeliveryCreator();
            floorProcessor.ProcessDelivery("Відправлення #204", "вул. Головна, 15 (пов. 4)");
        }

        static void RunTask3()
        {
            Console.WriteLine("--- ЗАВДАННЯ 3: Оформлення накладної (Builder) ---");

            // 1. Сформувати накладну з оголошеною цінністю, накладеним платежем і страхуванням
            try
            {
                WaybillBuilder builder = new StandardWaybillBuilder();
                WaybillDirector director = new WaybillDirector(builder);

                var waybill = director.ConstructFullWaybill(
                    sender: "ТОВ «Глобал»",
                    receiverName: "Петренко І.І.",
                    receiverPhone: "+380501234567",
                    weight: 2.5,
                    declaredValue: 5000,
                    codAmount: 1200,
                    isInsured: true
                );

                Console.WriteLine($"[10:15] [WaybillSystem] Успішно сформовано повну накладну:\n{waybill}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[10:15] [WaybillSystem] Помилка: {ex.Message}");
            }

            // 2. Сформувати типову накладну «Документи»
            try
            {
                WaybillBuilder builder = new StandardWaybillBuilder();
                WaybillDirector director = new WaybillDirector(builder);

                var docWaybill = director.ConstructDocumentsWaybill(
                    sender: "Юр. компанія «Право»",
                    receiverName: "Ковальчук О.П.",
                    receiverPhone: "+380679876543"
                );

                Console.WriteLine($"\n[10:18] [WaybillSystem] Успішно сформовано типову накладну «Документи»:\n{docWaybill}");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[10:18] [WaybillSystem] Помилка: {ex.Message}");
            }

            // 3. Спробувати завершити формування без телефону отримувача
            try
            {
                Console.WriteLine("\n[10:20] [WaybillSystem] Спроба сформувати накладну без телефону отримувача...");
                var invalidBuilder = new StandardWaybillBuilder();
                invalidBuilder.SetSender("Магазин");
                invalidBuilder.SetReceiver("Іванов І.І.", ""); // Телефон порожній!
                invalidBuilder.SetWeight(1.0);

                var invalidWaybill = invalidBuilder.Build(); // Тут має випасти виняток
            }
            catch (Exception ex)
            {
                Console.WriteLine($"[10:20] [WaybillSystem] Журнал: {ex.Message}");
            }
        }
    }
}
