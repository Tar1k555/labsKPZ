namespace DeliveryService.Task4_Adapter
{
    public class LocalScales : ILocalScales
    {
        public decimal GetWeightKg() => 5.0m;
        public decimal GetLengthCm() => 50.0m;
    }
}
