package View;

import Controller.GameController;
import Model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow {
    JPanel mainPanel = new JPanel();
    JPanel userPanel = new JPanel();
    JPanel compPanel = new JPanel();
    JPanel userBox = new JPanel();
    JPanel compBox = new JPanel();
    JLabel userLabel = new JLabel("Player");
    JLabel compLabel = new JLabel("Computer");
    public static JButton[][] userButtons = new JButton[10][10];
    public static JButton[][] compButtons = new JButton[10][10];
    public static JFrame mainFrame;


    public void createGameField(JFrame frame) {
        mainFrame = frame;
        mainPanel.setLayout(new BorderLayout());
        userBox.setLayout(new BoxLayout(userBox, BoxLayout.PAGE_AXIS));
        userBox.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        userBox.add(userLabel);
        createUserPanel();
        userBox.add(userPanel);

        compBox.setLayout(new BoxLayout(compBox, BoxLayout.PAGE_AXIS));
        compBox.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        compBox.add(compLabel);
        createCompPanel();
        compBox.add(compPanel);

        mainPanel.add(userBox, BorderLayout.WEST);
        mainPanel.add(compBox, BorderLayout.EAST);
        frame.add(mainPanel);
        frame.pack();

    }


    public void createUserPanel() {
        userPanel.setLayout(new GridLayout(10, 10));
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 50));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int I = i;
                final int J = j;
                JButton button = new JButton();
                button.setBackground(new Color(0xAAFFBB));
                button.setPreferredSize(new Dimension(50, 50));
                button.setMaximumSize(new Dimension(50, 50));
                button.setMinimumSize(new Dimension(50, 50));
                userButtons[j][i] = button;
                userPanel.add(button);
            }

        }
    }

    public void createCompPanel() {
        compPanel.setLayout(new GridLayout(10, 10));
        compPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 30));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int I = i;
                final int J = j;
                JButton button = new JButton();
                button.setBackground(new Color(0xAAFFBB));
                button.setPreferredSize(new Dimension(50, 50));
                button.setMaximumSize(new Dimension(50, 50));
                button.setMinimumSize(new Dimension(50, 50));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GameController.doShoot(new Point(J, I));
                    }
                });
                compButtons[j][i] = button;
                compPanel.add(button);
            }
        }
    }

    public static void writeWinner(String winner) {
        JOptionPane.showMessageDialog(mainFrame, winner);
    }
}
