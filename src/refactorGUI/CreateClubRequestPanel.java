package refactorGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import backend.Professor;

public class CreateClubRequestPanel extends JPanel implements IResetable{
    private JTextField clubNameField;
    private JComboBox<Professor> advisorChoices;
    private JButton sendRequestButton;
    private JButton backButton;

    public CreateClubRequestPanel()
    {
        setLayout(new BorderLayout());
        createTitleLabel();
        createClubFieldsPanel();
    }

    private void createClubFieldsPanel() {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));

        clubNameField = new JTextField();
        advisorChoices = new JComboBox<>();

        fieldPanel.add(GuiUtils.createLabeledRow("Club Name:", clubNameField));
        fieldPanel.add(GuiUtils.createLabeledRow("Advisor:", advisorChoices));

        sendRequestButton = new JButton("Send Request");
        fieldPanel.add(sendRequestButton);

        backButton = new JButton("Back");
        fieldPanel.add(backButton);

        GuiUtils.autoStrutAndCenterChildren(fieldPanel, 10);

        add(fieldPanel, BorderLayout.CENTER);
    }

    private void createTitleLabel()
    {
        JLabel titleLabel = new JLabel("Create Account");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);
    }

    public void populateProfessorChoices(Professor[] profs)
    {
        advisorChoices.removeAllItems();

        for (Professor professor : profs) {
            advisorChoices.addItem(professor);   
        }

        advisorChoices.validate();
    }

    public String getClubName()
    {
        return clubNameField.getText();
    }

    public Professor getSelectedProfessor()
    {
        return advisorChoices.getItemAt(advisorChoices.getSelectedIndex());
    }

    public void setSendRequestButtonListener(ActionListener e)
    {
        sendRequestButton.addActionListener(e);
    }

    public void setBackButtonListener(ActionListener e)
    {
        backButton.addActionListener(e);
    }

    public void reset() {
        clubNameField.setText(null);
    }
}
