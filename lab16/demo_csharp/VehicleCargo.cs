namespace PosylkaPlus.Task2;

public sealed class VehicleCargo //колекшн, приховує вкладену структуру
{
    private readonly List<Pallet> _pallets = new();

    public void AddPallet(Pallet pallet)
    {
        _pallets.Add(pallet);
        EventLogger.Log("VehicleCargo", $"Завантажено палету {pallet.PalletCode} до автомобіля.");
    }

    internal IReadOnlyList<ShipmentItem> CollectAllInLoadingOrder() //збір елементів
    {
        var result = new List<ShipmentItem>();
        foreach (var pallet in _pallets)
        {
            foreach (var bag in pallet.Bags)
            {
                CollectFromBag(bag, result);
            }
        }
        return result;
    }

    internal IReadOnlyList<ShipmentItem> CollectAllInUnloadingOrder()
    {
        var result = new List<ShipmentItem>();
        for (int i = _pallets.Count - 1; i >= 0; i--)
        {
            var bags = _pallets[i].Bags;
            for (int j = bags.Count - 1; j >= 0; j--)
            {
                CollectFromBagReversed(bags[j], result);
            }
        }
        return result;
    }

    private static void CollectFromBag(MailBag bag, List<ShipmentItem> target)
    {
        target.AddRange(bag.Items);
        foreach (var nested in bag.NestedBags)
        {
            CollectFromBag(nested, target);
        }
    }

    private static void CollectFromBagReversed(MailBag bag, List<ShipmentItem> target)
    {
        for (int i = bag.NestedBags.Count - 1; i >= 0; i--)
        {
            CollectFromBagReversed(bag.NestedBags[i], target);
        }
        for (int i = bag.Items.Count - 1; i >= 0; i--)
        {
            target.Add(bag.Items[i]);
        }
    }

    public IShipmentIterator CreateRouteOrderIterator() => new RouteOrderIterator(this);
    public IShipmentIterator CreateUnloadingOrderIterator() => new UnloadingOrderIterator(this);
    public IShipmentIterator CreateDeclaredValueIterator() => new DeclaredValueIterator(this);
}
