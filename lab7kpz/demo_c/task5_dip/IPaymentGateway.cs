namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public interface IPaymentGateway
    {
        bool ExecuteCharge(string cardNumber, decimal amount);
    }
}
