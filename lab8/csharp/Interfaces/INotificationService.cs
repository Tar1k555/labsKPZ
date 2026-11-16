namespace Variant3_FitnessMembership.Interfaces
{
    public interface INotificationService
    {
        void SendPushNotification(string recipientName, string message);
    }
}
