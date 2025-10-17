package Polygons;

public class Rectangle implements Polygon {
    private final double w, h;

    public Rectangle(double w, double h) {
        if (w <= 0 || h <= 0) throw new IllegalArgumentException("Lados deben ser positivos");
        this.w = w; this.h = h;
    }

    @Override public double perimeter() { return 2 * (w + h); }
    @Override public double area() { return w * h; }
    @Override public String describe() { return String.format("Rectangle(w=%.3f, h=%.3f)", w, h); }
}
