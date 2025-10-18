public class Curso {
    private String nombre;

    public Curso(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("Nombre de curso no puede estar vacío");
        this.nombre = nombre.trim();
    }

    public String getNombre() { return nombre; }

    @Override
    public String toString() { return String.format("Curso{name='%s'}", nombre); }
}
