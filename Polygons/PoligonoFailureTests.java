package Polygons;

import java.util.concurrent.Callable;

public class PoligonoFailureTests {
    public static void main(String[] args) {
        System.out.println("Running failure tests...");

        // Test case: fromSides with null array
        expectException("fromSides with null array", () -> Poligonos.fromSides(null), IllegalArgumentException.class);

        // Test case: fromSides with less than 3 sides
        expectException("fromSides with 2 sides", () -> Poligonos.fromSides(new double[]{1, 1}), IllegalArgumentException.class);

        // Test case: fromSides with more than 6 sides
        expectException("fromSides with 7 sides", () -> Poligonos.fromSides(new double[]{1, 1, 1, 1, 1, 1, 1}), IllegalArgumentException.class);

        // Test case: Non-regular pentagon
        expectException("Non-regular pentagon", () -> Poligonos.fromSides(new double[]{1, 2, 1, 1, 1}), IllegalArgumentException.class);

        // Test case: Non-regular hexagon
        expectException("Non-regular hexagon", () -> Poligonos.fromSides(new double[]{1, 1, 2, 1, 1, 1}), IllegalArgumentException.class);

        // Test case: Invalid triangle - sides don't form a triangle
        expectException("Invalid triangle inequality", () -> new Triangle(1, 2, 4), IllegalArgumentException.class);

        // Test case: Invalid triangle - zero side
        expectException("Triangle with zero side", () -> new Triangle(1, 2, 0), IllegalArgumentException.class);

        // Test case: Invalid triangle - negative side
        expectException("Triangle with negative side", () -> new Triangle(1, 2, -1), IllegalArgumentException.class);

        System.out.println("All failure tests passed.");
    }

    private static void expectException(String testName, Runnable code, Class<? extends Exception> expectedException) {
        try {
            code.run();
            System.out.printf("%s: FAIL - Expected %s but no exception was thrown.\n", testName, expectedException.getSimpleName());
        } catch (Exception e) {
            if (expectedException.isInstance(e)) {
                System.out.printf("%s: PASS\n", testName);
            } else {
                System.out.printf("%s: FAIL - Expected %s but got %s.\n", testName, expectedException.getSimpleName(), e.getClass().getSimpleName());
            }
        }
    }
}