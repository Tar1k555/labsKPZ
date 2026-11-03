namespace Variant3_FitnessMembership.Domain
{
    public class Subscription
    {
        public int Id { get; }
        public Member Member { get; }
        public MembershipPlan Plan { get; }
        public bool PersonalTrainerIncluded { get; }
        public decimal BaseAmount { get; private set; }
        public decimal DiscountAmount { get; private set; }
        public decimal FinalCost { get; private set; }
        public string StartDate { get; }
        public string Status { get; private set; }

        // Creator паттерн: конструктор гарантує створення валідного об'єкта
        public Subscription(int id, Member member, MembershipPlan plan, bool personalTrainerIncluded, string startDate)
        {
            Id = id;
            Member = member;
            Plan = plan;
            PersonalTrainerIncluded = personalTrainerIncluded;
            StartDate = startDate;
            Status = "Draft";

            CalculateCost();
        }

        private void CalculateCost()
        {
            decimal rawCost = Plan.BaseMonthlyRate * Plan.MonthsDuration;

            if (Plan.Code == "VIP")
            {
                rawCost += 5000m;
            }
            else if (Plan.Code == "Day")
            {
                rawCost *= 0.8m;
            }

            if (PersonalTrainerIncluded)
            {
                rawCost += 3000m;
            }

            BaseAmount = rawCost;

            decimal discount = 0m;
            if (Member.HasDiscountCard)
            {
                discount = BaseAmount * 0.10m;
            }

            DiscountAmount = discount;
            FinalCost = BaseAmount - DiscountAmount;
            Status = "Active";
        }

        public string GenerateContract()
        {
            return $"Договір #{Id} з {Member.Name}\n" +
                   $"Тариф: {Plan.Code}\n" +
                   $"Базова вартість: {BaseAmount} грн\n" +
                   $"Знижка: {DiscountAmount} грн\n" +
                   $"До сплати: {FinalCost} грн";
        }
    }
}
