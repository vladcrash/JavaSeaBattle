package Model;


public class Ship {

    Point head;
    Point tail;
    boolean isHorizontal;
    int deck;

    public Ship() {

    }

    public Ship(int deck, boolean isHorizontal) {
        this.head = new Point();
        this.tail = new Point();
        this.isHorizontal = isHorizontal;
        this.deck = deck;
    }


    private boolean isHorizontal() {
        return head.y == tail.y;
    }

    public void shipInfo() {
        System.out.printf("head(x, y): (%d, %d), tail(x, y): (%d, %d), orientationHor: %b, decks: %d\n", head.x, head.y, tail.x, tail.y, isHorizontal, deck);
    }
}
