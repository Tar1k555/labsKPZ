package com.example.task1_facade;

import com.example.util.ConsoleLogger;

public class AddressChecker {
    public boolean verifyAddress(String address) {
        ConsoleLogger.log("AddressChecker", "Перевірка адреси: '" + address + "' -> Валідно.");
        return true;
    }
}