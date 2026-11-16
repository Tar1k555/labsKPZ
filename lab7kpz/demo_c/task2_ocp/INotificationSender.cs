namespace Variant3_NotificationService.Task2_OCP
{
    public interface INotificationSender
    {
        bool Send(NotificationMessage message);
    }
}
