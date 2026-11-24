namespace PosylkaPlus.Task1;

public sealed class SorterHistory //caretaker
{
    private readonly Stack<SorterMemento> _history = new();

    public void Push(SorterMemento memento)
    {
        _history.Push(memento);
        EventLogger.Log("SorterHistory", $"Знімок '{memento.SnapshotLabel}' збережено в історію (всього знімків: {_history.Count}).");
    }

    public SorterMemento PopLast()
    {
        if (_history.Count == 0)
        {
            throw new InvalidOperationException("Історія збережених станів порожня.");
        }

        SorterMemento memento = _history.Pop();
        EventLogger.Log("SorterHistory", $"Вилучено останній знімок '{memento.SnapshotLabel}' для відновлення.");
        return memento;
    }

    public SorterMemento? FindByLabel(string label)
    {
        return _history.FirstOrDefault(m => m.SnapshotLabel == label);
    }
}
