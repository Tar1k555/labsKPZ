package com.example.task3_lsp;

public class AreaCalculator {
    public double calculateArea(IShape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Фігура не може бути null.");
        }
        return shape.getArea();
    }
}