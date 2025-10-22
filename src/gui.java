import javax.swing.*;
import java.awt.event.*;

public class gui implements ActionListener {

    JFrame frame = new JFrame("canvas but awesome");

    JButton signInButton = new JButton("sign in");
    JTextField passwordField = new JTextField();
    JTextField usernameField = new JTextField();
    JCheckBox staySignedIn = new JCheckBox("stay signed in?");
    JLabel passwordLabel = new JLabel("Enter password");
    JLabel usernameLabel = new JLabel("Enter username");

    public static void main(String[] args) {
        gui gui = new gui();
        gui.makeGui();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("username: " + usernameField.getText());
        System.out.println("password: " + passwordField.getText());
        System.out.println(staySignedIn.isSelected());
    }

    public void makeGui()
    {
        signInButton.addActionListener(this);
        
        usernameField.setBounds(240, 110, 120, 30);
        usernameLabel.setBounds(140, 110, 120, 30);
        signInButton.setBounds(150, 230, 220, 50);
        passwordField.setBounds(240, 150, 120, 30);
        staySignedIn.setBounds(150, 190,300, 30);
        passwordLabel.setBounds(140, 150, 120, 30);

        frame.add(signInButton);
        frame.add(usernameField);
        frame.add(usernameLabel);
        frame.add(passwordField);
        frame.add(passwordLabel);
        frame.add(staySignedIn);

        frame.setSize(500, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
