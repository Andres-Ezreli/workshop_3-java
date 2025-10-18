package Hospital;

public class Main {
    public static void main(String[] args) {
        Hospital h = new Hospital("Hospital La Milagrosa");

        // Create medicos
        Medico m1 = new Medico("Dr. Juan Perez", "Cardiologia");
        Medico m2 = new Medico("Dra. Maria Lopez", "Neurologia");
        Medico m3 = new Medico("Dr. Carlos Sanchez", "Pediatria");

        // Register medicos in hospital (one-to-many)
        h.addMedico(m1);
        h.addMedico(m2);
        h.addMedico(m3);

        // Create pacientes
        Paciente p1 = new Paciente("Pedro Gomez", 45);
        Paciente p2 = new Paciente("Luisa Fernandez", 30);
        Paciente p3 = new Paciente("Ana Martinez", 10);

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
        for (Medico m : h.getMedicos()) System.out.println("  " + m + " -> Hospital=" + m.getHospital().getNombre());

        System.out.println("Pacientes:");
        for (Paciente p : h.getPacientes()) System.out.println("  " + p + " -> Hospital=" + p.getHospital().getNombre());

        System.out.println("--- relationships ---");
        System.out.println(p1.getNombre() + " medicos: " + p1.getMedicos());
        System.out.println(p2.getNombre() + " medicos: " + p2.getMedicos());
        System.out.println(p3.getNombre() + " medicos: " + p3.getMedicos());

        System.out.println(m2.getNombre() + " pacientes: " + m2.getPacientes());
    }
}
