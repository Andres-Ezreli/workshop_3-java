public class Administrador extends Empleado {
    private String departamento;
    private double bonus;

    public Administrador(String nombre, String departamento, double bonus) {
        super(nombre);
        this.departamento = departamento;
        this.bonus = bonus;
    }

    public String getDepartamento() { return departamento; }

    @Override
    public double calcularSalario() {
        return salarioBase + bonus;
    }

    @Override
    public String toString() {
        return String.format("Administrador{name='%s', departamento='%s'}", getNombre(), departamento);
    }
}
