namespace DeliveryService.Task4_Adapter
{
    public class ScalesAdapter : ILocalScales
    {
        private readonly PartnerScales _partnerScales;

        public ScalesAdapter(PartnerScales partnerScales)
        {
            _partnerScales = partnerScales;
        }

        public decimal GetWeightKg()
        {
            double lbs = _partnerScales.MeasureWeightInPounds();
            decimal kg = (decimal)(lbs * 0.45359237);
            return decimal.Round(kg, 2);
        }

        public decimal GetLengthCm()
        {
            double inches = _partnerScales.MeasureLengthInInches();
            decimal cm = (decimal)(inches * 2.54);
            return decimal.Round(cm, 2);
        }
    }
}
