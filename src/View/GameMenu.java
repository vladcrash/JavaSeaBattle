package View;

import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameMenu {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton buttonPlay = new JButton("Play");
    JButton buttonAbout = new JButton("About");
    JButton buttonExit = new JButton("Exit");
    JLabel label = new JLabel();

    public void createMenu() {
        frame.setSize(300, 400);
        frame.setTitle("SeaBattle");
        panel.setLayout(new GridLayout(0, 1));

        buttonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel);
                GameWindow  gameWindow = new GameWindow();
                gameWindow.createGameField(frame);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Game game = new Game();
                        game.start();
                    }
                }).start();

            }
        });
        buttonAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Ура!"};
                JOptionPane.showOptionDialog(frame,
                        "Если вы это читаете, значит, я ... \nНаконец-таки дописал эту программу.",
                        "About",
                        JOptionPane.YES_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);

            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(buttonPlay);
        panel.add(buttonAbout);
        panel.add(buttonExit);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);
    }
}
