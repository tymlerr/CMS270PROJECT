package gui;

import backend.*;
import gui.MainGUI.ScreenNames;

import javax.swing.*;

public class StudentDashboardPanel extends JPanel {

    public StudentDashboardPanel(MainGUI gui, Student student) {
        setLayout(null);

        JLabel title = new JLabel("Student Dashboard - " + student.getDisplayName());
        title.setBounds(20, 20, 400, 40);

        JLabel clubsLbl = new JLabel("Your Clubs:");
        clubsLbl.setBounds(20, 70, 100, 30);

        DefaultListModel<String> clubsModel = new DefaultListModel<>();

        for (Club c : student.getClubs()) {
            clubsModel.addElement(c.getClubName());
        }

        JButton logoutButton = new JButton("Log out");
        logoutButton.setBounds(420, 10, 150, 50);

        logoutButton.addActionListener(e -> {
            SessionManager.logOut();
            gui.showScreen(ScreenNames.welcome);
        });

        JList<String> clubList = new JList<>(clubsModel);
        JScrollPane scroll = new JScrollPane(clubList);
        scroll.setBounds(20, 110, 200, 250);

        JButton joinBtn = new JButton("Join Club");
        joinBtn.setBounds(260, 110, 150, 40);

        JButton leaveBtn = new JButton("Leave Club");
        leaveBtn.setBounds(260, 170, 150, 40);

        add(title);
        add(clubsLbl);
        add(scroll);
        add(joinBtn);
        add(leaveBtn);
        add(logoutButton);
    }
}
