package Polygons;

import java.util.List;

public class Rectangle extends Poligono {
    private final double w, h;

    public Rectangle(double w, double h) {
        super(List.of(w, h, w, h));
        this.w = w; this.h = h;
    }

    @Override
    public double calcularArea() { return w * h; }

    @Override
    public String toString() { return String.format("Rectángulo(w=%.3f, h=%.3f)", w, h); }
}
