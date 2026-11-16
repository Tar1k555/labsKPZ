namespace Variant3_FitnessMembership.Infrastructure
{
    using System;
    using Variant3_FitnessMembership.Domain;
    using Variant3_FitnessMembership.Interfaces;

    public class SqlSubscriptionRepository : ISubscriptionRepository
    {
        public void Save(Subscription subscription)
        {
            Console.WriteLine($"[База даних] Абонемент #{subscription.Id} успішно додано до таблиці subscriptions.");
        }
    }
}
