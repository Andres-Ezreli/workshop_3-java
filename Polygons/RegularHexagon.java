package Polygons;

import java.util.List;

public class RegularHexagon extends Poligono {
    private final double s;

    public RegularHexagon(double s) {
        super(List.of(s, s, s, s, s, s));
        this.s = s;
    }

    @Override
    public double calcularArea() {
        return (3.0 * Math.sqrt(3.0) / 2.0) * s * s;
    }

    @Override
    public String toString() { return String.format("Hexágono regular(lado=%.3f)", s); }
}