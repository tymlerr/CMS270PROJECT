package refactorGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

import backend.*;


public class StudentDashboard extends JPanel {

    public enum Mode { viewJoined, viewUnjoined }

    private JButton logoutButton;
    private JLabel accountNameLabel;
    private JLabel clubListHeaderLabel;
    private JButton switchModeButton;
    private JButton createClubButton;

    private JList<Club> list;
    private DefaultListModel<Club> listModel;

    private ClubInfoViewer infoViewer;

    private Mode currentMode;

    public StudentDashboard()
    {
        setLayout(new BorderLayout());
        createHeadboard();
        createListDisplayPanel();
        createRightPanel();

        currentMode = Mode.viewUnjoined;
    }

    private void createListDisplayPanel() {
        JPanel listDisplay = new JPanel();
        listDisplay.setPreferredSize(new Dimension(250, Integer.MAX_VALUE));
        listDisplay.setLayout(new BoxLayout(listDisplay, BoxLayout.Y_AXIS));

        listDisplay.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 5));

        clubListHeaderLabel = new JLabel("List header");
        listDisplay.add(clubListHeaderLabel);
        clubListHeaderLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 25));
        clubListHeaderLabel.setAlignmentX(CENTER_ALIGNMENT);

        listDisplay.add(Box.createVerticalStrut(5));

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list);
        listDisplay.add(scrollPane, BorderLayout.WEST);

        add(listDisplay, BorderLayout.WEST);
    }

    private void createRightPanel() {

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 10));
        add(rightPanel, BorderLayout.CENTER);

        // info panel
        
        JPanel infoDisplay = new JPanel();
        infoDisplay.setLayout(new BorderLayout());
        infoDisplay.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        infoViewer = new ClubInfoViewer();
        infoDisplay.add(infoViewer, BorderLayout.CENTER);

        rightPanel.add(infoDisplay, BorderLayout.CENTER);

        // footer

        JPanel footer = new JPanel();
        footer.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));

        footer.add(Box.createHorizontalGlue());
        createClubButton = new JButton("Create Club");
        footer.add(createClubButton);
        footer.add(Box.createHorizontalStrut(5));
        switchModeButton = new JButton("Switch Mode");
        footer.add(switchModeButton);

        rightPanel.add(footer, BorderLayout.SOUTH);
    }

    private void createHeadboard() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        accountNameLabel = new JLabel("Account name");
        logoutButton = new JButton("Log out");

        accountNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        header.add(accountNameLabel, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
    }

    public void unselectAllFromList()
    {
        list.clearSelection();
    }

    public void displayAs(Account account)
    {
        accountNameLabel.setText("Hello " + account.getDisplayName());
    }

    public Mode getCurrentMode()
    {
        return currentMode;
    }

    public void setMode(Mode mode)
    {
        currentMode = mode;
        if(mode == Mode.viewJoined)
        {
            clubListHeaderLabel.setText("Your Clubs:");
            switchModeButton.setText("Join clubs");
        }
        else
        {
            clubListHeaderLabel.setText("Joinable Clubs");
            switchModeButton.setText("See Joined");
        }
    }

    public void displayClubs(Club[] clubs)
    {
        listModel.clear();

        for (Club club : clubs) {
            listModel.addElement(club);
        }

        list.validate();
        list.repaint();
    }

    public void showClub(Club club)
    {
        infoViewer.display(club, currentMode);
    }

    public void hideInfoPanel()
    {
        infoViewer.hideInfoPanel();
    }

    public Club getSelectedClub()
    {
        return list.getSelectedValue();
    }

    public void setLogoutListener(ActionListener e)
    {
        logoutButton.addActionListener(e);
    }

    public void setSwitchModeListener(ActionListener e)
    {
        switchModeButton.addActionListener(e);
    }

    public void setCreateClubListener(ActionListener e)
    {
        createClubButton.addActionListener(e);
    }

    public void setListSelectionListener(ListSelectionListener e)
    {
        list.addListSelectionListener(e);
    }

    public void setLeaveClubActionListener(ActionListener e)
    {
        infoViewer.setLeaveActionListener(e);
    }

    public void setJoinClubActionListener(ActionListener e)
    {
        infoViewer.setJoinActionListener(e);
    }

    private class ClubInfoViewer extends JPanel
    {
        private JButton joinClubButton;
        private JButton leaveClubButton;

        private JLabel clubNameLabel;
        private JLabel advisorNameLabel;

        private JPanel centerPanel;

        ClubInfoViewer()
        {
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

            createHeader();
            createCenter();
            createFooter();
        }

        private void createHeader() {
            JPanel header = new JPanel();
            header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
            header.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

            clubNameLabel = new JLabel("Club name");
            header.add(clubNameLabel);
            clubNameLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 20));
            clubNameLabel.setAlignmentX(CENTER_ALIGNMENT);
            
            header.add(Box.createVerticalStrut(5));

            advisorNameLabel = new JLabel("Advisor");
            header.add(advisorNameLabel);
            advisorNameLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 20));
            advisorNameLabel.setAlignmentX(CENTER_ALIGNMENT);

            header.add(Box.createVerticalStrut(10));

            JLabel memberLabel = new JLabel("Members:");
            header.add(memberLabel);
            memberLabel.setMinimumSize(new Dimension(Integer.MAX_VALUE, 20));
            memberLabel.setAlignmentX(CENTER_ALIGNMENT);

            add(header, BorderLayout.NORTH);
        }

        private void createCenter() {
            JPanel center = new JPanel();
            center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
            centerPanel = center;

            JScrollPane scrollPane = new JScrollPane(center);
            add(scrollPane, BorderLayout.CENTER);
        }

        private void createFooter() {
            JPanel footer = new JPanel();
            footer.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));

            leaveClubButton = new JButton("Leave");
            footer.add(leaveClubButton);
            leaveClubButton.setAlignmentX(CENTER_ALIGNMENT);

            joinClubButton = new JButton("Join Club");
            footer.add(joinClubButton);
            joinClubButton.setAlignmentX(CENTER_ALIGNMENT);

            joinClubButton.setVisible(false);

            add(footer, BorderLayout.SOUTH);
        }

        public void display(Club club, Mode mode)
        {
            setVisible(true);
            clubNameLabel.setText(club.getClubName());
            advisorNameLabel.setText(club.getAdvisor().getDisplayName());

            centerPanel.removeAll();
            for (Student stu : club.getClubMembers()) {
                centerPanel.add(new JLabel(stu.getDisplayName()));
            }

            GuiUtils.autoStrutAndCenterChildren(centerPanel, 3);
            centerPanel.add(Box.createVerticalGlue());

            if(mode == Mode.viewJoined)
            {
                leaveClubButton.setVisible(true);
                joinClubButton.setVisible(false);
            } else {
                leaveClubButton.setVisible(false);
                joinClubButton.setVisible(true);
            }
        }

        public void hideInfoPanel()
        {
            setVisible(false);
        }

        public void setJoinActionListener(ActionListener e)
        {
            joinClubButton.addActionListener(e);
        }

        public void setLeaveActionListener(ActionListener e)
        {
            leaveClubButton.addActionListener(e);
        }
    }
}
