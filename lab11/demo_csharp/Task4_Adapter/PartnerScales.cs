using System;

namespace DeliveryService.Task4_Adapter
{
    public class PartnerScales
    {
        public double MeasureWeightInPounds()
        {
            double weightLbs = 11.0231;
            Console.WriteLine($"[12:00:00] [PartnerScales] Отримано сирі дані ваги: {weightLbs} фунтів");
            return weightLbs;
        }

        public double MeasureLengthInInches()
        {
            double lengthInches = 19.685;
            Console.WriteLine($"[12:00:00] [PartnerScales] Отримано сирі дані довжини: {lengthInches} дюймів");
            return lengthInches;
        }
    }
}
