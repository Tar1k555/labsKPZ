package com.example.task3_lsp;

public class Square implements IShape {
    private final double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Сторона квадрата повинна бути більшою за 0.");
        }
        this.side = side;
    }

    public double getSide() { return side; }

    @Override
    public double getArea() {
        return side * side;
    }
}