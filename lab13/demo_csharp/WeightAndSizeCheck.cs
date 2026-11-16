using System;

namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public class WeightAndSizeCheck : BaseShipmentHandler
    {
        private readonly double _maxWeight;

        public WeightAndSizeCheck(double maxWeight = 30.0)
        {
            _maxWeight = maxWeight;
        }

        public override void Handle(Shipment shipment)
        {
            Console.WriteLine($"[10:00:03] [WeightAndSizeCheck] Перевірка ваги (до {_maxWeight} кг) і габаритів для {shipment.TrackingNumber}...");
            if (shipment.Weight > _maxWeight)
            {
                Console.WriteLine($"[10:00:03] [WeightAndSizeCheck] ВІДХИЛЕНО: Вага відправлення становить {shipment.Weight} кг, що перевищує ліміт {_maxWeight} кг.");
                return;
            }
            base.Handle(shipment);
        }
    }
}
