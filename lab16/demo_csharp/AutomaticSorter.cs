namespace PosylkaPlus.Task1;

public sealed class AutomaticSorter //originator
{
    private readonly Dictionary<string, int> _directionBins = new();
    private int _processedCount;
    private readonly List<string> _currentQueue = new();

    public void ConfigureDirection(string direction)
    {
        _directionBins.TryAdd(direction, 0);
        EventLogger.Log("AutomaticSorter", $"Налаштовано кошик для напрямку '{direction}'.");
    }

    public void EnqueueParcel(string parcelCode)
    {
        _currentQueue.Add(parcelCode);
        EventLogger.Log("AutomaticSorter", $"Посилку {parcelCode} додано до черги.");
    }

    public void ProcessNextParcel(string targetDirection)
    {
        if (_currentQueue.Count == 0)
        {
            EventLogger.Log("AutomaticSorter", "Черга порожня, немає чого сортувати.");
            return;
        }

        string parcel = _currentQueue[0];
        _currentQueue.RemoveAt(0);

        if (!_directionBins.ContainsKey(targetDirection))
        {
            _directionBins[targetDirection] = 0;
        }

        _directionBins[targetDirection]++;
        _processedCount++;

        EventLogger.Log(
            "AutomaticSorter",
            $"Оброблено посилку {parcel} -> кошик '{targetDirection}' (всього оброблено: {_processedCount})."
        );
    }

    public void SimulatePowerFailure()
    {
        EventLogger.Log("AutomaticSorter", "!!! ЗБІЙ ЖИВЛЕННЯ: оперативну пам'ять сортувальника очищено !!!");
        _directionBins.Clear();
        _processedCount = 0;
        _currentQueue.Clear();
    }

    public SorterMemento SaveState(string label)
    {
        EventLogger.Log("AutomaticSorter", $"Збереження знімка стану '{label}'...");
        return new SorterMemento(label, _directionBins, _processedCount, _currentQueue);
    }

    public void RestoreState(SorterMemento memento)
    {
        _directionBins.Clear();
        foreach (var pair in memento.DirectionBins)
        {
            _directionBins[pair.Key] = pair.Value;
        }

        _processedCount = memento.ProcessedCount;

        _currentQueue.Clear();
        _currentQueue.AddRange(memento.CurrentQueue);

        EventLogger.Log(
            "AutomaticSorter",
            $"Стан успішно відновлено зі знімка '{memento.SnapshotLabel}'."
        );
    }

    public void PrintCurrentState(string contextNote)
    {
        string binsText = _directionBins.Count == 0
            ? "порожньо"
            : string.Join(", ", _directionBins.Select(b => $"{b.Key}: {b.Value} шт."));

        string queueText = _currentQueue.Count == 0
            ? "порожня"
            : string.Join(", ", _currentQueue);

        EventLogger.Log(
            "AutomaticSorter",
            $"[{contextNote}] Оброблено: {_processedCount} | Кошики: [{binsText}] | Черга: [{queueText}]"
        );
    }
}
