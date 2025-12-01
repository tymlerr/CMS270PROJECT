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
    ProfessorDashboard professorDash;
    CreateClubRequestPanel clubRequestPanel;

    public enum ScreenNames {
        welcome,
        studentDash,
        professorDash,
        registration,
        createRequest
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

        createTestCases();

        initWelcomePanel();
        initRegistrationPanel();
        initStudentDash();
        initCreateRequestPanel();
        initProfessorDash();
    }

    private void createTestCases() {


        Student stuPalmer = (Student)accountManager.addAccount("Erik Palmer", "palmer", "pass", "Student");
        Student stuLam = (Student)accountManager.addAccount("Alison Lam", "lam", "pass", "Student");
        Student stuWolf = (Student)accountManager.addAccount("Laurence Wolf", "wolf", "pass", "Student");
        Student stuRoy = (Student)accountManager.addAccount("Fred Roy", "roy", "pass", "Student");
        Student stuWatson = (Student)accountManager.addAccount("Lacy Watson", "watson", "pass", "Student");
        Student stuBullock = (Student)accountManager.addAccount("Erma Bullock", "bullock", "pass", "Student");
        Student stuBarron = (Student)accountManager.addAccount("Sally Barron", "barron", "pass", "Student");

        Professor profStevens = (Professor)accountManager.addAccount("Prof. Stevens", "stevens", "pass", "Professor");
        Professor profNovak = (Professor)accountManager.addAccount("Prof. Novak", "novak", "pass", "Professor");
        Professor profCavarro = (Professor)accountManager.addAccount("Prof. Cavarro", "cavarro", "pass", "Professor");
        Professor profHawkins = (Professor)accountManager.addAccount("Prof. Hawkins", "hawkins", "pass", "Professor");
        Professor profSparks = (Professor)accountManager.addAccount("Prof. Sparks", "sparks", "pass", "Professor");
        Professor profSnow = (Professor)accountManager.addAccount("Prof. Snow", "snow", "pass", "Professor");
        Professor profRamirez = (Professor)accountManager.addAccount("Prof. Ramirez", "ramirez", "pass", "Professor");

        clubManager.submitClubRequest(profStevens, stuWolf, "C# Appreciation Club");
        Club cSharp = clubManager.acceptRequest(clubManager.requests.get(0));
        cSharp.addMember(stuPalmer);
        cSharp.addMember(stuLam);
        cSharp.addMember(stuRoy);
        cSharp.addMember(stuWatson);
        cSharp.addMember(stuBullock);
        cSharp.addMember(stuBarron);

        clubManager.submitClubRequest(profNovak, stuPalmer, "Cooking Club");
        Club cooking = clubManager.acceptRequest(clubManager.requests.get(0));
        cooking.addMember(stuLam);
        cooking.addMember(stuRoy);
        cooking.addMember(stuBullock);
        cooking.addMember(stuWatson);

        clubManager.submitClubRequest(profCavarro, stuLam, "Robotics Club");
        Club robotics = clubManager.acceptRequest(clubManager.requests.get(0));
        robotics.addMember(stuPalmer);
        robotics.addMember(stuWolf);
        robotics.addMember(stuBarron);

        clubManager.submitClubRequest(profHawkins, stuLam, "Creative Writing Club");
        Club writing = clubManager.acceptRequest(clubManager.requests.get(0));
        writing.addMember(stuLam);
        writing.addMember(stuBarron);
        writing.addMember(stuWatson);
    }

    public void populateRequestView()
    {
        if(accountManager.currentlySignedInAccount instanceof Professor prof)
        {
            JPanel reqDisplayPanel = professorDash.getReqDisplayPanel();
            reqDisplayPanel.removeAll();

            for (ClubRequest req : clubManager.getRequestsByProfessor(prof)) {
                RequestDisplay reqDisplay = new RequestDisplay(req);

                reqDisplay.setAcceptActionListener(e -> onAcceptRequest(req));
                reqDisplay.setRejectActionListener(e -> onRejectRequest(req));

                reqDisplayPanel.add(reqDisplay);
            }

            GuiUtils.autoStrutAndCenterChildren(reqDisplayPanel, 5);

            reqDisplayPanel.revalidate();
            reqDisplayPanel.repaint();
        }
    }

    public void onAcceptRequest(ClubRequest req)
    {
        Club club = clubManager.acceptRequest(req);

        if(club == null)
        {
            onRejectRequest(req);
            JOptionPane.showMessageDialog(mainPanel, "A club by that name already exists! removing...");
            return;
        }

        refreshProfessorDash();
    }

    public void onRejectRequest(ClubRequest req)
    {
        clubManager.requests.remove(req);
        refreshProfessorDash();

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

    private void initProfessorDash() {
        ProfessorDashboard p = new ProfessorDashboard();
        professorDash = p;
        addScreen(p, ScreenNames.professorDash);

        p.setLogoutListener(e -> {
            showScreen(ScreenNames.welcome);
            accountManager.logOut();
        });
    }

    private void initCreateRequestPanel()
    {
        CreateClubRequestPanel c = new CreateClubRequestPanel();
        addScreen(c, ScreenNames.createRequest);
        clubRequestPanel = c;

        c.setBackButtonListener(e -> {
            refreshStudentDash();
            showScreen(ScreenNames.studentDash);
        });

        c.setSendRequestButtonListener(e -> {
            if(GuiUtils.isStringBlankOrSpaces(c.getClubName()))
            {
                JOptionPane.showMessageDialog(mainPanel, "Fields cannot be blank!");
                return;
            }

            ClubRequest req = clubManager.submitClubRequest(c.getSelectedProfessor(), (Student)accountManager.currentlySignedInAccount, c.getClubName());
            if(req == null)
            {
                JOptionPane.showMessageDialog(mainPanel, "Professor already advising a club!");
                return;
            }

            refreshStudentDash();
            showScreen(ScreenNames.studentDash);
            JOptionPane.showMessageDialog(mainPanel, "Request Sent!");
        });
        
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

        d.setCreateClubListener(e -> {
            Professor[] freeProfs = accountManager.getFreeProfessors();
            if(freeProfs.length == 0)
            {
                JOptionPane.showMessageDialog(mainPanel, "All professors are already advising a club!");
                return;
            }

            clubRequestPanel.populateProfessorChoices(freeProfs);
            showScreen(ScreenNames.createRequest);
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

    private void refreshProfessorDash()
    {
        if(accountManager.currentlySignedInAccount instanceof Professor prof)
        {
            Club advising = prof.getAdvisingClub();
            if(advising == null)
            {
                professorDash.switchModes(ProfessorDashboard.Mode.acceptRequests);
                populateRequestView();
            } else {
                professorDash.switchModes(ProfessorDashboard.Mode.advisorView);
                professorDash.displayAdvisingClub(advising);
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
        } else {
            showScreen(ScreenNames.professorDash);
            professorDash.displayAs(loggedIn);
            refreshProfessorDash();
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
