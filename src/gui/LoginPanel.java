package gui;

import backend.*;
import gui.MainGUI.ScreenNames;

import javax.swing.*;

public class LoginPanel extends JPanel {

    JTextField usernameField;
    JPasswordField passwordField;

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

        JButton loginBtn = new JButton("Sign In");
        loginBtn.setBounds(230, 260, 150, 50);

        JButton backButton = new JButton("Back");
        backButton.setBounds(230, 320, 150, 50);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            Account acc = SessionManager.attemptLogin(user, pass);

            if (acc == null) {
                JOptionPane.showMessageDialog(this, "Incorrect username or password!");
                return;
            }
            
            reset();

            // Login success
            if (acc instanceof Student) {
                gui.loadStudentDashboard((Student) acc);
            } else {
                gui.loadProfessorDashboard((Professor) acc);
            }
        });

        backButton.addActionListener(e -> {
            reset();
            gui.showScreen(ScreenNames.welcome);
        });

        add(title);
        add(uLabel);
        add(usernameField);
        add(pLabel);
        add(passwordField);
        add(loginBtn);
        add(backButton);
    }

    public void reset()
    {
        usernameField.setText("");
        passwordField.setText("");
    }
}
