namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public interface ISmsNotifier
    {
        void SendSmsReceipt(string phone, string transactionId, decimal amount);
    }
}
