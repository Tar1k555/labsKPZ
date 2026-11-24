namespace PosylkaPlus.Task1;

public sealed class Task1Runner
{
    public void RunScenario()
    {
        EventLogger.Log("Runner", "=== ЗАВДАННЯ 1: ВІДНОВЛЕННЯ СТАНУ СОРТУВАЛЬНИКА (MEMENTO) ===");

        var sorter = new AutomaticSorter();
        var history = new SorterHistory();

        sorter.ConfigureDirection("Київ");
        sorter.ConfigureDirection("Львів");
        sorter.ConfigureDirection("Чернівці");

        sorter.EnqueueParcel("PKG-001");
        sorter.EnqueueParcel("PKG-002");
        sorter.EnqueueParcel("PKG-003");
        sorter.EnqueueParcel("PKG-004");

        sorter.ProcessNextParcel("Київ");
        sorter.ProcessNextParcel("Львів");

        sorter.PrintCurrentState("Стан перед збереженням 1");
        history.Push(sorter.SaveState("Контрольна точка №1"));

        sorter.ProcessNextParcel("Чернівці");
        sorter.EnqueueParcel("PKG-005");
        sorter.PrintCurrentState("Стан перед збереженням 2");
        history.Push(sorter.SaveState("Контрольна точка №2"));

        sorter.SimulatePowerFailure();
        sorter.PrintCurrentState("Стан після збою");

        SorterMemento lastSnapshot = history.PopLast();
        sorter.RestoreState(lastSnapshot);
        sorter.PrintCurrentState("Стан після відновлення останнього знімка");

        SorterMemento firstSnapshot = history.PopLast();
        sorter.RestoreState(firstSnapshot);
        sorter.PrintCurrentState("Стан після відкоту до попереднього знімка");
    }
}
