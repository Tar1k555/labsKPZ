namespace Variant3_FitnessMembership.Interfaces
{
    using Variant3_FitnessMembership.Domain;

    public interface ISubscriptionRepository
    {
        void Save(Subscription subscription);
    }
}
