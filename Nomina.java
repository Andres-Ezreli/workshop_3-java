

public class Nomina {
    public static void generarPago(Empleado empleado) {
        System.out.println("Pagando a " + empleado.getNombre() + " la cantidad de " + empleado.calcularSalario());
    }
}