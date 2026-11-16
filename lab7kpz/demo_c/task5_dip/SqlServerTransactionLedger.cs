using System;

namespace Variant3_FinancialTransactionProcessor.Task5_DIP
{
    public class SqlServerTransactionLedger : ITransactionLedger
    {
        public void RecordTransaction(string transactionId, decimal amount, string status)
        {
            Console.WriteLine($"[SQL Server Ledger] Записано транзакцію {transactionId}: сума={amount}, статус={status}.");
        }
    }
}
