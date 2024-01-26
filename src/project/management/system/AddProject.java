package project.management.system;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AddProject extends JFrame implements ActionListener {

    private JTextField tfProjectName, tfClientName, tfBudget, tfProjectManager;
    private JDateChooser dcStartDate, dcEndDate;
    private JComboBox<String> cbStatus;
    private JLabel lblProjectId;
    private JButton add, back;

    private Random random = new Random();
    private int number = random.nextInt(999999);

    public AddProject() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Project Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel[] labels = {
                new JLabel("Project Name"), new JLabel("Client Name"), new JLabel("Start Date"),
                new JLabel("End Date"), new JLabel("Budget"), new JLabel("Project Manager"),
                new JLabel("Status"), new JLabel("Project Id")
        };

        int[] yPositions = {150, 200, 250, 300, 350, 400, 450, 500};

        for (int i = 0; i < labels.length; i++) {
            labels[i].setBounds(50, yPositions[i], 150, 30);
            labels[i].setFont(new Font("serif", Font.PLAIN, 20));
            add(labels[i]);
        }

        tfProjectName = createTextField(200, 150);
        tfClientName = createTextField(200, 200);
        tfBudget = createTextField(200, 350);
        tfProjectManager = createTextField(200, 400);
        dcStartDate = createDateChooser(200, 250);
        dcEndDate = createDateChooser(200, 300);
        cbStatus = createStatusComboBox(200, 450);
        lblProjectId = createLabel(String.valueOf(number), 200, 500);

        add = createButton("Add Details", 250, 550);
        back = createButton("Back", 450, 550);

        setSize(900, 600);
        setLocation(300, 50);
        setVisible(true);
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 30);
        add(textField);
        return textField;
    }

    private JDateChooser createDateChooser(int x, int y) {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(x, y, 150, 30);
        add(dateChooser);
        return dateChooser;
    }

    private JComboBox<String> createStatusComboBox(int x, int y) {
        String[] statusOptions = {"In Progress", "Completed", "Pending"};
        JComboBox<String> comboBox = new JComboBox<>(statusOptions);
        comboBox.setBackground(Color.WHITE);
        comboBox.setBounds(x, y, 150, 30);
        add(comboBox);
        return comboBox;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("serif", Font.PLAIN, 20));
        add(label);
        return label;
    }

    private JButton createButton(String label, int x, int y) {
        JButton button = new JButton(label);
        button.setBounds(x, y, 150, 40);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);
        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            addProjectDetails();
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    private void addProjectDetails() {
        String projectName = tfProjectName.getText();
        String clientName = tfClientName.getText();
        String startDate = ((JTextField) dcStartDate.getDateEditor().getUiComponent()).getText();
        String endDate = ((JTextField) dcEndDate.getDateEditor().getUiComponent()).getText();
        String budget = tfBudget.getText();
        String projectManager = tfProjectManager.getText();
        String status = (String) cbStatus.getSelectedItem();
        String projectId = lblProjectId.getText();

        try {
            conn conn = new conn();
            String query = "insert into projects values('" + projectName + "', '" + clientName + "', '" + startDate + "', '" + endDate + "', '" + budget + "', '" + projectManager + "', '" + status + "', '" + projectId + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Details added successfully");
            setVisible(false);
            new Home();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddProject();
    }
}
