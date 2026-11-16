package com.example.task4_isp;

public class MultiFunctionDevice implements IPrinter, IScanner, IFax {
    @Override
    public void printDocument(String doc) {
        System.out.println("MFD Print: " + doc);
    }

    @Override
    public void duplexPrint(String doc) {
        System.out.println("MFD Duplex Print: " + doc);
    }

    @Override
    public void scanDocument() {
        System.out.println("MFD Scan...");
    }

    @Override
    public void sendFax(String number, String doc) {
        System.out.println("MFD Fax sent to " + number);
    }
}