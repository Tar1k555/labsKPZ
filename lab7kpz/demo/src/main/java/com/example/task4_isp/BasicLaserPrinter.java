package com.example.task4_isp;

public class BasicLaserPrinter implements IPrinter {
    @Override
    public void printDocument(String doc) {
        System.out.println("Printing: " + doc);
    }

    @Override
    public void duplexPrint(String doc) {
        System.out.println("Duplex printing: " + doc);
    }
}