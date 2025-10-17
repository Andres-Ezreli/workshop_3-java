package Polygons;

public class RegularHexagon implements Polygon {
    private final double s;

    public RegularHexagon(double s) {
        if (s <= 0) throw new IllegalArgumentException("Lado debe ser positivo");
        this.s = s;
    }

    @Override public double perimeter() { return 6 * s; }

    @Override public double area() { return (3.0 * Math.sqrt(3.0) / 2.0) * s * s; }

    @Override public String describe() { return String.format("RegularHexagon(side=%.3f)", s); }
}