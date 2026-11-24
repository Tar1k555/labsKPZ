namespace PosylkaPlus.Task2;

public sealed class DeclaredValueIterator : IShipmentIterator //обхід лише елементів з оголошеною цінністю
{
    private readonly List<ShipmentItem> _valuableItems;
    private int _position;

    public string TraversalName => "Інвентаризація (оголошена цінність)";

    public DeclaredValueIterator(VehicleCargo cargo)
    {
        _valuableItems = cargo.CollectAllInLoadingOrder()
            .Where(item => item.DeclaredValue > 0m)
            .ToList();
        _position = 0;
    }

    public bool HasNext() => _position < _valuableItems.Count;

    public ShipmentItem Next()
    {
        if (!HasNext())
        {
            throw new InvalidOperationException("Елементи для обходу закінчилися.");
        }
        return _valuableItems[_position++];
    }
}
