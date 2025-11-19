package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;

public class MainGUI {

    JFrame frame;
    CardLayout cardLayout;
    JPanel mainPanel;

    // Panel names
    public enum ScreenNames {
        welcome,
        login,
        studentDash,
        professorDash,
        registration
    }

    public MainGUI() {
        frame = new JFrame("Rollins Clubs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create screens
        mainPanel.add(new WelcomePanel(this), ScreenNames.welcome.toString());
        mainPanel.add(new LoginPanel(this), ScreenNames.login.toString());
        mainPanel.add(new RegistrationPanel(this), ScreenNames.registration.toString());

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Switch screen
    public void showScreen(ScreenNames screen) {
        cardLayout.show(mainPanel, screen.toString());
    }

    // Create student dashboard after login
    public void loadStudentDashboard(Student stu) {
        mainPanel.add(new StudentDashboardPanel(this, stu), ScreenNames.studentDash.toString());
        showScreen(ScreenNames.studentDash);
    }

    // Create professor dashboard after login
    public void loadProfessorDashboard(Professor prof) {
        mainPanel.add(new ProfessorDashboardPanel(this, prof), ScreenNames.professorDash.toString());
        showScreen(ScreenNames.professorDash);
    }

    public static void main(String[] args) {
        otherStuff.TestData.populate();
        new MainGUI();
    }
}
