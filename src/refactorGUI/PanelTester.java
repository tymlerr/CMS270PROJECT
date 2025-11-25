package refactorGUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import backend.ClubManager;

public class PanelTester {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            //frame.add(new StudentDashboard(new ClubManager()));  

            frame.setVisible(true);
        });
    }
}
