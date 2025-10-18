package Polygons;

import java.util.List;

public class RegularPentagon extends Poligono {
    private final double s;

    public RegularPentagon(double s) {
        super(List.of(s, s, s, s, s));
        this.s = s;
    }

    @Override
    public double calcularArea() {
        double per = perimetro();
        double apotema = s / (2.0 * Math.tan(Math.PI / 5.0));
        return per * apotema / 2.0;
    }

    @Override
    public String toString() { return String.format("Pentágono regular(lado=%.3f)", s); }
}
