using System;

namespace DeliveryApp.Factories
{
    // Результат розрахунку доставки
    public class DeliveryResult
    {
        public string MethodName { get; set; }
        public decimal Cost { get; set; }
        public int Days { get; set; }
        public string Destination { get; set; }

        public override string ToString() => $"[Delivery] Спосіб: {MethodName} | Адреса/Пункт: {Destination} | Вартість: {Cost} грн | Строк: {Days} дн.";
    }

    // Абстрактний творчий клас (процес оформлення)
    public abstract class ShipmentProcessor
    {
        // Фабричний метод
        public abstract DeliveryResult CalculateSpecificDelivery(string destination);

        // Загальний процес оформлення доставки (описаний один раз)
        public void ProcessDelivery(string shipmentId, string destination)
        {
            Console.WriteLine($"[10:05] [DeliveryProcess] Перевірка адреси для {shipmentId}...");

            DeliveryResult result = CalculateSpecificDelivery(destination);

            Console.WriteLine($"[10:05] [DeliveryProcess] Призначення виконавця та реєстрація запису...");
            Console.WriteLine($"[10:05] [DeliveryJournal] Успішно оформлено! Деталі: {result}");
        }
    }

    // --- Кур'єрська доставка ---
    public class CourierDeliveryProcessor : ShipmentProcessor
    {
        public override DeliveryResult CalculateSpecificDelivery(string destination)
        {
            return new DeliveryResult { MethodName = "Кур'єрська доставка", Cost = 150.0m, Days = 1, Destination = destination };
        }
    }

    // --- Доставка у поштомат ---
    public class PostmatDeliveryProcessor : ShipmentProcessor
    {
        public override DeliveryResult CalculateSpecificDelivery(string destination)
        {
            return new DeliveryResult { MethodName = "Поштомат", Cost = 70.0m, Days = 2, Destination = destination };
        }
    }

    // --- Доставка у відділення ---
    public class BranchDeliveryProcessor : ShipmentProcessor
    {
        public override DeliveryResult CalculateSpecificDelivery(string destination)
        {
            return new DeliveryResult { MethodName = "Відділення", Cost = 50.0m, Days = 2, Destination = destination };
        }
    }

    // --- Новий спосіб: Доставка на поверх (згідно з вимогою) ---
    public class FloorDeliveryProcessor : ShipmentProcessor
    {
        public override DeliveryResult CalculateSpecificDelivery(string destination)
        {
            return new DeliveryResult { MethodName = "Доставка на поверх", Cost = 250.0m, Days = 1, Destination = destination };
        }
    }

    // Обгортки-творці (Creators)
    public class CourierDeliveryCreator : ShipmentProcessor { public override DeliveryResult CalculateSpecificDelivery(string destination) => new CourierDeliveryProcessor().CalculateSpecificDelivery(destination); }
    public class PostmatDeliveryCreator : ShipmentProcessor { public override DeliveryResult CalculateSpecificDelivery(string destination) => new PostmatDeliveryProcessor().CalculateSpecificDelivery(destination); }
    public class BranchDeliveryCreator : ShipmentProcessor { public override DeliveryResult CalculateSpecificDelivery(string destination) => new BranchDeliveryProcessor().CalculateSpecificDelivery(destination); }
    public class FloorDeliveryCreator : ShipmentProcessor { public override DeliveryResult CalculateSpecificDelivery(string destination) => new FloorDeliveryProcessor().CalculateSpecificDelivery(destination); }
}
