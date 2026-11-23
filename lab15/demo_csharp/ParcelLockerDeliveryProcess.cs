namespace PosylkaPlus.Delivery.Task1;

/// <summary>
/// Роль у шаблоні: ConcreteClass (Вручення через поштомат).
/// </summary>
public sealed class ParcelLockerDeliveryProcess : DeliveryProcessTemplate
{
    private readonly string _lockerId;

    public ParcelLockerDeliveryProcess(string lockerId)
    {
        _lockerId = lockerId;
    }

    protected override string ChannelName => $"Поштомат {_lockerId}";

    protected override void IdentifyRecipient(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Ідентифікація отримувача] Отримувач {shipment.RecipientName} ввів одноразовий код доступу на екрані поштомата."
        );
    }

    // У поштоматі попередній огляд вмісту до відкриття комірки за замовчуванням не виконується.
    protected override bool AllowContentInspection(Shipment shipment) => false;

    protected override void ObtainConfirmation(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Підтвердження] Датчики поштомата зафіксували забір відправлення {shipment.TrackingNumber} та закриття комірки."
        );
    }
}
