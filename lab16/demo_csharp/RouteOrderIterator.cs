namespace PosylkaPlus.Task2;

public sealed class RouteOrderIterator : IShipmentIterator //конкріт ітератора, який обходить елементи у порядку маршруту доставки
{
    private readonly List<ShipmentItem> _orderedItems;
    private int _position;

    public string TraversalName => "Маршрут кур'єра";

    public RouteOrderIterator(VehicleCargo cargo)
    {
        _orderedItems = cargo.CollectAllInLoadingOrder()
            .OrderBy(item => item.RouteStopOrder)
            .ToList();
        _position = 0;
    }

    public bool HasNext() => _position < _orderedItems.Count;

    public ShipmentItem Next()
    {
        if (!HasNext())
        {
            throw new InvalidOperationException("Елементи для обходу закінчилися.");
        }
        return _orderedItems[_position++];
    }
}
