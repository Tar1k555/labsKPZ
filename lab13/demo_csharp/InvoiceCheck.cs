using System;

namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public class InvoiceCheck : BaseShipmentHandler
    {
        public override void Handle(Shipment shipment)
        {
            Console.WriteLine($"[10:00:01] [InvoiceCheck] Перевірка заповненості накладної для {shipment.TrackingNumber}...");
            if (!shipment.IsInvoiceFilled)
            {
                Console.WriteLine($"[10:00:01] [InvoiceCheck] ВІДХИЛЕНО: Накладну заповнено некоректно або не повністю.");
                return;
            }
            base.Handle(shipment);
        }
    }
}
