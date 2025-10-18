

package EmployerAdmin;

public class Profesor extends Empleado {
    private Curso curso;

    public Profesor(String nombre, Curso curso, double salarioBase, double bonification) {
        super(nombre, salarioBase, bonification);
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario() * 1.1; // Bono del 10%
    }
}
