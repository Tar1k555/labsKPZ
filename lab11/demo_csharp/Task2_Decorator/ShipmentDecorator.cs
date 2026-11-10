namespace DeliveryService.Task2_Decorator
{
    public abstract class ShipmentDecorator : IShipment
    {
        protected readonly IShipment _shipment;

        protected ShipmentDecorator(IShipment shipment)
        {
            _shipment = shipment;
        }

        public virtual string GetDescription() => _shipment.GetDescription();
        public virtual decimal GetCost() => _shipment.GetCost();
    }
}
