namespace PosylkaPlus.Task2;

public sealed class ShipmentItem
{
    public string TrackingNumber { get; }
    public int RouteStopOrder { get; }
    public decimal DeclaredValue { get; }
    public string Description { get; }

    public ShipmentItem(string trackingNumber, int routeStopOrder, decimal declaredValue, string description)
    {
        TrackingNumber = trackingNumber;
        RouteStopOrder = routeStopOrder;
        DeclaredValue = declaredValue;
        Description = description;
    }

    public override string ToString()
    {
        string valueInfo = DeclaredValue > 0m ? $"{DeclaredValue} грн" : "без оголошеної цінності";
        return $"{TrackingNumber} (зупинка №{RouteStopOrder}, {valueInfo} — {Description})";
    }
}
