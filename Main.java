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
                String output = runEmployerAdminLogic();
                showOutputDialog(frame, "Employer Admin Output", output);
            }
        });

        polygonsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String output = runPolygonsLogic();
                showOutputDialog(frame, "Polygons Output", output);
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

    private static String runEmployerAdminLogic() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Employer Admin ---\n");
        Curso java101 = new Curso("Java 101");
        Profesor prof = new Profesor("Ana Perez", java101, 500.0);
        Administrador admin = new Administrador("Luis Gomez", "Recursos Humanos", 400.0);

        sb.append(Nomina.generarPago(prof)).append("\n");
        sb.append(Nomina.generarPago(admin)).append("\n");
        return sb.toString();
    }

    private static String runPolygonsLogic() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Polygons ---\n");
        double[][] examples = new double[][]{
            {3, 4, 5},
            {4, 6, 4, 6},
            {5, 5, 5, 5, 5},
            {6, 6, 6, 6, 6, 6}
        };

        for (double[] ex : examples) {
            try {
                Poligono p = Poligonos.fromSides(ex);
                sb.append(String.format("%s -> %s%n", java.util.Arrays.toString(ex), p.obtenerInfo()));
            } catch (Exception e) {
                sb.append(String.format("Input %s -> ERROR: %s%n", java.util.Arrays.toString(ex), e.getMessage()));
            }
        }
        return sb.toString();
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