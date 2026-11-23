namespace PosylkaPlus.Delivery.Task1;

/// <summary>
/// Роль у шаблоні: AbstractClass (Абстрактний клас із шаблонним методом вручення).
/// </summary>
public abstract class DeliveryProcessTemplate
{
    protected abstract string ChannelName { get; }

    // Шаблонний метод: фіксує порядок кроків і не перевизначається у нащадках.
    public void Deliver(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[TemplateMethod: Deliver] Початок процедури вручення відправлення {shipment.TrackingNumber}."
        );

        IdentifyRecipient(shipment);
        VerifyPayment(shipment);

        if (AllowContentInspection(shipment))
        {
            InspectContent(shipment);
        }

        HandOverShipment(shipment);
        ObtainConfirmation(shipment);
        CloseWaybill(shipment);

        EventLogger.Log(
            ChannelName,
            $"[TemplateMethod: Deliver] Завершено процедуру вручення відправлення {shipment.TrackingNumber}."
        );
    }

    protected abstract void IdentifyRecipient(Shipment shipment);

    // Спільна логіка перевірки оплати (зокрема накладеного платежу) для всіх способів.
    protected virtual void VerifyPayment(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Перевірка оплати] Перевірка статусу оплати для {shipment.TrackingNumber} (накладений платіж: {shipment.CashOnDeliveryAmount} грн)."
        );

        if (shipment.CashOnDeliveryAmount > 0m && !shipment.IsPaid)
        {
            EventLogger.Log(
                ChannelName,
                $"[Крок: Перевірка оплати] Стягнення накладеного платежу {shipment.CashOnDeliveryAmount} грн за {shipment.TrackingNumber}."
            );
            shipment.MarkAsPaid();
        }

        if (!shipment.IsPaid)
        {
            throw new InvalidOperationException($"Відправлення {shipment.TrackingNumber} не оплачене.");
        }

        EventLogger.Log(
            ChannelName,
            $"[Крок: Перевірка оплати] Оплату за {shipment.TrackingNumber} підтверджено."
        );
    }

    // Hook-метод: за замовчуванням огляд вмісту дозволено, але може бути змінений підкласом.
    protected virtual bool AllowContentInspection(Shipment shipment) => true;

    // Hook-метод з реалізацією за замовчуванням (необов'язковий етап).
    protected virtual void InspectContent(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок (Hook): Огляд вмісту] Отримувач {shipment.RecipientName} оглянув вміст відправлення {shipment.TrackingNumber}."
        );
    }

    protected virtual void HandOverShipment(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Видача відправлення] Відправлення {shipment.TrackingNumber} передано отримувачу {shipment.RecipientName}."
        );
    }

    protected abstract void ObtainConfirmation(Shipment shipment);

    protected virtual void CloseWaybill(Shipment shipment)
    {
        EventLogger.Log(
            ChannelName,
            $"[Крок: Закриття накладної] Накладну за відправленням {shipment.TrackingNumber} закрито в системі."
        );
    }
}
