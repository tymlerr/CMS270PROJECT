package refactorGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomePanel extends JPanel implements IResetable{

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton createAccountButton;

    public WelcomePanel()
    {
        setLayout(new BorderLayout());

        createTitleLabel();
        createLoginPanel();
    }

    private void createTitleLabel()
    {
        JLabel titleLabel = new JLabel("Welcome!");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);
    }

    private void createLoginPanel()
    {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        createAccountButton =  new JButton("Create Account");

        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(GuiUtils.createLabeledRow("Username:", usernameField));
        loginPanel.add(GuiUtils.createLabeledRow("Password:", passwordField));
        loginPanel.add(loginButton);
        loginPanel.add(createAccountButton);

        loginPanel.add(Box.createVerticalGlue());

        GuiUtils.autoStrutAndCenterChildren(loginPanel, 10);
        add(loginPanel, BorderLayout.CENTER);
    }

    public String getUsername()
    {
        return usernameField.getText();
    }

    public String getPassword()
    {
        return new String(passwordField.getPassword());
    }

    public void reset()
    {
        usernameField.setText(null);
        passwordField.setText(null);
    }

    public void setLoginListener(ActionListener e)
    {
        loginButton.addActionListener(e);
    }
    
    public void setCreateAccountListener(ActionListener e)
    {
        createAccountButton.addActionListener(e);
    }
}
