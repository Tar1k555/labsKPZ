namespace Variant3_FitnessMembership
{
    using System;
    using Variant3_FitnessMembership.Controllers;
    using Variant3_FitnessMembership.Infrastructure;
    using Variant3_FitnessMembership.Interfaces;

    class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            ISubscriptionRepository repo = new SqlSubscriptionRepository();
            INotificationService notificationService = new PushNotificationService();
            SubscriptionController controller = new SubscriptionController(repo, notificationService);

            Console.WriteLine("=== Реєстрація абонемента фітнес-клубу ===");
            Console.Write("Введіть ім'я клієнта: ");
            string name = Console.ReadLine();

            Console.Write("Введіть тип абонемента (Day/Full/VIP): ");
            string planType = Console.ReadLine();

            string contract = controller.RegisterSubscription(name, planType);

            Console.WriteLine("\n--- Форма договору ---");
            Console.WriteLine(contract);
        }
    }
}
