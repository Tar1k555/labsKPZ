using System;

namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public class StripePaymentGatewayClient : IPaymentGateway
    {
        public bool ExecuteCharge(string cardNumber, decimal amount)
        {
            Console.WriteLine($"[Stripe API] Виконано списання {amount} UAH з картки {cardNumber}.");
            return true;
        }
    }
}
