namespace PosylkaPlus.Task2
{
    public abstract class Notification
    {
        protected INotificationChannel Channel;

        protected Notification(INotificationChannel channel)
        {
            Channel = channel;
        }

        public void SetChannel(INotificationChannel channel)
        {
            Channel = channel;
        }

        public abstract void Notify(string recipient, string trackingNumber);
    }
}
