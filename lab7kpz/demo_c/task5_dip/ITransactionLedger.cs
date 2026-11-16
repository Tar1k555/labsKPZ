namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public interface ITransactionLedger
    {
        void RecordTransaction(string transactionId, decimal amount, string status);
    }
}
