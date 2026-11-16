using System;

namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public class LithiumBatteryCheck : BaseShipmentHandler
    {
        public override void Handle(Shipment shipment)
        {
            Console.WriteLine($"[10:00:05] [LithiumBatteryCheck] Перевірка на наявність літієвих батарей у {shipment.TrackingNumber}...");
            if (shipment.ContainsLithiumBatteries && shipment.IsInternational)
            {
                Console.WriteLine($"[10:00:05] [LithiumBatteryCheck] ВІДХИЛЕНО: Літієві батареї потребують спеціальної декларації для міжнародних рейсів.");
                return;
            }
            Console.WriteLine($"[10:00:05] [LithiumBatteryCheck] Перевірку батарей успішно пройдено.");
            base.Handle(shipment);
        }
    }
}
