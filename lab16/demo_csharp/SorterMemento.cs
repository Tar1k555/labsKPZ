namespace PosylkaPlus.Task1;

public sealed class SorterMemento
{
    public string SnapshotLabel { get; }
    public DateTime CreatedAt { get; }

    internal Dictionary<string, int> DirectionBins { get; }
    internal int ProcessedCount { get; }
    internal List<string> CurrentQueue { get; }

    internal SorterMemento(
        string snapshotLabel,
        Dictionary<string, int> directionBins,
        int processedCount,
        List<string> currentQueue)
    {
        SnapshotLabel = snapshotLabel;
        CreatedAt = DateTime.Now;
        DirectionBins = new Dictionary<string, int>(directionBins); //deep copy
        ProcessedCount = processedCount;
        CurrentQueue = new List<string>(currentQueue);
    }
}
