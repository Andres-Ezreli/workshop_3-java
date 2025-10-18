package Polygons;

import java.util.List;

public class Triangle extends Poligono {
    private final double a, b, c;

    public Triangle(double a, double b, double c) {
        super(List.of(a, b, c));
        // Poligono already validates positive lengths; additional triangle inequality check:
        if (a + b <= c || a + c <= b || b + c <= a) throw new IllegalArgumentException("Las longitudes no forman un triángulo válido");
        this.a = a; this.b = b; this.c = c;
    }

    @Override
    public double calcularArea() {
        double s = perimetro() / 2.0;
        return Math.sqrt(Math.max(0.0, s * (s - a) * (s - b) * (s - c)));
    }

    @Override
    public String toString() {
        return String.format("Triángulo(%.3f, %.3f, %.3f)", a, b, c);
    }
}
