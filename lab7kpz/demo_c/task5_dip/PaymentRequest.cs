namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public class PaymentRequest
    {
        public string TransactionId { get; set; }
        public string CardNumber { get; set; }
        public decimal Amount { get; set; }
        public string PayerPhone { get; set; }
    }
}
