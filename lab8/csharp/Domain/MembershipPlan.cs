namespace Variant3_FitnessMembership.Domain
{
    public class MembershipPlan
    {
        public string Code { get; } // "Day", "Full", "VIP"
        public string Title { get; }
        public decimal BaseMonthlyRate { get; }
        public int MonthsDuration { get; }

        public MembershipPlan(string code, string title, decimal baseMonthlyRate, int monthsDuration)
        {
            Code = code;
            Title = title;
            BaseMonthlyRate = baseMonthlyRate;
            MonthsDuration = monthsDuration;
        }
    }
}
