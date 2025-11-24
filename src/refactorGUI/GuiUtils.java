package refactorGUI;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

public class GuiUtils {
    public static JPanel createLabeledRow(String labelText, JComponent field) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setMinimumSize(new Dimension(100, 20));
        label.setPreferredSize(new Dimension(100, 20));
        label.setMaximumSize(new Dimension(100, 20));

        field.setMinimumSize(new Dimension(100, 20));
        field.setPreferredSize(new Dimension(100, 20));
        field.setMaximumSize(new Dimension(100, 20));

        row.add(Box.createHorizontalGlue());
        row.add(label);
        row.add(Box.createHorizontalStrut(10));
        row.add(field);
        row.add(Box.createHorizontalGlue());

        row.setMaximumSize(new Dimension((Integer.MAX_VALUE), 20));

        return row;
    }

    public static void autoStrutAndCenterChildren(JPanel panel, int strutSize)
    {
        Component[] comps = panel.getComponents();
        panel.removeAll();

        for (int i = 0; i < comps.length; i++) {
            Component c = comps[i];

            if (c instanceof JComponent jc) {
                jc.setAlignmentX(Component.CENTER_ALIGNMENT);
            }

            panel.add(c);

            if (i < comps.length - 1) {
                panel.add(Box.createVerticalStrut(strutSize));
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    public static boolean isFieldBlankOrSpaces(JTextField field)
    {
        return field.getText().trim().isEmpty();
    }

    public static boolean isStringBlankOrSpaces(String string)
    {
        return string.trim().isEmpty();
    }

    public int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
