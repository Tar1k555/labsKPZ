namespace DeliveryService.Task2_Decorator
{
    public class PackagingDecorator : ShipmentDecorator
    {
        public PackagingDecorator(IShipment shipment) : base(shipment) { }

        public override string GetDescription() => $"{_shipment.GetDescription()}, Пакування";
        public override decimal GetCost() => _shipment.GetCost() + 30.00m;
    }
}
