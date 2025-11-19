package gui;

import backend.*;

import javax.swing.*;
import java.awt.*;

public class MainGUI {

    JFrame frame;
    CardLayout cardLayout;
    JPanel mainPanel;

    // Logged-in user
    public static Account currentAccount;

    // Panel names
    public static final String WELCOME = "Welcome";
    public static final String LOGIN = "Login";
    public static final String STUDENT_DASH = "StudentDash";
    public static final String PROFESSOR_DASH = "ProfessorDash";

    public MainGUI() {
        frame = new JFrame("Rollins Clubs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create screens
        mainPanel.add(new WelcomePanel(this), WELCOME);
        mainPanel.add(new LoginPanel(this), LOGIN);
        // Dashboards are created only after login

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Switch screen
    public void showScreen(String screen) {
        cardLayout.show(mainPanel, screen);
    }

    // Create student dashboard after login
    public void loadStudentDashboard(Student stu) {
        mainPanel.add(new StudentDashboardPanel(stu), STUDENT_DASH);
        showScreen(STUDENT_DASH);
    }

    // Create professor dashboard after login
    public void loadProfessorDashboard(Professor prof) {
        mainPanel.add(new ProfessorDashboardPanel(prof), PROFESSOR_DASH);
        showScreen(PROFESSOR_DASH);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
