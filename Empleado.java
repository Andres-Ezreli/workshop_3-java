public class Empleado {
    private String nombre;
    protected double salarioBase = 1000.0;

    public Empleado(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public double calcularSalario() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0) throw new IllegalArgumentException("Salario base no puede ser negativo");
        this.salarioBase = salarioBase;
    }

    @Override
    public String toString() {
        return String.format("Empleado{name='%s'}", nombre);
    }
}