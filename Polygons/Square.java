

package Polygons;

import java.util.List;

public class Square extends Poligono {
    private final double side;

    public Square(double side) {
        super(List.of(side, side, side, side));
        this.side = side;
    }

    @Override
    public double calcularArea() {
        return side * side;
    }

    @Override
    public String toString() { return String.format("Cuadrado(lado=%.3f)", side); }
}
