package Model;

import View.GameWindow;

import java.awt.*;
import java.util.Iterator;

import static View.GameWindow.compButtons;

public class User {
    public static Point point;
    public volatile static boolean isShoot;

    public static void doShoot(Field compField) {
        isShoot = false;
        while (!isShoot);
        int x = point.x;
        int y = point.y;
        Cell cellShoot = compField.field.get(compField.key[x][y]);

        switch (cellShoot.state) {
            case NOT_SET:
                cellShoot.state = Cell.State.MISS;
                compButtons[x][y].setBackground(new Color(0xFFEA8F));
                break;
            case SHIPPOINT:
                cellShoot.state = Cell.State.SHOOT;
                compButtons[x][y].setBackground(new Color(0xFF706B));
                idk(compField, x, y);

                if(compField.ships.isEmpty()) {
                    String winner = "Вы победили!";
                    GameWindow.writeWinner(winner);
                    break;
                }

                doShoot(compField);
                break;
            case SHOOT:
                System.out.println("В этом месте корабль уже подбит!\nДавай-ка еще разок");
                doShoot(compField);
                break;
            case MISS:
                System.out.println("Сюда уже стреляли! Давай по новой");
                doShoot(compField);
                break;
        }
    }

    private static void idk(Field compField, int x, int y) {
        Iterator<Ship> ship = compField.ships.iterator();
        while (ship.hasNext()) {
            Ship shippy = ship.next();
            int count = 0;
            if (shippy.isHorizontal) {
                if (shippy.head.x <= x && shippy.tail.x >= x && shippy.head.y == y) {
                    for (int head = shippy.head.x; head < shippy.head.x + shippy.deck; head++) {
                        if (compField.field.get(compField.key[head][shippy.head.y]).state == Cell.State.SHOOT) {
                            count++;
                        }
                    }

                    if(count != shippy.deck) {
                        System.out.println("Ранил!");
                    } else {
                        System.out.println("Убил!");
                        setAreal(compField, shippy);
                        ship.remove();
                    }
                    break;
                }
            } else {
                if (shippy.head.y <= y && shippy.tail.y >= y && shippy.head.x == x) {
                    for (int head = shippy.head.y; head < shippy.head.y + shippy.deck; head++) {
                        if (compField.field.get(compField.key[shippy.head.x][head]).state == Cell.State.SHOOT) {
                            count++;
                        }
                    }

                    if(count != shippy.deck) {
                        System.out.println("Ранил!");
                    } else {
                        System.out.println("Убил!");
                        setAreal(compField, shippy);
                        ship.remove();
                    }
                    break;
                }
            }
        }
    }

    private static void setAreal(Field compField, Ship ship) {
        Coeffs coeff = new Coeffs();
        compField.defineCoeff(ship, coeff);

        if (ship.isHorizontal) {
            for (int i = coeff.headY; i < coeff.headY + 3 + coeff.coeffVer; i++) {
                for (int j = coeff.headX; j < coeff.headX + ship.deck + 2 + coeff.coeffHor; j++) {
                    if ((compField.field.get(compField.key[j][i])).state == Cell.State.NOT_SET) {
                        compField.field.get(compField.key[j][i]).state = Cell.State.MISS;
                        compButtons[j][i].setBackground(new Color(0xFFEA8F));
                    }
                }
            }
        } else {
            for (int i = coeff.headY; i < coeff.headY + ship.deck + 2 + coeff.coeffVer; i++) {
                for (int j = coeff.headX; j < coeff.headX + 3 + coeff.coeffHor; j++) {
                    if ((compField.field.get(compField.key[j][i])).state == Cell.State.NOT_SET) {
                        compField.field.get(compField.key[j][i]).state = Cell.State.MISS;
                        compButtons[j][i].setBackground(new Color(0xFFEA8F));
                    }
                }
            }
        }
    }


}
