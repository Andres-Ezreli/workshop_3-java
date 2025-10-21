
import EmployerAdmin.*;
import Polygons.*;
import Hospital. *;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Project Runner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Create buttons
        JButton employerButton = new JButton("Employer Admin");
        JButton polygonsButton = new JButton("Polygons");
        JButton hospitalButton = new JButton("Hospital");

        // Add action listeners
        employerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runEmployerAdminLogic(frame);
            }
        });

        polygonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runPolygonsLogic(frame);
            }
        });

        hospitalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String output = runHospitalLogic();
                showOutputDialog(frame, "Hospital Output", output);
            }
        });

        // Add buttons to the frame
        frame.getContentPane().add(employerButton);
        frame.getContentPane().add(polygonsButton);
        frame.getContentPane().add(hospitalButton);

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    private static void showOutputDialog(Component parent, String title, String message) {
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setText(message);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(parent, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void runEmployerAdminLogic(JFrame parent) {
        String[] options = {"Create Administrator", "Create Professor"};
        int choice = JOptionPane.showOptionDialog(parent, "Select an employee type to create:",
                "Employer Admin", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (choice == -1) return; // User closed the dialog

        try {
            String nombre = JOptionPane.showInputDialog(parent, "Enter name:");
            if (nombre == null) return;

            double salarioBase = Double.parseDouble(JOptionPane.showInputDialog(parent, "Enter base salary:"));
            double bonification = Double.parseDouble(JOptionPane.showInputDialog(parent, "Enter bonification:"));

            if (choice == 0) { // Administrator
                String departamento = JOptionPane.showInputDialog(parent, "Enter department:");
                if (departamento == null) return;

                Administrador admin = new Administrador(nombre, departamento, salarioBase, bonification);
                String output = Nomina.generarPago(admin);
                showOutputDialog(parent, "Administrator Details", output);

            } else { // Professor
                String cursoStr = JOptionPane.showInputDialog(parent, "Enter course name:");
                if (cursoStr == null) return;
                Curso curso = new Curso(cursoStr);

                Profesor prof = new Profesor(nombre, curso, salarioBase, bonification);
                String output = Nomina.generarPago(prof);
                showOutputDialog(parent, "Professor Details", output);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void runPolygonsLogic(JFrame parent) {
        String input = JOptionPane.showInputDialog(parent, "Enter side lengths, separated by commas (e.g., 3,4,5):");
        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            String[] sideStrings = input.split(",");
            double[] sides = new double[sideStrings.length];
            for (int i = 0; i < sideStrings.length; i++) {
                sides[i] = Double.parseDouble(sideStrings[i].trim());
            }

            Poligono p = Poligonos.fromSides(sides);
            String output = String.format("Input: %s\nOutput: %s", input, p.obtenerInfo());
            showOutputDialog(parent, "Polygon Details", output);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Invalid number format. Please enter numbers separated by commas.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            String output = String.format("Input: %s\nError: %s", input, e.getMessage());
            showOutputDialog(parent, "Polygon Error", output);
        }
    }

    // Método para validar nombres (solo letras y espacios)
    private static boolean esNombreValido(String nombre) {
        return nombre != null && nombre.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    // Método para validar nombre de hospital (letras, espacios y algunos caracteres especiales)
    private static boolean esNombreHospitalValido(String nombre) {
        return nombre != null && 
               nombre.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\\s.&'-]+$") && 
               nombre.trim().length() >= 3;  // Mínimo 3 caracteres
    }

    // Método para validar especialidad médica (solo letras y espacios)
    private static boolean esEspecialidadValida(String especialidad) {
        return especialidad != null && especialidad.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    }

    // Método para validar edad (solo números enteros positivos)
    private static boolean esEdadValida(String edadStr) {
        if (edadStr == null || !edadStr.matches("^[0-9]+$")) {
            return false;
        }
        try {
            int edad = Integer.parseInt(edadStr);
            return edad > 0 && edad <= 150;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Método para validar historia clínica (texto con caracteres válidos)
    private static boolean esHistoriaClinicaValida(String historia) {
        return historia != null && 
               historia.trim().matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s,.;:-]+$");
    }

    private static String runHospitalLogic() {
        Hospital hospital = null;
        java.util.List<Medico> medicos = new java.util.ArrayList<>();
        java.util.List<Paciente> pacientes = new java.util.ArrayList<>();
        StringBuilder registro = new StringBuilder();

        while (true) {
            String[] options = {
                "Crear Hospital",
                "Agregar Médico",
                "Agregar Paciente",
                "Asignar Médico a Paciente",
                "Ver Información",
                "Salir"
            };

            int choice = JOptionPane.showOptionDialog(null,
                "Seleccione una operación:",
                "Sistema Hospital",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

            // Si el usuario cierra la ventana o selecciona Salir
            if (choice == -1 || choice == 5) {
                if (hospital != null) {
                    registro.append("\n=== Registro de Operaciones ===\n");
                    registro.append(mostrarInformacionHospital(hospital));
                } else {
                    registro.append("No se creó ningún hospital.");
                }
                break;
            }

            try {
                switch (choice) {
                    case 0: // Crear Hospital
                        if (hospital != null) {
                            JOptionPane.showMessageDialog(null, 
                                "Ya existe un hospital creado.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        String nombreHospital = JOptionPane.showInputDialog(null,
                            "Ingrese el nombre del hospital:\n" +
                            "(Puede contener letras, números, espacios y los símbolos . & ' -)\n" +
                            "Mínimo 3 caracteres",
                            "Crear Hospital",
                            JOptionPane.QUESTION_MESSAGE);

                        if (nombreHospital == null) break; // Usuario canceló

                        if (!esNombreHospitalValido(nombreHospital)) {
                            JOptionPane.showMessageDialog(null,
                                "El nombre del hospital no es válido.\n" +
                                "- Debe tener al menos 3 caracteres\n" +
                                "- Puede contener letras, números, espacios\n" +
                                "- Símbolos permitidos: . & ' -",
                                "Error de Validación",
                                JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        hospital = new Hospital(nombreHospital.trim());
                        registro.append("Hospital creado: ").append(nombreHospital.trim()).append("\n");
                        JOptionPane.showMessageDialog(null,
                            "Hospital creado exitosamente",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                        break;

                    case 1: // Agregar Médico
                        if (hospital == null) {
                            JOptionPane.showMessageDialog(null, "Primero debe crear un hospital.");
                            break;
                        }

                        // Validar nombre del médico
                        String nombreMedico = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
                        if (nombreMedico == null) break;
                        if (!esNombreValido(nombreMedico)) {
                            JOptionPane.showMessageDialog(null, 
                                "El nombre del médico solo debe contener letras y espacios.",
                                "Error de Validación",
                                JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        // Validar especialidad
                        String especialidad = JOptionPane.showInputDialog("Ingrese la especialidad del médico:");
                        if (especialidad == null) break;
                        if (!esEspecialidadValida(especialidad)) {
                            JOptionPane.showMessageDialog(null, 
                                "La especialidad solo debe contener letras y espacios.",
                                "Error de Validación",
                                JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                        Medico medico = new Medico(nombreMedico.trim(), especialidad.trim());
                        hospital.addMedico(medico);
                        medicos.add(medico);
                        registro.append("Médico agregado: ").append(nombreMedico.trim())
                               .append(" (").append(especialidad.trim()).append(")\n");
                        break;

                    case 2: // Agregar Paciente
                        if (hospital == null) {
                            JOptionPane.showMessageDialog(null, "Primero debe crear un hospital.");
                            break;
                        }
                        
                        // Validar nombre
                        String nombrePaciente = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
                        if (nombrePaciente == null) break;
                        if (!esNombreValido(nombrePaciente)) {
                            JOptionPane.showMessageDialog(null, 
                                "El nombre del paciente solo debe contener letras y espacios.",
                                "Error de Validación",
                                JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                        // Validar edad
                        String edadStr = JOptionPane.showInputDialog("Ingrese la edad del paciente (solo números):");
                        if (edadStr == null) break;
                        if (!esEdadValida(edadStr)) {
                            JOptionPane.showMessageDialog(null, 
                                "La edad debe ser un número entero entre 1 y 150.",
                                "Error de Validación",
                                JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        int edad = Integer.parseInt(edadStr);
                        
                        // Historia clínica
                        String historiaClinica = JOptionPane.showInputDialog(
                            "Ingrese la historia clínica del paciente (diagnósticos, tratamientos, etc.):"
                        );
                        if (historiaClinica == null) break;
                        
                        if (historiaClinica.trim().isEmpty()) {
                            int confirm = JOptionPane.showConfirmDialog(null, 
                                "¿Desea continuar sin historia clínica?",
                                "Confirmar", 
                                JOptionPane.YES_NO_OPTION);
                            if (confirm != JOptionPane.YES_OPTION) break;
                            historiaClinica = "Sin historia clínica registrada";
                        } else if (!esHistoriaClinicaValida(historiaClinica)) {
                            JOptionPane.showMessageDialog(null, 
                                "La historia clínica solo puede contener letras, números y signos básicos de puntuación.",
                                "Error de Validación",
                                JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                        Paciente paciente = new Paciente(nombrePaciente.trim(), edad, historiaClinica.trim());
                        hospital.addPaciente(paciente);
                        pacientes.add(paciente);
                        registro.append("Paciente agregado: ").append(nombrePaciente)
                               .append(" (").append(edad).append(" años)\n");
                        break;

                    case 3: // Asignar Médico a Paciente
                        if (hospital == null || medicos.isEmpty() || pacientes.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Debe tener hospital, médicos y pacientes registrados.");
                            break;
                        }
                        
                        // Crear arrays para las opciones
                        String[] medicosOptions = medicos.stream()
                            .map(m -> m.getNombre() + " (" + m.getEspecialidad() + ")")
                            .toArray(String[]::new);
                        
                        String[] pacientesOptions = pacientes.stream()
                            .map(p -> p.getNombre() + " (" + p.getEdad() + " años)")
                            .toArray(String[]::new);

                        // Seleccionar médico
                        int medicoIndex = JOptionPane.showOptionDialog(null,
                            "Seleccione un médico:",
                            "Asignar Médico",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            medicosOptions,
                            medicosOptions[0]);

                        if (medicoIndex == -1) break;

                        // Seleccionar paciente
                        int pacienteIndex = JOptionPane.showOptionDialog(null,
                            "Seleccione un paciente:",
                            "Asignar Paciente",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            pacientesOptions,
                            pacientesOptions[0]);

                        if (pacienteIndex == -1) break;

                        // Realizar la asignación
                        Medico medicoSeleccionado = medicos.get(medicoIndex);
                        Paciente pacienteSeleccionado = pacientes.get(pacienteIndex);
                        pacienteSeleccionado.addMedico(medicoSeleccionado);
                        registro.append("Asignación: ").append(pacienteSeleccionado.getNombre())
                          .append(" -> ").append(medicoSeleccionado.getNombre()).append("\n");
                        break;

                    case 4: // Ver Información
                        if (hospital == null) {
                            JOptionPane.showMessageDialog(null, "No hay información para mostrar.");
                            break;
                        }
                        String info = mostrarInformacionHospital(hospital);
                        JOptionPane.showMessageDialog(null, info, "Información del Hospital", 
                            JOptionPane.INFORMATION_MESSAGE);
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para la edad.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }

        return registro.toString();
    }

    private static String mostrarInformacionHospital(Hospital hospital) {
        StringBuilder info = new StringBuilder();
        info.append("=== Estado Actual del Hospital ===\n");
        info.append(hospital).append("\n");
        
        info.append("\nMédicos:\n");
        for (Medico m : hospital.getMedicos()) {
            info.append("  ").append(m).append("\n");
            info.append("    Pacientes atendidos: ").append(m.getPacientes()).append("\n");
        }
        
        info.append("\nPacientes:\n");
        for (Paciente p : hospital.getPacientes()) {
            info.append("\n  Nombre: ").append(p.getNombre());
            info.append("\n  Edad: ").append(p.getEdad()).append(" años");
            info.append("\n  Historia Clínica: \n    ");
            String historia = p.getHistoriaClinica();
            if (historia != null && !historia.trim().isEmpty()) {
                // Formatear la historia clínica para mejor legibilidad
                info.append(historia.replace("\n", "\n    "));
            } else {
                info.append("No registrada");
            }
            info.append("\n  Médicos asignados: ").append(p.getMedicos()).append("\n");
        }
        return info.toString();
    }
}