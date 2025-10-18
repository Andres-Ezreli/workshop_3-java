

package Hospital;

import java.util.ArrayList;
import java.util.List;

public class Medico {

    private String nombre;
    private String especialidad;
    private Hospital hospital; 
    private final List<Paciente> pacientes = new ArrayList<>(); 

    public Medico(String nombre, String especialidad) {

        this.nombre = nombre;
        this.especialidad = especialidad;

    }

    public String getNombre() { return nombre; }

    public String getEspecialidad() { return especialidad; }

    public Hospital getHospital() { return hospital; }

    public void setHospital(Hospital hospital) {

        if (this.hospital == hospital) return;

        Hospital old = this.hospital;
    
        this.hospital = hospital;

        if (old != null) old.removeMedico(this);

        if (hospital != null && !hospital.getMedicos().contains(this)) hospital.addMedico(this);
    }

    public List<Paciente> getPacientes() { return pacientes; }

    public void addPaciente(Paciente p) {
        if (p == null) return;
        if (!pacientes.contains(p)) pacientes.add(p);
        if (!p.getMedicos().contains(this)) p.addMedico(this);
    }

    public void removePaciente(Paciente p) {
        if (p == null) return;
        if (pacientes.remove(p)) {
            if (p.getMedicos().contains(this)) p.removeMedico(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Medico{name='%s', esp='%s', pacientes=%d}", nombre, especialidad, pacientes.size());
    }
}
