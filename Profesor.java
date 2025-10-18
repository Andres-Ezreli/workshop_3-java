

public class Profesor extends Empleado {
    private Curso curso;

    public Profesor(String nombre, Curso curso, double salario) {
        super(nombre, salario);
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public double calcularSalario() {
        return getSalario() * 1.1; // Bono del 10%
    }
}