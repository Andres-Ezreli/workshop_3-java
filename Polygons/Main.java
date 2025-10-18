package Polygons;

public class Main {
	public static void main(String[] args) {
    double[][] examples = new double[][]{
            {3,4,5},
            {4, 6, 4, 6},
            {5, 5, 5, 5, 5},
            {6, 6, 6, 6, 6, 6}
        };

        for (double[] ex : examples) {
            try {
                Poligono p = Poligonos.fromSides(ex);
                System.out.printf("%s -> %s%n", java.util.Arrays.toString(ex), p.obtenerInfo());
            } catch (Exception e) {
                System.out.printf("Input %s -> ERROR: %s%n", java.util.Arrays.toString(ex), e.getMessage());
            }
        }
	}
}
