namespace PosylkaPlus.Delivery.Task1;

/// <summary>
/// Роль у шаблоні: ConcreteClass (Вручення у відділенні).
/// </summary>
public sealed class BranchDeliveryProcess : DeliveryProcessTemplate
{
    private readonly string _branchCode;

    public BranchDeliveryProcess(string branchCode)
    {
        _branchCode = branchCode;
    }

    protected override string ChannelName => $"Відділення {_branchCode}";

    protected override void IdentifyRecipient(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Ідентифікація отримувача] Оператор відділення перевірив документ і назвав отримувача: {shipment.RecipientName}."
        );
    }

    protected override void ObtainConfirmation(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Підтвердження] Отримано підпис {shipment.RecipientName} у журналі видачі відділення."
        );
    }
}
