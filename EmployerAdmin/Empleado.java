package EmployerAdmin;

public abstract class Empleado {
    private String nombre;
    private double salarioBase;
    private double bonification;

    public Empleado(String nombre, double salarioBase, double bonification) {
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.bonification = bonification;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getBonification() {
        return bonification;
    }

    public double calcularSalario() {
        return getSalarioBase() + getBonification();
    }
}