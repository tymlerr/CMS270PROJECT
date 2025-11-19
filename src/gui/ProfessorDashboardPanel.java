package gui;

import backend.*;
import gui.MainGUI.ScreenNames;

import javax.swing.*;

public class ProfessorDashboardPanel extends JPanel {

    public ProfessorDashboardPanel(MainGUI gui, Professor prof) {
        setLayout(null);

        JLabel title = new JLabel("Professor Dashboard - " + prof.getDisplayName());
        title.setBounds(20, 20, 400, 40);

        Club advising = prof.getAdvisingClub();

        JButton logoutButton = new JButton("Log out");
        logoutButton.setBounds(420, 10, 150, 50);

        logoutButton.addActionListener(e -> {
            SessionManager.logOut();
            gui.showScreen(ScreenNames.welcome);
        });

        add(logoutButton);

        if (advising != null) {
            JLabel clubLabel = new JLabel("You advise: " + advising.getClubName());
            clubLabel.setBounds(20, 80, 300, 30);

            DefaultListModel<String> members = new DefaultListModel<>();

            for (Student s : advising.getClubMembers()) {
                members.addElement(s.getDisplayName());
            }

            JList<String> memberList = new JList<>(members);
            JScrollPane scroll = new JScrollPane(memberList);
            scroll.setBounds(20, 120, 200, 250);

            add(clubLabel);
            add(scroll);
        } else {
            JLabel noClub = new JLabel("You are not advising a club yet.");
            noClub.setBounds(20, 80, 300, 30);

            JButton viewInv = new JButton("View Invitations");
            viewInv.setBounds(20, 130, 150, 40);

            add(noClub);
            add(viewInv);
        }

        add(title);
    }
}
