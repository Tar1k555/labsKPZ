namespace Variant3_FitnessMembership.Controllers
{
    using Variant3_FitnessMembership.Domain;
    using Variant3_FitnessMembership.Interfaces;

    public class SubscriptionController
    {
        private readonly ISubscriptionRepository _repository;
        private readonly INotificationService _notificationService;

        public SubscriptionController(ISubscriptionRepository repository, INotificationService notificationService)
        {
            _repository = repository;
            _notificationService = notificationService;
        }

        public string RegisterSubscription(string clientName, string planCode)
        {
            Member member = new Member(42, clientName, "+380509998877", hasDiscountCard: true);
            MembershipPlan plan = new MembershipPlan(planCode, "Річний абонемент", 1200m, 12);

            Subscription subscription = new Subscription(1002, member, plan, personalTrainerIncluded: true, startDate: "2026-11-01");

            _repository.Save(subscription);
            _notificationService.SendPushNotification(member.Name, "Ваш абонемент активовано!");

            return subscription.GenerateContract();
        }
    }
}
