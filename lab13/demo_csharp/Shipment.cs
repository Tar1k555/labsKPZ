namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public class Shipment
    {
        public string TrackingNumber { get; set; }
        public bool IsInternational { get; set; }
        public double Weight { get; set; }
        public bool IsInvoiceFilled { get; set; }
        public bool ContainsProhibitedItems { get; set; }
        public bool ContainsLithiumBatteries { get; set; }
        public double DimensionsM3 { get; set; }
    }
}
