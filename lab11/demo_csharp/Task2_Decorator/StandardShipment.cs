namespace DeliveryService.Task2_Decorator
{
    public class StandardShipment : IShipment
    {
        public string GetDescription() => "Стандартне відправлення";
        public decimal GetCost() => 100.00m;
    }
}
