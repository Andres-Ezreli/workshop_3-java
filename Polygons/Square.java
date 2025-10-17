package Polygons;

public class Square extends Polygon {
    private final double side;

    public Square(double side) {
        if (side <= 0) throw new IllegalArgumentException("Lado debe ser positivo");
        this.side = side;
    }

    @Override public double perimeter() { return 4 * side; }
    @Override public double area() { return side * side; }
    @Override public String describe() { return String.format("Square(side=%.3f)", side); }
}
