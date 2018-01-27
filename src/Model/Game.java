package Model;

import java.awt.*;
import java.util.Scanner;

import static View.GameWindow.userButtons;


public class Game {
    Scanner in = new Scanner(System.in);
    Field userField;
    Field compField;
    boolean win = true;

    public void start() {
        System.out.println("Привествую тебя игрок!");
        userField = new Field();
        userField.createField();
        userField.createShipsAuto();
        drawShips(userField);
        userField.show();
        System.out.println();

        compField = new Field();
        compField.createField();
        compField.createShipsAuto();
        compField.show();

        while (win) {
            System.out.println("Стреляй, братишка!");
            User.doShoot(compField);
            if(compField.ships.isEmpty()) {
                break;
            }

            Computer.doShoot(userField);
            if(userField.ships.isEmpty()) {
                break;
            }
            showFields();
        }
        System.exit(0);
    }

    private void drawShips(Field userField) {
        for (Ship ship : userField.ships) {
            if (ship.isHorizontal) {
                for (int i = ship.head.x; i < ship.tail.x + 1; i++) {
                    userButtons[i][ship.head.y].setBackground(new Color(0xE5B362));
                }
            } else {
                for (int i = ship.head.y; i < ship.tail.y + 1; i++) {
                    userButtons[ship.head.x][i].setBackground(new Color(0xE5B362));
                }
            }
        }
    }

    private void showFields() {
        userField.show();
        System.out.println();
        compField.show();
    }

}
