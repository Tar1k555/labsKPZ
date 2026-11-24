namespace PosylkaPlus.Task2;

public sealed class UnloadingOrderIterator : IShipmentIterator
{
    private readonly IReadOnlyList<ShipmentItem> _items;
    private int _position;

    public string TraversalName => "Порядок розвантаження";

    public UnloadingOrderIterator(VehicleCargo cargo)
    {
        _items = cargo.CollectAllInUnloadingOrder();
        _position = 0;
    }

    public bool HasNext() => _position < _items.Count;

    public ShipmentItem Next()
    {
        if (!HasNext())
        {
            throw new InvalidOperationException("Елементи для обходу закінчилися.");
        }
        return _items[_position++];
    }
}
