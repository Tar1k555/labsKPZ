using System;

namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public class InternationalCheck : BaseShipmentHandler
    {
        public override void Handle(Shipment shipment)
        {
            if (shipment.IsInternational)
            {
                Console.WriteLine($"[10:00:04] [InternationalCheck] Перевірка митних вимог для міжнародного відправлення {shipment.TrackingNumber}...");
            }
            else
            {
                Console.WriteLine($"[10:00:04] [InternationalCheck] Відправлення внутрішнє, митний контроль пропущено.");
            }
            base.Handle(shipment);
        }
    }
}
