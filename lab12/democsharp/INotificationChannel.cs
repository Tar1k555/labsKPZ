namespace PosylkaPlus.Task2
{
    public interface INotificationChannel
    {
        string ChannelName { get; }
        void SendMessage(string recipient, string subject, string body);
    }
}
