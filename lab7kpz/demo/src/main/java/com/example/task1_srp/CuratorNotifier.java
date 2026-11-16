package com.example.task1_srp;

public class CuratorNotifier {
    public void notifyCurator(String curatorEmail, String studentName, String transcript) {
        if (curatorEmail != null && !curatorEmail.isBlank()) {
            try {
                System.out.println("Надсилання пошти куратору " + curatorEmail + " через mail.univ.local");
                System.out.println("Тема: Успішність студента " + studentName);
                System.out.println("Тіло: " + transcript);
            } catch (Exception ex) {
                System.out.println("Помилка надсилання відомості куратору: " + ex.getMessage());
            }
        }
    }
}