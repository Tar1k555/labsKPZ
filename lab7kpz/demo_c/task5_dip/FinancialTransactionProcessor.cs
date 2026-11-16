using System;

namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public class FinancialTransactionProcessor
    {
        private readonly IPaymentGateway _paymentGateway;
        private readonly ITransactionLedger _ledger;
        private readonly ISmsNotifier _smsNotifier;

        public FinancialTransactionProcessor(
            IPaymentGateway paymentGateway,
            ITransactionLedger ledger,
            ISmsNotifier smsNotifier)
        {
            _paymentGateway = paymentGateway ?? throw new ArgumentNullException(nameof(paymentGateway));
            _ledger = ledger ?? throw new ArgumentNullException(nameof(ledger));
            _smsNotifier = smsNotifier ?? throw new ArgumentNullException(nameof(smsNotifier));
        }

        public void ProcessPayment(PaymentRequest request)
        {
            if (request == null || request.Amount <= 0)
            {
                throw new ArgumentException("Сума транзакції повинна бути більшою за нуль.");
            }

            bool success = _paymentGateway.ExecuteCharge(request.CardNumber, request.Amount);
            string status = success ? "SUCCESS" : "FAILED";

            _ledger.RecordTransaction(request.TransactionId, request.Amount, status);
            _smsNotifier.SendSmsReceipt(request.PayerPhone, request.TransactionId, request.Amount);
        }
    }
}
