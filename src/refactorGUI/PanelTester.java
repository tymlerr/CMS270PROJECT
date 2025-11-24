package refactorGUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PanelTester {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Swap this line to test any panel you want
            frame.add(new RegistrationPanel());  
            // frame.add(new DashboardPanel());
            // frame.add(new AddBookPanel());
            // frame.add(new AllUsersDisplayPanel());

            frame.setVisible(true);
        });
    }
}
