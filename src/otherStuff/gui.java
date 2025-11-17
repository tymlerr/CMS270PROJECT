package otherStuff;
import javax.swing.*;
import java.awt.event.*;

public class gui implements ActionListener {

    // main frames
    JFrame frame = new JFrame("Center for Rollins Clubs"); 
    JFrame welcomeFrame = new JFrame("Welcome!");
    JFrame userTypeFrame = new JFrame("Select User Type");

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

    // user type screen components
    JButton studentButton = new JButton("Student");
    JButton professorButton = new JButton("Professor");
    JLabel userTypePrompt = new JLabel("Are you a student or professor?");

    // track user type (for later use if needed)
    String userType = "";

    public static void main(String[] args) {
        gui app = new gui();
        app.showWelcomeScreen();
    }

    // Action listener for the sign-in button
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("User type: " + userType);
        System.out.println("Username: " + usernameField.getText());
        System.out.println("Password: " + passwordField.getText());
        System.out.println("Stay signed in: " + staySignedIn.isSelected());
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
            showUserTypeScreen(); // go to new screen
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

    // NEW SCREEN: STUDENT or PROFESSOR
    public void showUserTypeScreen() {
        userTypeFrame.setLayout(null);
        userTypeFrame.setSize(400, 300);
        userTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userTypeFrame.setResizable(false);

        userTypePrompt.setBounds(100, 50, 250, 30);
        studentButton.setBounds(80, 120, 100, 40);
        professorButton.setBounds(200, 120, 120, 40);

        // Button listeners
        studentButton.addActionListener(e -> {
            userType = "Student";
            userTypeFrame.dispose();
            makeGui(); // go to login screen
        });

        professorButton.addActionListener(e -> {
            userType = "Professor";
            userTypeFrame.dispose();
            makeGui();
        });

        userTypeFrame.add(userTypePrompt);
        userTypeFrame.add(studentButton);
        userTypeFrame.add(professorButton);
        userTypeFrame.setVisible(true);
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
