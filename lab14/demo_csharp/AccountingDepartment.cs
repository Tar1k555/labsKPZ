using System;

namespace PosylkaPlus.Task1_Observer
{
    public class AccountingDepartment : IObserver
    {
        public string Name => "Бухгалтерія";

        public void Update(string trackingNumber, string status)
        {
            if (status == "Вручено")
            {
                Console.WriteLine($"[12:00:01] [{Name}] Відправлення {trackingNumber} успішно вручено. Фіксую надходження коштів за накладеним платежем.");
            }
            else
            {
                Console.WriteLine($"[12:00:01] [{Name}] Статус «{status}» для {trackingNumber} отримано, але обробка коштів не потрібна.");
            }
        }
    }
}
