package gui;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel(MainGUI gui) {
        setLayout(null);

        JLabel title = new JLabel("Welcome to Rollins Clubs!", SwingConstants.CENTER);
        title.setBounds(100, 40, 400, 40);

        JButton existingBtn = new JButton("Existing User");
        existingBtn.setBounds(200, 140, 200, 50);

        JButton newUserBtn = new JButton("New User");
        newUserBtn.setBounds(200, 220, 200, 50);

        existingBtn.addActionListener(e -> gui.showScreen(MainGUI.LOGIN));

        newUserBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "New user registration not implemented yet.")
        );

        add(title);
        add(existingBtn);
        add(newUserBtn);
    }
}
