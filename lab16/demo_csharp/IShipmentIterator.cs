namespace PosylkaPlus.Task2;

public interface IShipmentIterator
{
    string TraversalName { get; }
    bool HasNext();
    ShipmentItem Next();
}
