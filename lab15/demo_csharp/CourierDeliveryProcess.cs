namespace PosylkaPlus.Delivery.Task1;

/// <summary>
/// Роль у шаблоні: ConcreteClass (Вручення кур'єром).
/// </summary>
public sealed class CourierDeliveryProcess : DeliveryProcessTemplate
{
    private readonly string _courierName;

    public CourierDeliveryProcess(string courierName)
    {
        _courierName = courierName;
    }

    protected override string ChannelName => $"Кур'єр ({_courierName})";

    protected override void IdentifyRecipient(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Ідентифікація отримувача] Кур'єр звірив особу отримувача {shipment.RecipientName} за кодом у застосунку."
        );
    }

    protected override void ObtainConfirmation(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Підтвердження] Отримано ОТР-підтвердження у смартфоні отримувача {shipment.RecipientName}."
        );
    }
}
