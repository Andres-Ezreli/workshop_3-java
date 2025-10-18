

package Polygons;

import java.util.HashMap;
import java.util.Map;

public class Poligonos {
    public static Poligono fromSides(double[] sides) {
        if (sides == null) throw new IllegalArgumentException("Array null");
        int m = sides.length;
        if (m < 3 || m > 6) throw new IllegalArgumentException("Solo soportado 3..6 lados");

        final double EPS = 1e-6;
        java.util.function.BiPredicate<Double, Double> eq = (x, y) -> Math.abs(x - y) <= EPS;

        switch (m) {
            case 3:
                return new Triangle(sides[0], sides[1], sides[2]);
            case 4: {
                double a = sides[0];
                boolean allEqual = true;
                for (int i = 1; i < 4; i++) if (!eq.test(a, sides[i])) { allEqual = false; break; }
                if (allEqual) return new Square(a);

                Map<Double, Integer> groups = new HashMap<>();
                outer:
                for (double s : sides) {
                    for (Double key : new java.util.ArrayList<>(groups.keySet())) {
                        if (eq.test(key, s)) { groups.put(key, groups.get(key) + 1); continue outer; }
                    }
                    groups.put(s, 1);
                }

                if (groups.size() == 2) {
                    var it = groups.entrySet().iterator();
                    var e1 = it.next();
                    var e2 = it.next();
                    if ((e1.getValue() == 2 && e2.getValue() == 2) || (e1.getValue() == 1 && e2.getValue() == 3) || (e1.getValue() == 3 && e2.getValue() == 1)) {
                        return new Rectangle(e1.getKey(), e2.getKey());
                    }
                }

                throw new IllegalArgumentException("Se esperan cuadrados o rectángulos (dos pares de lados iguales) para entradas de 4 lados.");
            }
            case 5:
                boolean allEq5 = true;
                for (int i = 1; i < 5; i++) if (!eq.test(sides[i], sides[0])) { allEq5 = false; break; }
                if (!allEq5) throw new IllegalArgumentException("Pentágono debe ser regular (todos los lados iguales)");
                return new RegularPentagon(sides[0]);
            case 6:
                boolean allEq6 = true;
                for (int i = 1; i < 6; i++) if (!eq.test(sides[i], sides[0])) { allEq6 = false; break; }
                if (!allEq6) throw new IllegalArgumentException("Hexágono debe ser regular (todos los lados iguales)");
                return new RegularHexagon(sides[0]);
            default:
                throw new IllegalStateException("No debería llegar aquí");
        }
    }
}
