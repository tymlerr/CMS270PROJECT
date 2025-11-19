package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.AccountManager;
import gui.MainGUI.ScreenNames;

public class RegistrationPanel extends JPanel{
    JTextField displayNameField;
    JTextField usernameField;
    JPasswordField passwordField;

    JComboBox<String> typeSelect; // Student / Professor

    public RegistrationPanel(MainGUI gui) {
        setLayout(null);

        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setBounds(100, 20, 400, 40);

        // display name - y 100
        JLabel dLabel = new JLabel("Display Name:");
        dLabel.setBounds(180, 100, 120, 30);

        displayNameField = new JTextField();
        displayNameField.setBounds(280, 100, 150, 30);

        // username - y 150
        JLabel uLabel = new JLabel("Username:");
        uLabel.setBounds(180, 150, 120, 30);

        usernameField = new JTextField();
        usernameField.setBounds(280, 150, 150, 30);

        // password - y 200
        JLabel pLabel = new JLabel("Password:");
        pLabel.setBounds(180, 200, 120, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(280, 200, 150, 30);

        // type selector - y 250
        JLabel typeLabel = new JLabel("I am a:");
        typeLabel.setBounds(180, 250, 120, 30);

        typeSelect = new JComboBox<>(new String[]{"Student", "Professor"});
        typeSelect.setBounds(280, 250, 150, 30);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(230, 305, 150, 50);

        JButton backButton = new JButton("Back");
        backButton.setBounds(230, 370, 150, 50);

        createAccountButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());
            String display = displayNameField.getText();

            String type = (String) typeSelect.getSelectedItem();

            if(AccountManager.getAccountByName(user) != null)
            {
                JOptionPane.showMessageDialog(this, "Username Taken!");
                return;
            }

            if(type == "Student")
            {
                gui.loadStudentDashboard(AccountManager.addStudent(display, user, pass));
            } else {
                gui.loadProfessorDashboard(AccountManager.addProfessor(display, user, pass));
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
        add(createAccountButton);
        add(typeLabel);
        add(backButton);
        add(typeSelect);
        add(displayNameField);
        add(dLabel);
    }

    public void reset()
    {
        usernameField.setText("");
        passwordField.setText("");
        displayNameField.setText("");
        typeSelect.setSelectedIndex(0);
    }
}
