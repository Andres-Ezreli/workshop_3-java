package Hospital;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String nombre;
    private final List<Medico> medicos = new ArrayList<>();
    private final List<Paciente> pacientes = new ArrayList<>();

    public Hospital(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Medico> getMedicos() { return medicos; }
    public List<Paciente> getPacientes() { return pacientes; }

    public void addMedico(Medico m) {
        if (m == null) return;
        if (!medicos.contains(m)) {
            medicos.add(m);
        }
        if (m.getHospital() != this) m.setHospital(this);
    }

    public void removeMedico(Medico m) {
        if (m == null) return;
        if (medicos.remove(m)) {
            if (m.getHospital() == this) m.setHospital(null);
        }
    }

    public void addPaciente(Paciente p) {
        if (p == null) return;
        if (!pacientes.contains(p)) {
            pacientes.add(p);
        }
        if (p.getHospital() != this) p.setHospital(this);
    }

    public void removePaciente(Paciente p) {
        if (p == null) return;
        if (pacientes.remove(p)) {
            if (p.getHospital() == this) p.setHospital(null);
        }
    }

    @Override
    public String toString() {
        return String.format("Hospital{name='%s', medicos=%d, pacientes=%d}", nombre, medicos.size(), pacientes.size());
    }
}
