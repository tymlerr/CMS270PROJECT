package refactorGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegistrationPanel extends JPanel implements IResetable{
    private JTextField displayNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JComboBox<String> typeSelect; // Student / Professor

    private JButton createButton;
    private JButton backButton;

    public RegistrationPanel()
    {
        setLayout(new BorderLayout());
        createTitleLabel();
        createRegistrationPanel();
    }

    private void createTitleLabel()
    {
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);
    }

    private void createRegistrationPanel()
    {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        
        displayNameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        typeSelect = new JComboBox<>(new String[]{"Student", "Professor"});
        createButton = new JButton("Create Account");
        backButton = new JButton("Back");


        registerPanel.add(GuiUtils.createLabeledRow("Display Name:", displayNameField));
        registerPanel.add(GuiUtils.createLabeledRow("Username:", usernameField));
        registerPanel.add(GuiUtils.createLabeledRow("Password:", passwordField));
        registerPanel.add(GuiUtils.createLabeledRow("I am a:", typeSelect));

        registerPanel.add(createButton);
        registerPanel.add(backButton);

        registerPanel.add(Box.createVerticalGlue());

        GuiUtils.autoStrutAndCenterChildren(registerPanel, 10);
        add(registerPanel, BorderLayout.CENTER);
    }

    public void reset()
    {
        displayNameField.setText(null);
        usernameField.setText(null);
        passwordField.setText(null);
        typeSelect.setSelectedIndex(0);
    }

    public String getDisplayName()
    {
        return displayNameField.getText();
    }

    public String getUsername()
    {
        return usernameField.getText();
    }

    public String getPassword()
    {
        return new String(passwordField.getPassword());
    }

    public String getAccountType()
    {
        return (String) typeSelect.getSelectedItem();
    }

    public void setCreateAccountListener(ActionListener e)
    {
        createButton.addActionListener(e);
    }

    public void setBackListener(ActionListener e)
    {
        backButton.addActionListener(e);
    }
}
