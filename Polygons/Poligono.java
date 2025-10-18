package Polygons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Poligono {
    protected final List<Double> lados;

    protected Poligono(List<Double> lados) {
        if (lados == null) throw new IllegalArgumentException("Lista de lados nula");
        if (lados.size() < 3) throw new IllegalArgumentException("Un polígono debe tener al menos 3 lados");
        List<Double> copy = new ArrayList<>(lados.size());
        for (Double d : lados) {
            if (d == null || d <= 0) throw new IllegalArgumentException("Todas las longitudes deben ser positivas");
            copy.add(d);
        }
        this.lados = Collections.unmodifiableList(copy);
    }

    // Helper: compute perimeter
    public double perimetro() {
        double sum = 0.0;
        for (double s : lados) sum += s;
        return sum;
    }

    public abstract double calcularArea();

    public String tipoPoligono() {
        int n = lados.size();
        switch (n) {
            case 3: return "Triángulo";
            case 4: return "Cuadrilátero";
            case 5: return "Pentágono";
            case 6: return "Hexágono";
            default: return String.format("Polígono de %d lados", n);
        }
    }

    public String obtenerInfo() {
        return String.format("%s - Area real = %.3f", tipoPoligono(), calcularArea());
    }

    public void mostrarInfo() {
        System.out.println(obtenerInfo());
    }
}
