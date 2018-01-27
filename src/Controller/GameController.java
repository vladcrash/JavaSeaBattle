package Controller;

import Model.Point;
import Model.User;

public class GameController {
    public static void doShoot(Point point) {
        User.point = point;
        User.isShoot = true;
    }
}
