public class Profesor extends Empleado {
    private Curso curso;
    private double bonificacion;

    public Profesor(String nombre, Curso curso, double bonificacion) {
        super(nombre);
        this.curso = curso;
        this.bonificacion = bonificacion;
    }

    public Curso getCurso() { return curso; }

    @Override
    public double calcularSalario() {
        return salarioBase + bonificacion;
    }

    @Override
    public String toString() {
        return String.format("Profesor{name='%s', curso='%s'}", getNombre(), curso != null ? curso.getNombre() : "-");
    }
}
