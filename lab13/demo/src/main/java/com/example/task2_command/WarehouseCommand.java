package com.example.task2_command;

public interface WarehouseCommand {
    void execute();
    void undo();
    String getDescription();
}