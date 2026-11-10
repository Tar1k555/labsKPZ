namespace DeliveryService.Task2_Decorator
{
    public class InsuranceDecorator : ShipmentDecorator
    {
        public InsuranceDecorator(IShipment shipment) : base(shipment) { }

        public override string GetDescription() => $"{_shipment.GetDescription()}, Страхування";
        public override decimal GetCost() => _shipment.GetCost() + 50.00m;
    }
}
