package EmployerAdmin;

public class Administrador extends Empleado {
    private String departamento;

    public Administrador(String nombre, String departamento, double salario) {
        super(nombre, salario);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    @Override
    public double calcularSalario() {
        return getSalario();
    }
}
