package com.example.task2_mediator;

public interface SortingCenterMediator {
    void notify(SortingDevice sender, String event, Object data);
}