package com.example.task3_visitor;

import com.example.util.ConsoleLogger;

public class CustomsDeclarationVisitor implements ShipmentVisitor {
    private int declarationsCount = 0;

    @Override
    public String getOperationName() {
        return "Формування митної декларації";
    }

    @Override
    public void visit(Letter letter) {
        letter.setCustomsStatus("Пропуск (внутрішній лист)");
        ConsoleLogger.log("CustomsVisitor", "Лист " + letter.getTrackingNumber() + ": внутрішнє відправлення — пропуск.");
    }

    @Override
    public void visit(Parcel parcel) {
        parcel.setCustomsStatus("Пропуск (внутрішня посилка)");
        ConsoleLogger.log("CustomsVisitor", "Посилка " + parcel.getTrackingNumber() + ": внутрішнє відправлення — пропуск.");
    }

    @Override
    public void visit(FreightCargo cargo) {
        cargo.setCustomsStatus("Пропуск (внутрішній вантаж)");
        ConsoleLogger.log("CustomsVisitor", "Вантаж " + cargo.getTrackingNumber() + ": внутрішнє відправлення — пропуск.");
    }

    @Override
    public void visit(InternationalShipment international) {
        declarationsCount++;
        String status = String.format("Декларація CN23 [Країна: %s, Код УКТЗЕД: %s, Вартість: %.2f EUR]",
                international.getDestinationCountry(), international.getHsCode(), international.getCustomsValueEur());
        international.setCustomsStatus(status);
        ConsoleLogger.log("CustomsVisitor", "Міжнародне " + international.getTrackingNumber() + ": сформовано -> " + status);
    }

    public int getDeclarationsCount() {
        return declarationsCount;
    }
}