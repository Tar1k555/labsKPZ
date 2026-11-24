namespace PosylkaPlus.Task2;

public sealed class Pallet
{
    public string PalletCode { get; }
    private readonly List<MailBag> _bags = new();

    public IReadOnlyList<MailBag> Bags => _bags;

    public Pallet(string palletCode)
    {
        PalletCode = palletCode;
    }

    public void AddBag(MailBag bag) => _bags.Add(bag);
}
