import javax.swing.*;
import java.awt.event.*;

public class gui implements ActionListener {

    // main frames
    JFrame frame = new JFrame("Center for Rollins Clubs"); 
    JFrame welcomeFrame = new JFrame("Welcome!");

    // login screen components
    JButton signInButton = new JButton("Sign In");
    JTextField passwordField = new JTextField();
    JTextField usernameField = new JTextField();
    JCheckBox staySignedIn = new JCheckBox("Stay signed in?");
    JLabel passwordLabel = new JLabel("Enter Password:");
    JLabel usernameLabel = new JLabel("Enter Username:");

    // welcome screen components
    JButton newUserButton = new JButton("New User");
    JButton existingUserButton = new JButton("Existing User");

    public static void main(String[] args) {
        gui app = new gui();
        app.showWelcomeScreen();
    }

    // Action listener for the sign-in button
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("username: " + usernameField.getText());
        System.out.println("password: " + passwordField.getText());
        System.out.println("stay signed in: " + staySignedIn.isSelected());
    }

    // WELCOME SCREEN 
    public void showWelcomeScreen() {
        welcomeFrame.setLayout(null);
        welcomeFrame.setSize(400, 300);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setResizable(false);

        JLabel prompt = new JLabel("Are you a new or existing user?");
        prompt.setBounds(100, 50, 250, 30);

        existingUserButton.setBounds(70, 120, 120, 40);
        newUserButton.setBounds(210, 120, 120, 40);

        // button listeners
        existingUserButton.addActionListener(e -> {
            welcomeFrame.dispose(); // close welcome screen
            makeGui(); // open login screen
        });

        newUserButton.addActionListener(e -> {
            welcomeFrame.dispose();
            showNewUserScreen();
        });

        welcomeFrame.add(prompt);
        welcomeFrame.add(existingUserButton);
        welcomeFrame.add(newUserButton);
        welcomeFrame.setVisible(true);
    }

    // LOGIN SCREEN
    public void makeGui() {
        signInButton.addActionListener(this);
        
        usernameField.setBounds(240, 110, 120, 30);
        usernameLabel.setBounds(140, 110, 120, 30);
        signInButton.setBounds(150, 230, 220, 50);
        passwordField.setBounds(240, 150, 120, 30);
        staySignedIn.setBounds(150, 190, 300, 30);
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

    // NEW USER SCREEN (place holder)
    public void showNewUserScreen() {
        JFrame newUserFrame = new JFrame("Create Account");
        newUserFrame.setLayout(null);
        newUserFrame.setSize(400, 300);
        newUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newUserFrame.setResizable(false);

        JLabel msg = new JLabel("New User Registration (To - Do)");
        msg.setBounds(80, 100, 250, 30);
        newUserFrame.add(msg);

        newUserFrame.setVisible(true);
    }
}
