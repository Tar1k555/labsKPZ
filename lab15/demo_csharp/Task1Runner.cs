namespace PosylkaPlus.Delivery.Task1;

public sealed class Task1Runner
{
    public void RunScenario()
    {
        EventLogger.Log("Runner", "=== СЦЕНАРІЙ ЗАВДАННЯ 1: ВРУЧЕННЯ ВІДПРАВЛЕНЬ ===");

        // 1. Вручення у відділенні (оплачене заздалегідь)
        var branchShipment = new Shipment("PKG-1001", "Олена Коваль", isPaid: true);
        DeliveryProcessTemplate branchProcess = new BranchDeliveryProcess("№12");
        branchProcess.Deliver(branchShipment);

        // 2. Вручення через поштомат
        var lockerShipment = new Shipment("PKG-1002", "Іван Мельник", isPaid: true);
        DeliveryProcessTemplate lockerProcess = new ParcelLockerDeliveryProcess("PM-305");
        lockerProcess.Deliver(lockerShipment);

        // 3. Вручення з накладеним платежем кур'єром (та сама логіка перевірки оплати)
        var codShipment = new Shipment("PKG-1003", "Марія Савчук", isPaid: false, cashOnDeliveryAmount: 850.00m);
        DeliveryProcessTemplate courierProcess = new CourierDeliveryProcess("Андрій Ткач");
        courierProcess.Deliver(codShipment);
    }
}
