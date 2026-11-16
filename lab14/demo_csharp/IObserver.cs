namespace PosylkaPlus.Task1_Observer
{
    public interface IObserver
    {
        string Name { get; }
        void Update(string trackingNumber, string status);
    }
}
