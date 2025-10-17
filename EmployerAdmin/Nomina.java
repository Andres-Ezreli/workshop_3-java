public class Nomina {
    public static void generarPago(Empleado e) {
        double salario = e.calcularSalario();
        // Imprimir comprobante simple
        System.out.println("----Comprobante de pago----");
        System.out.println("Empleado: " + e.getNombre());
        System.out.println("Tipo: " + e.getClass().getSimpleName());
        System.out.printf("Salario a pagar: %.2f\n", salario);
        System.out.println("---------------------------\n");
    }
}
