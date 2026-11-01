using System;

namespace DeliverySystem.Models
{
    public sealed class InvoiceNumberService
    {
        private static readonly Lazy<InvoiceNumberService> _instance =
            new Lazy<InvoiceNumberService>(() => new InvoiceNumberService());

        private int _counter = 1000;
        private readonly string _serviceId = "SNS-CORE-01";

        private InvoiceNumberService()
        {
            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] [InvoiceService] Службу нумерації ініціалізовано");
        }

        public static InvoiceNumberService Instance => _instance.Value;

        public string ServiceId => _serviceId;

        public string GetNextInvoiceNumber(string branchName)
        {
            int number;
            lock (this)
            {
                number = ++_counter;
            }
            return $"INV-{branchName}-{number}";
        }
    }
}
