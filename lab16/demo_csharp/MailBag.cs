namespace PosylkaPlus.Task2;

public sealed class MailBag //мішки з потенцційно мішками
{
    public string BagCode { get; }
    private readonly List<ShipmentItem> _items = new();
    private readonly List<MailBag> _nestedBags = new();

    public IReadOnlyList<ShipmentItem> Items => _items;
    public IReadOnlyList<MailBag> NestedBags => _nestedBags;

    public MailBag(string bagCode)
    {
        BagCode = bagCode;
    }

    public void AddItem(ShipmentItem item) => _items.Add(item);

    public void AddNestedBag(MailBag bag) => _nestedBags.Add(bag);
}