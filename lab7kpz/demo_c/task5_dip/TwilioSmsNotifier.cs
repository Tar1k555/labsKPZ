using System;

namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public class TwilioSmsNotifier : ISmsNotifier
    {
        public void SendSmsReceipt(string phone, string transactionId, decimal amount)
        {
            Console.WriteLine($"[Twilio SMS] Повідомлення на {phone}: Платіж {transactionId} на суму {amount} UAH успішно виконано.");
        }
    }
}
