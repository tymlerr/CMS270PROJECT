package refactorGUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import backend.*;
import refactorGUI.StudentDashboard.Mode;

public class Controller {

    AccountManager accountManager;
    ClubManager clubManager;

    Map<String, JPanel> panels;

    JFrame frame;
    CardLayout cardLayout;
    JPanel mainPanel;

    StudentDashboard studentDash;

    public enum ScreenNames {
        welcome,
        studentDash,
        registration
    }

    public Controller()
    {
        accountManager = new AccountManager();
        clubManager = new ClubManager();

        frame = new JFrame("Rollins Clubs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        panels = new HashMap<String,JPanel>();

        frame.add(mainPanel);
        frame.setVisible(true);

        initWelcomePanel();
        initRegistrationPanel();
        initStudentDash();
    }

    public void addScreen(JPanel panel, ScreenNames name)
    {
        mainPanel.add(panel, name.toString());
        panels.put(name.toString(), panel);
    }

    public void showScreen(ScreenNames screen) {
        // auto reset compatible panels before switching to them
        JPanel panel = panels.get(screen.toString());

        if(panel instanceof IResetable resetable)
        {
            resetable.reset();
        }

        cardLayout.show(mainPanel, screen.toString());
    }

    private void initStudentDash() {
        StudentDashboard d = new StudentDashboard();
        addScreen(d, ScreenNames.studentDash);
        studentDash = d;

        d.setLogoutListener(e -> {
            showScreen(ScreenNames.welcome);
            accountManager.logOut();
        });

        d.setListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if(d.getSelectedClub() != null)
                    d.showClub(d.getSelectedClub());
            }
        });

        d.setSwitchModeListener(e -> {
            if(d.getCurrentMode() == StudentDashboard.Mode.viewJoined)
            {
                d.setMode(StudentDashboard.Mode.viewUnjoined);
            } else {
                d.setMode(StudentDashboard.Mode.viewJoined);
            }

            refreshStudentDash();
        });

        d.setJoinClubActionListener(e -> {
            if(accountManager.currentlySignedInAccount instanceof Student stu)
            {
                if(d.getSelectedClub() != null)
                    d.getSelectedClub().addMember(stu);
            }

            refreshStudentDash();
        });

        d.setLeaveClubActionListener(e -> {
            if(accountManager.currentlySignedInAccount instanceof Student stu)
            {
                if(d.getSelectedClub() != null)
                    d.getSelectedClub().removeMember(stu);
            }

            refreshStudentDash();
        });
    }

    private void refreshStudentDash()
    {
        studentDash.unselectAllFromList();
        studentDash.hideInfoPanel();
        if(studentDash.getCurrentMode() == StudentDashboard.Mode.viewUnjoined)
        {
            studentDash.displayClubs(clubManager.getUnjoinedClubs((Student)accountManager.currentlySignedInAccount));
        } else {
            if(accountManager.currentlySignedInAccount instanceof Student stu)
            {
                studentDash.displayClubs(stu.getClubs());
            }
        }
    }

    private void initWelcomePanel()
    {
        WelcomePanel w  = new WelcomePanel();
        addScreen(w, ScreenNames.welcome);

        w.setLoginListener(e -> {
            String user = w.getUsername();
            String pass = w.getPassword();

            if(GuiUtils.isStringBlankOrSpaces(user) || GuiUtils.isStringBlankOrSpaces(pass))
            {
                JOptionPane.showMessageDialog(mainPanel, "Fields Cannot be blank!");
                return;
            }

            Account acc = accountManager.attemptLogin(user, pass);
            if(acc == null)
            {
                JOptionPane.showMessageDialog(mainPanel, "Could not find account.");
                return;
            }

            handleLogin(acc);
        });
        
        w.setCreateAccountListener(e -> {
            showScreen(ScreenNames.registration);
        });
    }

    public void handleLogin(Account loggedIn)
    {
        if(loggedIn instanceof Student stu)
        {
            showScreen(ScreenNames.studentDash);
            studentDash.displayAs(stu);
            studentDash.setMode(Mode.viewUnjoined);
            studentDash.displayClubs(clubManager.getUnjoinedClubs(stu));
            refreshStudentDash();
        }
    }

    public void initRegistrationPanel()
    {
        RegistrationPanel r = new RegistrationPanel();
        addScreen(r, ScreenNames.registration);

        r.setCreateAccountListener(e -> {
            
            if(GuiUtils.isStringBlankOrSpaces(r.getDisplayName()) 
                || GuiUtils.isStringBlankOrSpaces(r.getPassword())
                || GuiUtils.isStringBlankOrSpaces(r.getUsername()))
            {
                JOptionPane.showMessageDialog(mainPanel, "Fields Cannot be blank!");
                return;
            }

            Account acc = accountManager.addAccount(r.getDisplayName(), r.getUsername(), r.getPassword(), r.getAccountType());

            if(acc == null)
            {
                JOptionPane.showMessageDialog(mainPanel, "Username taken!");
                return;
            }

            accountManager.attemptLogin(acc.getUserName(), acc.getPassword());

            handleLogin(acc);
        });

        r.setBackListener(e -> {
            showScreen(ScreenNames.welcome);
        });
    }
}
