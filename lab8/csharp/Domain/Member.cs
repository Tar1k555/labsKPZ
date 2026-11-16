namespace Variant3_FitnessMembership.Domain
{
    public class Member
    {
        public int Id { get; }
        public string Name { get; }
        public string Phone { get; }
        public bool HasDiscountCard { get; }

        public Member(int id, string name, string phone, bool hasDiscountCard)
        {
            Id = id;
            Name = name;
            Phone = phone;
            HasDiscountCard = hasDiscountCard;
        }
    }
}
