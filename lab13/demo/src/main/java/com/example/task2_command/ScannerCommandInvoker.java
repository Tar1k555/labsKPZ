package com.example.task2_command;

import com.example.util.ConsoleLogger;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ScannerCommandInvoker {
    private final Queue<WarehouseCommand> commandQueue = new LinkedList<>();
    private final Deque<WarehouseCommand> executedHistory = new ArrayDeque<>();
    private final List<String> warehouseAuditLog = new ArrayList<>();
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    // Сканер додає команду в чергу
    public void receiveFromScanner(WarehouseCommand command) {
        commandQueue.offer(command);
        ConsoleLogger.log("ScannerInvoker", "Отримано запит зі сканера та поставлено в чергу (розмір черги: "
                + commandQueue.size() + "): " + command.getDescription());
    }

    // Послідовне виконання всіх команд із черги
    public void processAllQueuedCommands() {
        ConsoleLogger.log("ScannerInvoker", "Початок послідовної обробки черги команд (" + commandQueue.size() + " шт.)...");
        while (!commandQueue.isEmpty()) {
            WarehouseCommand cmd = commandQueue.poll();
            cmd.execute();
            executedHistory.push(cmd);
            recordAudit("ВИКОНАНО", cmd.getDescription());
        }
        ConsoleLogger.log("ScannerInvoker", "Чергу команд повністю оброблено.");
    }

    // Скасування останньої виконаної операції
    public void undoLastOperation() {
        if (executedHistory.isEmpty()) {
            ConsoleLogger.log("ScannerInvoker", "Історія операцій порожня — нічого скасовувати.");
            return;
        }
        WarehouseCommand lastCmd = executedHistory.pop();
        ConsoleLogger.log("ScannerInvoker", "Запит на скасування останньої дії: " + lastCmd.getDescription());
        lastCmd.undo();
        recordAudit("СКАСОВАНО (UNDO)", lastCmd.getDescription());
    }

    private void recordAudit(String status, String details) {
        String entry = String.format("[%s] %-16s | %s", LocalTime.now().format(TIME_FMT), status, details);
        warehouseAuditLog.add(entry);
    }

    public void printWarehouseLog() {
        System.out.println("\n=== ЖУРНАЛ ОПЕРАЦІЙ СОРТУВАЛЬНОГО СКЛАДУ ===");
        for (int i = 0; i < warehouseAuditLog.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, warehouseAuditLog.get(i));
        }
        System.out.println("=============================================\n");
    }
}