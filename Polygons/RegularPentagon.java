package Polygons;

public class RegularPentagon implements Polygon {
    private final double s;

    public RegularPentagon(double s) {
        if (s <= 0) throw new IllegalArgumentException("Lado debe ser positivo");
        this.s = s;
    }

    @Override public double perimeter() { return 5 * s; }

    @Override public double area() {
        double a = s / (2.0 * Math.tan(Math.PI / 5.0));
        return perimeter() * a / 2.0;
    }

    @Override public String describe() { return String.format("RegularPentagon(side=%.3f)", s); }
}
