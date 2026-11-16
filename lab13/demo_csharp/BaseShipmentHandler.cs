namespace PosylkaPlus.Task1_ChainOfResponsibility
{
    public abstract class BaseShipmentHandler : IShipmentHandler
    {
        private IShipmentHandler _nextHandler;

        public IShipmentHandler SetNext(IShipmentHandler handler)
        {
            _nextHandler = handler;
            return handler;
        }

        public virtual void Handle(Shipment shipment)
        {
            _nextHandler?.Handle(shipment);
        }
    }
}
