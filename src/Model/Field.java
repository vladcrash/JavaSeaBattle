package Model;

import java.util.*;


public class Field {
    public static final int FIELD_SIZE = 10;
    public static final int ONEDECK = 4;
    public static final int TWODECK = 3;
    public static final int THREEDECK = 2;
    public static final int FOURDECK = 1;
    public static final int ALLSHIPS = 10;

    Map<Point, Cell> field;
    Point[][] key = new Point[FIELD_SIZE][FIELD_SIZE];
    ArrayList<Ship> ships = new ArrayList<>();
    Ship ship;

    public void createField() {
        field = new LinkedHashMap<>();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                Point point = new Point(j, i);
                field.put(point, new Cell());
                key[j][i] = point;
            }
        }
    }

    public void show() {
        Set<Point> points = field.keySet();
        Iterator<Point> iterator = points.iterator();
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                Point point = iterator.next();
                switch ((field.get(point)).state) {
                    case MISS:
                        System.out.print('.' + " ");
                        break;
                    case SHIPPOINT:
                        System.out.print('\u262F' + " ");
                        break;
                    case SHOOT:
                        System.out.print('x' + " ");
                        break;
                    case NOT_SET:
                        System.out.print('-' + " ");
                        break;
                }
            }
            System.out.println();
        }


    }

    public void createShipsAuto() {
        int count = 0;
//        coeff = new Coeffs();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j <= i; j++) {
                do {
                    ship = new Ship(5 - (i + 1), new Random().nextBoolean());
                    Point point = new Point((new Random().nextInt(FIELD_SIZE)), (new Random().nextInt(FIELD_SIZE)));
                    ship.head = point;
                    if (ship.isHorizontal) {
                        if ((point.x + ship.deck - 1) > 9) {
                            ship.head.x -= point.x + ship.deck - 1 - 9;
                        }

                        ship.tail.x = ship.head.x + ship.deck - 1;
                        ship.tail.y = point.y;
                    } else {
                        if ((point.y + ship.deck - 1) > 9) {
                            ship.head.y -= point.y + ship.deck - 1 - 9;
                        }

                        ship.tail.x = point.x;
                        ship.tail.y = ship.head.y + ship.deck - 1;
                    }

                } while (!check(ship));
                setShip(ship);
                ships.add(ship);
                count++;
            }

        }
        System.out.println("Создано кораблей: " + count);
    }


    private boolean check(Ship ship) {
        Coeffs coeff = new Coeffs();
        defineCoeff(ship, coeff);

        if (ship.isHorizontal) {
            for (int i = coeff.headY; i < coeff.headY + 3 + coeff.coeffVer; i++) {
                for (int j = coeff.headX; j < coeff.headX + ship.deck + 2 + coeff.coeffHor; j++) {
                    if ((field.get(key[j][i])).state != Cell.State.NOT_SET) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = coeff.headY; i < coeff.headY + ship.deck + 2 + coeff.coeffVer; i++) {
                for (int j = coeff.headX; j < coeff.headX + 3 + coeff.coeffHor; j++) {
                    if ((field.get(key[j][i])).state != Cell.State.NOT_SET) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void defineCoeff(Ship ship, Coeffs coeff) {
        boolean common = false;
        if(ship.isHorizontal) {
            switch (0) {
                case 0:
                    if(ship.head.y == 0) {
                        coeff.headX = ship.head.x - 1;
                        coeff.headY = ship.head.y;
                        coeff.coeffVer = -1;
                        if(ship.tail.x == 9) {
                            coeff.coeffHor = -1;
                        } else {
                            coeff.coeffHor = 0;
                        }

                        common = true;
                    }
                case 1:
                    if(ship.head.x == 0) {
                        coeff.headX = ship.head.x;
                        coeff.headY = ship.head.y - 1;
                        coeff.coeffHor = -1;
                        if(ship.head.y == 9) {
                            coeff.coeffVer = -1;
                        } else {
                            coeff.coeffVer = 0;
                        }

                        common = true;
                    }
                case 2:
                    if((ship.head.y == 9 && ship.head.x != 0) || (ship.tail.x == 9 && ship.head.y != 0)) {
                        coeff.headX = ship.head.x - 1;
                        coeff.headY = ship.head.y - 1;
                        if(ship.head.y == 9) {
                            coeff.coeffHor = 0;
                            coeff.coeffVer = -1;
                        }

                        if(ship.tail.x == 9) {
                            coeff.coeffHor = -1;
                            coeff.coeffVer = 0;
                        }

                        if(ship.tail.x == 9 && ship.head.y == 9) {
                            coeff.coeffHor = -1;
                            coeff.coeffVer = -1;
                        }

                        common = true;
                    }
                case 3:
                    if(ship.head.x == 0 && ship.head.y == 0) {
                        coeff.headX = ship.head.x;
                        coeff.headY = ship.head.y;
                        coeff.coeffVer = -1;
                        coeff.coeffHor = -1;

                        common = true;
                    }
                case 4:
                    if (!common) {
                        coeff.headX = ship.head.x - 1;
                        coeff.headY = ship.head.y - 1;
                        coeff.coeffHor = 0;
                        coeff.coeffVer = 0;
                    }
            }
        } else {
            switch (0) {
                case 0:
                    if(ship.head.y == 0) {
                        coeff.headX = ship.head.x - 1;
                        coeff.headY = ship.head.y;
                        coeff.coeffVer = -1;
                        if(ship.tail.x == 9) {
                            coeff.coeffHor = -1;
                        } else {
                            coeff.coeffHor = 0;
                        }

                        common = true;
                    }
                case 1:
                    if(ship.head.x == 0) {
                        coeff.headX = ship.head.x;
                        coeff.headY = ship.head.y - 1;
                        coeff.coeffHor = -1;
                        if(ship.tail.y == 9) {
                            coeff.coeffVer = -1;
                        } else {
                            coeff.coeffVer = 0;
                        }

                        common = true;
                    }
                case 2:
                    if((ship.head.x == 9 && ship.head.y != 0) || (ship.tail.y == 9 && ship.head.x != 0)) {
                        coeff.headX = ship.head.x - 1;
                        coeff.headY = ship.head.y - 1;
                        if(ship.tail.y == 9) {
                            coeff.coeffHor = 0;
                            coeff.coeffVer = -1;
                        }

                        if(ship.tail.x == 9) {
                            coeff.coeffHor = -1;
                            coeff.coeffVer = 0;
                        }

                        if(ship.tail.x == 9 && ship.tail.y == 9) {
                            coeff.coeffHor = -1;
                            coeff.coeffVer = -1;
                        }

                        common = true;
                    }
                case 3:
                    if(ship.head.x == 0 && ship.head.y == 0) {
                        coeff.headX = ship.head.x;
                        coeff.headY = ship.head.y;
                        coeff.coeffVer = -1;
                        coeff.coeffHor = -1;

                        common = true;
                    }
                case 4:
                    if (!common) {
                        coeff.headX = ship.head.x - 1;
                        coeff.headY = ship.head.y - 1;
                        coeff.coeffHor = 0;
                        coeff.coeffVer = 0;
                    }
            }
        }

    }

    public void setShip(Ship ship) {
        if (ship.isHorizontal) {
            for (int i = ship.head.x; i < ship.tail.x + 1; i++) {
                (field.get(key[i][ship.head.y])).state = Cell.State.SHIPPOINT;
            }
        } else {
            for (int i = ship.head.y; i < ship.tail.y + 1; i++) {
                (field.get(key[ship.head.x][i])).state = Cell.State.SHIPPOINT;
            }
        }
    }

    public static void Winner() {
        System.out.println("Победил игрок под номером - " + ((new Random()).nextInt(2) + 1));
    }

}
