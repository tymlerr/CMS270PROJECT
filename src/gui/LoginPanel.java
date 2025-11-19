package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    JTextField usernameField;
    JPasswordField passwordField;

    JComboBox<String> typeSelect; // Student / Professor

    public LoginPanel(MainGUI gui) {
        setLayout(null);

        JLabel title = new JLabel("Login", SwingConstants.CENTER);
        title.setBounds(100, 20, 400, 40);

        JLabel uLabel = new JLabel("Username:");
        uLabel.setBounds(180, 100, 120, 30);

        usernameField = new JTextField();
        usernameField.setBounds(280, 100, 150, 30);

        JLabel pLabel = new JLabel("Password:");
        pLabel.setBounds(180, 150, 120, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(280, 150, 150, 30);

        JLabel typeLabel = new JLabel("User Type:");
        typeLabel.setBounds(180, 200, 120, 30);

        typeSelect = new JComboBox<>(new String[]{"Student", "Professor"});
        typeSelect.setBounds(280, 200, 150, 30);

        JButton loginBtn = new JButton("Sign In");
        loginBtn.setBounds(230, 260, 150, 50);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            String type = (String) typeSelect.getSelectedItem();

            Account acc = attemptLogin(user, pass);

            if (acc == null) {
                JOptionPane.showMessageDialog(this, "Incorrect username or password!");
                return;
            }

            // Check type
            if (type.equals("Student") && !(acc instanceof Student)) {
                JOptionPane.showMessageDialog(this, "This is not a student account!");
                return;
            }
            if (type.equals("Professor") && !(acc instanceof Professor)) {
                JOptionPane.showMessageDialog(this, "This is not a professor account!");
                return;
            }

            // Login success
            MainGUI.currentAccount = acc;

            if (acc instanceof Student) {
                gui.loadStudentDashboard((Student) acc);
            } else {
                gui.loadProfessorDashboard((Professor) acc);
            }
        });

        add(title);
        add(uLabel);
        add(usernameField);
        add(pLabel);
        add(passwordField);
        add(typeLabel);
        add(typeSelect);
        add(loginBtn);
    }

    private Account attemptLogin(String user, String pass) {
        for (Account a : AccountManager.accounts) {
            if (a.getUserName().equals(user) && a.getPassword().equals(pass)) {
                return a;
            }
        }
        return null;
    }
}
