using System;

namespace DeliveryApp.Builders
{
    public class Waybill
    {
        public string Sender { get; set; }
        public string ReceiverName { get; set; }
        public string ReceiverPhone { get; set; }
        public double Weight { get; set; }
        public decimal DeclaredValue { get; set; } = 0;
        public decimal CodAmount { get; set; } = 0;
        public bool IsInsured { get; set; } = false;

        public override string ToString()
        {
            return $"- Відправник: {Sender}\n" +
                   $"- Отримувач: {ReceiverName} (Тел: {ReceiverPhone})\n" +
                   $"- Вага: {Weight} кг\n" +
                   $"- Оголошена цінність: {DeclaredValue} грн\n" +
                   $"- Накладений платіж: {CodAmount} грн\n" +
                   $"- Страхування: {(IsInsured ? "Так" : "Ні")}";
        }
    }

    public interface WaybillBuilder
    {
        void SetSender(string sender);
        void SetReceiver(string name, string phone);
        void SetWeight(double weight);
        void SetDeclaredValue(decimal value);
        void SetCodAmount(decimal amount);
        void SetInsurance(bool insured);
        Waybill Build();
    }

    public class StandardWaybillBuilder : WaybillBuilder
    {
        private Waybill _waybill = new Waybill();

        public void SetSender(string sender) => _waybill.Sender = sender;

        public void SetReceiver(string name, string phone)
        {
            _waybill.ReceiverName = name;
            _waybill.ReceiverPhone = phone;
        }

        public void SetWeight(double weight) => _waybill.Weight = weight;
        public void SetDeclaredValue(decimal value) => _waybill.DeclaredValue = value;
        public void SetCodAmount(decimal amount) => _waybill.CodAmount = amount;
        public void SetInsurance(bool insured) => _waybill.IsInsured = insured;

        public Waybill Build()
        {
            if (string.IsNullOrWhiteSpace(_waybill.Sender))
            {
                throw new InvalidOperationException("Неможливо сформувати накладну: не вказано відправника.");
            }
            if (string.IsNullOrWhiteSpace(_waybill.ReceiverPhone))
            {
                throw new InvalidOperationException("Неможливо сформувати накладну: не вказано телефон отримувача.");
            }
            if (_waybill.Weight <= 0)
            {
                throw new InvalidOperationException("Неможливо сформувати накладну: вага має бути більшою за нуль.");
            }

            return _waybill;
        }
    }

    public class WaybillDirector
    {
        private readonly WaybillBuilder _builder;

        public WaybillDirector(WaybillBuilder builder)
        {
            _builder = builder;
        }

        public Waybill ConstructFullWaybill(string sender, string receiverName, string receiverPhone, double weight, decimal declaredValue, decimal codAmount, bool isInsured)
        {
            _builder.SetSender(sender);
            _builder.SetReceiver(receiverName, receiverPhone);
            _builder.SetWeight(weight);
            _builder.SetDeclaredValue(declaredValue);
            _builder.SetCodAmount(codAmount);
            _builder.SetInsurance(isInsured);
            return _builder.Build();
        }

        public Waybill ConstructDocumentsWaybill(string sender, string receiverName, string receiverPhone)
        {
            _builder.SetSender(sender);
            _builder.SetReceiver(receiverName, receiverPhone);
            _builder.SetWeight(0.5);
            _builder.SetDeclaredValue(100);
            _builder.SetCodAmount(0);
            _builder.SetInsurance(false);
            return _builder.Build();
        }
    }
}
