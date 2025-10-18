package EmployerAdmin;

public class Administrador extends Empleado {
    private String departamento;

    public Administrador(String nombre, String departamento, double salarioBase, double bonification) {
        super(nombre, salarioBase, bonification);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }
}
