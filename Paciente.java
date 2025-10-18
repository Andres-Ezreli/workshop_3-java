

import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String nombre;
    private int edad;
    private Hospital hospital; // reference
    private final List<Medico> medicos = new ArrayList<>();

    public Paciente(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    public Hospital getHospital() { return hospital; }
    public void setHospital(Hospital hospital) {
        if (this.hospital == hospital) return;
        Hospital old = this.hospital;
        this.hospital = hospital;
        if (old != null) old.removePaciente(this);
        if (hospital != null && !hospital.getPacientes().contains(this)) hospital.addPaciente(this);
    }

    public List<Medico> getMedicos() { return medicos; }

    public void addMedico(Medico m) {
        if (m == null) return;
        if (!medicos.contains(m)) medicos.add(m);
        if (!m.getPacientes().contains(this)) m.addPaciente(this);
    }

    public void removeMedico(Medico m) {
        if (m == null) return;
        if (medicos.remove(m)) {
            if (m.getPacientes().contains(this)) m.removePaciente(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Paciente{name='%s', edad=%d, medicos=%d}", nombre, edad, medicos.size());
    }
}
