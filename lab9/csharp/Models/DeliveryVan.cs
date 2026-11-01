using System;
using System.Collections.Generic;

namespace DeliverySystem.Models
{
    public class DeliveryVan
    {
        public string VanId { get; }
        public string CurrentCourier { get; private set; }
        public List<string> Cargo { get; private set; }
        public int UsageCount { get; private set; }

        public DeliveryVan(string vanId)
        {
            VanId = vanId;
            Cargo = new List<string>();
            UsageCount = 0;
        }

        public void AssignToCourier(string courierName, List<string> cargoList)
        {
            CurrentCourier = courierName;
            Cargo = new List<string>(cargoList);
            UsageCount++;
            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [VanPool] Фургон {VanId} виділено кур'єру {currentCourierFormatted(courierName)}");
        }

        public void ResetState()
        {
            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [VanPool] Фургон {VanId} повернено, розвантажено та очищено від даних попереднього кур'єра ({CurrentCourier}).");
            CurrentCourier = null;
            Cargo.Clear();
        }

        private string currentCourierFormatted(string name) => name;
    }
}
