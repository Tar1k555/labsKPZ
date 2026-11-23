namespace PosylkaPlus.Delivery.Task1;

public static class EventLogger
{
    private static int _tick;

    public static void Log(string source, string message)
    {
        _tick++;
        string timestamp = $"T+{_tick:D2}s";
        Console.WriteLine($"[{timestamp}] [{source}] {message}");
    }
}
