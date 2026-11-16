using System;

namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public class ProhibitedItemsCheck : BaseShipmentHandler
    {
        public override void Handle(Shipment shipment)
        {
            Console.WriteLine($"[10:00:02] [ProhibitedItemsCheck] Перевірка на заборонені предмети для {shipment.TrackingNumber}...");
            if (shipment.ContainsProhibitedItems)
            {
                Console.WriteLine($"[10:00:02] [ProhibitedItemsCheck] ВІДХИЛЕНО: Виявлено заборонені до перевезення предмети.");
                return;
            }
            base.Handle(shipment);
        }
    }
}
