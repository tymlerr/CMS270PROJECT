package refactorGUI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Controller {

    Map<String, JPanel> panels;

    JFrame frame;
    CardLayout cardLayout;
    JPanel mainPanel;

    public enum ScreenNames {
        welcome,
        studentDash,
        professorDash,
        registration
    }

    public Controller()
    {
        frame = new JFrame("Rollins Clubs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        panels = new HashMap<String,JPanel>();

        frame.add(mainPanel);
        frame.setVisible(true);

        initWelcomePanel();
        initRegistrationPanel();
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

    private void initWelcomePanel()
    {
        WelcomePanel w  = new WelcomePanel();
        addScreen(w, ScreenNames.welcome);

        w.setLoginListener(e -> {
            // handle login
            JOptionPane.showMessageDialog(mainPanel, "Not implemented yet pal....");
        });
        
        w.setCreateAccountListener(e -> {
            showScreen(ScreenNames.registration);
        });
    }

    public void initRegistrationPanel()
    {
        RegistrationPanel r = new RegistrationPanel();
        addScreen(r, ScreenNames.registration);

        r.setCreateAccountListener(e -> {
            // handle login
            JOptionPane.showMessageDialog(mainPanel, "Not implemented yet pal....");
        });

        r.setBackListener(e -> {
            showScreen(ScreenNames.welcome);
        });
    }
}
