package Model;

import View.GameWindow;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

import static View.GameWindow.userButtons;

public class Computer {
    public static void doShoot(Field userField) {
        int x = new Random().nextInt(10);
        int y = new Random().nextInt(10);
        Cell cellShoot = userField.field.get(userField.key[x][y]);

        switch (cellShoot.state) {
            case NOT_SET:
                cellShoot.state = Cell.State.MISS;
                userButtons[x][y].setBackground(new Color(0xFFEA8F));
                break;
            case SHIPPOINT:
                cellShoot.state = Cell.State.SHOOT;
                userButtons[x][y].setBackground(new Color(0xFF706B));
                idk(userField, x, y);
                if(userField.ships.isEmpty()) {
                    String winner = "Победил компьютер!";
                    GameWindow.writeWinner(winner);
                    break;
                }
                doShoot(userField);
                break;
            case SHOOT:
                doShoot(userField);
                break;
            case MISS:
                doShoot(userField);
                break;
        }

    }

    private static void idk(Field userField, int x, int y) {
        Iterator<Ship> ship = userField.ships.iterator();
        while (ship.hasNext()) {
            Ship shippy = ship.next();
            int count = 0;
            if (shippy.isHorizontal) {
                if (shippy.head.x <= x && shippy.tail.x >= x && shippy.head.y == y) {
                    for (int head = shippy.head.x; head < shippy.head.x + shippy.deck; head++) {
                        if (userField.field.get(userField.key[head][shippy.head.y]).state == Cell.State.SHOOT) {
                            count++;
                        }
                    }

                    if(count == shippy.deck) {
                        setAreal(userField, shippy);
                        ship.remove();
                    }
                    break;
                }
            } else {
                if (shippy.head.y <= y && shippy.tail.y >= y && shippy.head.x == x) {
                    for (int head = shippy.head.y; head < shippy.head.y + shippy.deck; head++) {
                        if (userField.field.get(userField.key[shippy.head.x][head]).state == Cell.State.SHOOT) {
                            count++;
                        }
                    }

                    if(count == shippy.deck) {
                        setAreal(userField, shippy);
                        ship.remove();
                    }
                    break;
                }
            }
        }
    }

    private static void setAreal(Field userField, Ship ship) {
        Coeffs coeff = new Coeffs();
        userField.defineCoeff(ship, coeff);

        if (ship.isHorizontal) {
            for (int i = coeff.headY; i < coeff.headY + 3 + coeff.coeffVer; i++) {
                for (int j = coeff.headX; j < coeff.headX + ship.deck + 2 + coeff.coeffHor; j++) {
                    if ((userField.field.get(userField.key[j][i])).state == Cell.State.NOT_SET) {
                        userField.field.get(userField.key[j][i]).state = Cell.State.MISS;
                        userButtons[j][i].setBackground(new Color(0xFFEA8F));
                    }
                }
            }
        } else {
            for (int i = coeff.headY; i < coeff.headY + ship.deck + 2 + coeff.coeffVer; i++) {
                for (int j = coeff.headX; j < coeff.headX + 3 + coeff.coeffHor; j++) {
                    if ((userField.field.get(userField.key[j][i])).state == Cell.State.NOT_SET) {
                        userField.field.get(userField.key[j][i]).state = Cell.State.MISS;
                        userButtons[j][i].setBackground(new Color(0xFFEA8F));
                    }
                }
            }
        }
    }
}
