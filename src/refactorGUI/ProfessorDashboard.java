package refactorGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.Account;
import backend.Club;
import backend.ClubRequest;
import backend.Student;

public class ProfessorDashboard extends JPanel {
    public enum Mode { acceptRequests, advisorView }
    private JButton logoutButton;
    private JLabel accountNameLabel;
    
    private JPanel reqDisplayPanel;
    private JPanel centerPanel;

    private JPanel advisingClubMemberPanel;
    private JLabel advisingClubNameLabel;

    public ProfessorDashboard()
    {   
        setLayout(new BorderLayout());
        createHeadboard();
        createCenterPanel();
    }

    private void createCenterPanel() {
        JPanel center = new JPanel(new CardLayout());
        centerPanel = center;
        center.add(createRequestViewer(), Mode.acceptRequests.toString());
        center.add(createClubAdvisoryView(), Mode.advisorView.toString());

        add(center, BorderLayout.CENTER);
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

    private JPanel createRequestViewer()
    {
        JPanel viewer = new JPanel();
        viewer.setLayout(new BoxLayout(viewer, BoxLayout.Y_AXIS));
        // viewer.setOpaque(true);
        // viewer.setBackground(Color.black);
        viewer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JLabel title = new JLabel("Requests");
        title.setAlignmentX(Component.CENTER_ALIGNMENT); 
        viewer.add(title);

        viewer.add(Box.createVerticalStrut(10));

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        reqDisplayPanel = displayPanel;
        
        JScrollPane scrollPane = new JScrollPane(displayPanel);

        JPanel scrollWrapper = new JPanel();
        scrollWrapper.setLayout(new BorderLayout());
        scrollWrapper.add(scrollPane, BorderLayout.CENTER);

        // touch this at all and it will explode
        scrollWrapper.setMaximumSize(new Dimension(300, Integer.MAX_VALUE));
        scrollWrapper.setPreferredSize(new Dimension(300, 400));
        
        viewer.add(scrollWrapper);

        return viewer;
    }

    public void displayAdvisingClub(Club club)
    {
        advisingClubNameLabel.setText(club.getClubName());
        advisingClubMemberPanel.removeAll();

        for (Student stu : club.getClubMembers()) {
            advisingClubMemberPanel.add(new JLabel(stu.getDisplayName()));
        }

        GuiUtils.autoStrutAndCenterChildren(advisingClubMemberPanel, 10);

        advisingClubMemberPanel.revalidate();
        advisingClubMemberPanel.repaint();
    }

    private JPanel createClubAdvisoryView()
    {
        JPanel advisorView = new JPanel();
        advisorView.setLayout(new BoxLayout(advisorView, BoxLayout.Y_AXIS));
        advisorView.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        advisingClubNameLabel = new JLabel("Club overview");
        advisingClubNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        advisorView.add(advisingClubNameLabel);

        advisorView.add(Box.createVerticalStrut(10));

        JLabel memberViewTitle = new JLabel("Members");
        memberViewTitle.setAlignmentX(Component.CENTER_ALIGNMENT); 
        advisorView.add(memberViewTitle);

        advisorView.add(Box.createVerticalStrut(5));

        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        advisingClubMemberPanel = memberPanel;
        
        JScrollPane scrollPane = new JScrollPane(memberPanel);

        JPanel scrollWrapper = new JPanel();
        scrollWrapper.setLayout(new BorderLayout());
        scrollWrapper.add(scrollPane, BorderLayout.CENTER);

        // touch this at all and it will explode
        scrollWrapper.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));
        scrollWrapper.setPreferredSize(new Dimension(250, 400));
        
        advisorView.add(scrollWrapper);

        return advisorView;
    }

    public void switchModes(Mode mode)
    {
        CardLayout cl = (CardLayout) centerPanel.getLayout();
        cl.show(centerPanel, mode.toString());
    }

    public JPanel getReqDisplayPanel()
    {
        return reqDisplayPanel;
    }

    public void displayAs(Account account)
    {
        accountNameLabel.setText("Hello " + account.getDisplayName());
    }

    public void setLogoutListener(ActionListener e)
    {
        logoutButton.addActionListener(e);
    }
}

class RequestDisplay extends JPanel
{
    private JLabel clubNameLabel;
    private JLabel sendingStudentLabel;

    private JButton acceptButton;
    private JButton rejectButton;

    RequestDisplay(ClubRequest req)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createInfoDisplay();
        add(Box.createVerticalStrut(10));
        createChoiceButtons();
        add(Box.createVerticalStrut(5));

        clubNameLabel.setText(req.potentialClubName);
        sendingStudentLabel.setText(req.senderStudent.getDisplayName());
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200,200,200)));
    }

    private void createChoiceButtons() {
        JPanel choiceButtons = new JPanel();
        choiceButtons.setLayout(new BoxLayout(choiceButtons, BoxLayout.X_AXIS));

        choiceButtons.add(Box.createHorizontalGlue());

        acceptButton = new JButton("Accept");
        choiceButtons.add(acceptButton);

        choiceButtons.add(Box.createHorizontalStrut(5));

        rejectButton = new JButton("Reject");
        choiceButtons.add(rejectButton);

        choiceButtons.add(Box.createHorizontalGlue());

        add(choiceButtons);
    }

    private void createInfoDisplay() {
        JPanel infoDisplay = new JPanel();
        infoDisplay.setLayout(new BoxLayout(infoDisplay, BoxLayout.Y_AXIS));

        clubNameLabel = new JLabel("Club name");
        clubNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        infoDisplay.add(clubNameLabel);

        infoDisplay.add(Box.createVerticalStrut(5));

        sendingStudentLabel = new JLabel("Requesting Student");
        sendingStudentLabel.setAlignmentX(CENTER_ALIGNMENT);
        infoDisplay.add(sendingStudentLabel);

        add(infoDisplay);
    }

    public void setAcceptActionListener(ActionListener e)
    {
        acceptButton.addActionListener(e);
    }

    public void setRejectActionListener(ActionListener e)
    {
        rejectButton.addActionListener(e);
    }

    @Override
    public Dimension getMaximumSize() {
        Dimension pref = getPreferredSize();
        return new Dimension(Integer.MAX_VALUE, pref.height);
    }
}
