public class Main {
    public static void main(String[] args) {
        System.out.println("--- employer ---");
            Curso java101 = new Curso("Java 101");
            Profesor prof = new Profesor("Ana Perez", java101, 500.0);
            Administrador admin = new Administrador("Luis Gomez", "Recursos Humanos", 400.0);

            Nomina.generarPago(prof);
            Nomina.generarPago(admin);

        System.out.println("\n--- figures ---");
        {
            double[][] examples = new double[][]{
                {3,4,5},
                {4, 6, 4, 6},
                {5, 5, 5, 5, 5},
                {6, 6, 6, 6, 6, 6}
            };

            for (double[] ex : examples) {
                try {
                    Polygons.Poligono p = Polygons.Poligonos.fromSides(ex);
                    System.out.printf("%s -> %s%n", java.util.Arrays.toString(ex), p.obtenerInfo());
                } catch (Exception e) {
                    System.out.printf("Input %s -> ERROR: %s%n", java.util.Arrays.toString(ex), e.getMessage());
                }
            }
        }

        System.out.println("\n--- hospital ---");
        {
            Hospital.Hospital h = new Hospital.Hospital("Hospital La Milagrosa");

            // Create medicos
            Hospital.Medico m1 = new Hospital.Medico("Dr. Juan Perez", "Cardiologia");
            Hospital.Medico m2 = new Hospital.Medico("Dra. Maria Lopez", "Neurologia");
            Hospital.Medico m3 = new Hospital.Medico("Dr. Carlos Sanchez", "Pediatria");

            // Register medicos in hospital (one-to-many)
            h.addMedico(m1);
            h.addMedico(m2);
            h.addMedico(m3);

            // Create pacientes
            Hospital.Paciente p1 = new Hospital.Paciente("Pedro Gomez", 45);
            Hospital.Paciente p2 = new Hospital.Paciente("Luisa Fernandez", 30);
            Hospital.Paciente p3 = new Hospital.Paciente("Ana Martinez", 10);

            h.addPaciente(p1);
            h.addPaciente(p2);
            h.addPaciente(p3);

            p1.addMedico(m1);
            p1.addMedico(m2);

            p2.addMedico(m2);

            p3.addMedico(m2);
            p3.addMedico(m3);

            System.out.println(h);
            System.out.println("Medicos:");
            for (Hospital.Medico m : h.getMedicos()) System.out.println("  " + m + " -> Hospital=" + m.getHospital().getNombre());

            System.out.println("Pacientes:");
            for (Hospital.Paciente p : h.getPacientes()) System.out.println("  " + p + " -> Hospital=" + p.getHospital().getNombre());

            System.out.println("--- relationships ---");
            System.out.println(p1.getNombre() + " medicos: " + p1.getMedicos());
            System.out.println(p2.getNombre() + " medicos: " + p2.getMedicos());
            System.out.println(p3.getNombre() + " medicos: " + p3.getMedicos());

            System.out.println(m2.getNombre() + " pacientes: " + m2.getPacientes());
        }
    }
}
