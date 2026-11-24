namespace PosylkaPlus.Task2;

public sealed class Task2Runner
{
    public void RunScenario()
    {
        EventLogger.Log("Runner", "=== ЗАВДАННЯ 2: ОБХІД ВІДПРАВЛЕНЬ У МАШИНІ (ITERATOR) ===");

        // Формуємо вкладену структуру: Палети -> Мішки -> Вкладені мішки -> Відправлення
        var cargo = new VehicleCargo();

        var pallet1 = new Pallet("PAL-01");
        var bag1 = new MailBag("BAG-101");
        bag1.AddItem(new ShipmentItem("PKG-201", routeStopOrder: 3, declaredValue: 0m, "Документи"));
        bag1.AddItem(new ShipmentItem("PKG-202", routeStopOrder: 1, declaredValue: 4500m, "Ноутбук"));
        pallet1.AddBag(bag1);

        var pallet2 = new Pallet("PAL-02");
        var outerBag = new MailBag("BAG-201");
        outerBag.AddItem(new ShipmentItem("PKG-203", routeStopOrder: 4, declaredValue: 0m, "Книги"));

        var innerBag = new MailBag("BAG-202-NESTED");
        innerBag.AddItem(new ShipmentItem("PKG-204", routeStopOrder: 2, declaredValue: 12000m, "Смартфон"));
        innerBag.AddItem(new ShipmentItem("PKG-205", routeStopOrder: 5, declaredValue: 800m, "Навушники"));

        outerBag.AddNestedBag(innerBag);
        pallet2.AddBag(outerBag);

        cargo.AddPallet(pallet1);
        cargo.AddPallet(pallet2);

        EventLogger.Log("Runner", "--- Стан до обходу: у кузові 2 палети з вкладеними мішками ---");

        IShipmentIterator routeIterator = cargo.CreateRouteOrderIterator();
        while (routeIterator.HasNext())
        {
            EventLogger.Log(routeIterator.TraversalName, routeIterator.Next().ToString());
        }

        IShipmentIterator unloadingIterator = cargo.CreateUnloadingOrderIterator();
        while (unloadingIterator.HasNext())
        {
            EventLogger.Log(unloadingIterator.TraversalName, unloadingIterator.Next().ToString());
        }

        IShipmentIterator valueIterator = cargo.CreateDeclaredValueIterator();
        IShipmentIterator parallelRouteIterator = cargo.CreateRouteOrderIterator();

        EventLogger.Log("Runner", "--- Демонстрація двох одночасних незалежних обходів ---");
        while (valueIterator.HasNext() || parallelRouteIterator.HasNext())
        {
            if (valueIterator.HasNext())
            {
                EventLogger.Log($"Паралельно [{valueIterator.TraversalName}]", valueIterator.Next().ToString());
            }
            if (parallelRouteIterator.HasNext())
            {
                EventLogger.Log($"Паралельно [{parallelRouteIterator.TraversalName}]", parallelRouteIterator.Next().ToString());
            }
        }
    }
}
