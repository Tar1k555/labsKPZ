namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public interface IShipmentHandler
    {
        IShipmentHandler SetNext(IShipmentHandler handler);
        void Handle(Shipment shipment);
    }
}
