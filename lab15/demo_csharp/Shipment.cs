namespace PosylkaPlus.Delivery.Task1;

public sealed class Shipment
{
    public string TrackingNumber { get; }
    public string RecipientName { get; }
    public bool IsPaid { get; private set; }
    public decimal CashOnDeliveryAmount { get; }

    public Shipment(string trackingNumber, string recipientName, bool isPaid, decimal cashOnDeliveryAmount = 0m)
    {
        TrackingNumber = trackingNumber;
        RecipientName = recipientName;
        IsPaid = isPaid;
        CashOnDeliveryAmount = cashOnDeliveryAmount;
    }

    public void MarkAsPaid()
    {
        IsPaid = true;
    }
}
