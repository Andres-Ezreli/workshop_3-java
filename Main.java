import EmployerAdmin.*;
import Polygons.*;
import Hospital.*;

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

    private static String runHospitalLogic() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Hospital ---\n");
        Hospital h = new Hospital("Hospital La Milagrosa");

        Medico m1 = new Medico("Dr. Juan Perez", "Cardiologia");
        Medico m2 = new Medico("Dra. Maria Lopez", "Neurologia");
        Medico m3 = new Medico("Dr. Carlos Sanchez", "Pediatria");

        h.addMedico(m1);
        h.addMedico(m2);
        h.addMedico(m3);

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

        sb.append(h).append("\n");
        sb.append("Medicos:\n");
        for (Medico m : h.getMedicos()) {
            sb.append("  ").append(m).append(" -> Hospital=").append(m.getHospital().getNombre()).append("\n");
        }

        sb.append("Pacientes:\n");
        for (Paciente p : h.getPacientes()) {
            sb.append("  ").append(p).append(" -> Hospital=").append(p.getHospital().getNombre()).append("\n");
        }

        sb.append("--- relationships ---\n");
        sb.append(p1.getNombre()).append(" medicos: ").append(p1.getMedicos()).append("\n");
        sb.append(p2.getNombre()).append(" medicos: ").append(p2.getMedicos()).append("\n");
        sb.append(p3.getNombre()).append(" medicos: ").append(p3.getMedicos()).append("\n");
        sb.append(m2.getNombre()).append(" pacientes: ").append(m2.getPacientes()).append("\n");

        return sb.toString();
    }
}