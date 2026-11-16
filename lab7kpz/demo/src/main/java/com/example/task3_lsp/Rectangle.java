package com.example.task3_lsp;

public class Rectangle implements IShape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Сторони прямокутника повинні бути більшими за 0.");
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() { return width; }
    public double getHeight() { return height; }

    @Override
    public double getArea() {
        return width * height;
    }
}