package Polygons;

import java.util.HashMap;
import java.util.Map;

public class Poligonos {
    public static Polygon fromSides(double[] sides) {
        if (sides == null) throw new IllegalArgumentException("Array null");
        int m = sides.length;
        if (m < 3 || m > 6) throw new IllegalArgumentException("Solo soportado 3..6 lados");

        switch (m) {
            case 3:
                return new Triangle(sides[0], sides[1], sides[2]);
            case 4: {
                Map<Double, Integer> counts = new HashMap<>();
                for (double s : sides) counts.merge(s, 1, Integer::sum);

                if (counts.size() == 1) return new Square(sides[0]);

                if (counts.size() == 2) {
                    var it = counts.entrySet().iterator();
                    var e1 = it.next();
                    var e2 = it.next();
                    if (e1.getValue() == 2 && e2.getValue() == 2) {
                        return new Rectangle(e1.getKey(), e2.getKey());
                    }
                }

                throw new IllegalArgumentException("Se esperan cuadrados o rectángulos (dos pares de lados iguales) para entradas de 4 lados.");
            }
            case 5:
                for (int i = 1; i < 5; i++) if (sides[i] != sides[0]) throw new IllegalArgumentException("Pentágono debe ser regular (todos los lados iguales)");
                return new RegularPentagon(sides[0]);
            case 6:
                for (int i = 1; i < 6; i++) if (sides[i] != sides[0]) throw new IllegalArgumentException("Hexágono debe ser regular (todos los lados iguales)");
                return new RegularHexagon(sides[0]);
            default:
                throw new IllegalStateException("No debería llegar aquí");
        }
    }
}
