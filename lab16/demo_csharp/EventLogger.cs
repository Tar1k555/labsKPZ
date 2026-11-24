namespace PosylkaPlus;

public static class EventLogger
{
    private static int _tick;

    public static void Log(string source, string message)
    {
        _tick++;
        Console.WriteLine($"[T+{_tick:D2}s] [{source}] {message}");
    }
}
