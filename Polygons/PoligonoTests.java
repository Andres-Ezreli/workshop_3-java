package Polygons;

public class PoligonoTests {
    private static final double EPS = 1e-6;

    public static void main(String[] args) {
    // Triangle 3-4-5 -> area 6
    runTest("Triangle 3-4-5", () -> {
            Poligono p = Poligonos.fromSides(new double[]{3,4,5});
            return approxEquals(p.calcularArea(), 6.0, EPS);
        });

        // Square side 2 -> area 4
    runTest("Square side 2", () -> {
            Poligono p = Poligonos.fromSides(new double[]{2,2,2,2});
            return approxEquals(p.calcularArea(), 4.0, EPS);
        });

        // Rectangle 3x4 -> area 12 (sides passed as 3,4,3,4)
    runTest("Rectangle 3x4", () -> {
            Poligono p = Poligonos.fromSides(new double[]{3,4,3,4});
            return approxEquals(p.calcularArea(), 12.0, EPS);
        });

        // Regular pentagon side 1 -> area via formula (approx)
    runTest("RegularPentagon s=1", () -> {
            Poligono p = Poligonos.fromSides(new double[]{1,1,1,1,1});
            double per = p.perimetro();
            double apotema = 1.0 / (2.0 * Math.tan(Math.PI / 5.0));
            double expected = per * apotema / 2.0;
            return approxEquals(p.calcularArea(), expected, EPS);
        });

        // Regular hexagon side 1 -> area = 3*sqrt(3)/2 * s^2
    runTest("RegularHexagon s=1", () -> {
            Poligono p = Poligonos.fromSides(new double[]{1,1,1,1,1,1});
            double expected = (3.0 * Math.sqrt(3.0) / 2.0) * 1.0;
            return approxEquals(p.calcularArea(), expected, EPS);
        });

        System.out.println("All tests executed.");
    }

    private static void runTest(String name, java.util.concurrent.Callable<Boolean> test) {
        try {
            boolean ok = test.call();
            System.out.printf("%s: %s\n", name, ok ? "PASS" : "FAIL");
        } catch (Exception e) {
            System.out.printf("%s: ERROR - %s\n", name, e.getMessage());
        }
    }

    private static boolean approxEquals(double a, double b, double eps) {
        return Math.abs(a - b) <= eps;
    }
}
