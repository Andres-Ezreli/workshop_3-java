package Polygons;

public class Triangle implements Polygon {
    private final double a, b, c;

    public Triangle(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) throw new IllegalArgumentException("Los lados deben ser positivos");
        if (a + b <= c || a + c <= b || b + c <= a) throw new IllegalArgumentException("Las longitudes no forman un triángulo válido");
        this.a = a; this.b = b; this.c = c;
    }

    @Override public double perimeter() { return a + b + c; }

    @Override public double area() {
        double s = perimeter() / 2.0;
        return Math.sqrt(Math.max(0.0, s * (s - a) * (s - b) * (s - c)));
    }

    @Override public String describe() { return String.format("Triangle(%.3f, %.3f, %.3f)", a, b, c); }
}
