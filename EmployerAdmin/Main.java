public class Main {
    public static void main(String[] args) {
        Curso java101 = new Curso("Java 101");
        Profesor prof = new Profesor("Ana Perez", java101, 500.0);
        Administrador admin = new Administrador("Luis Gomez", "Recursos Humanos", 400.0);

        Nomina.generarPago(prof);
        Nomina.generarPago(admin);
    }
}


