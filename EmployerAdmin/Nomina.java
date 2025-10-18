package EmployerAdmin;

public class Nomina {
    public static String generarPago(Empleado empleado) {
        return "Pagando a " + empleado.getNombre() + " la cantidad de " + empleado.calcularSalario();
    }
}
