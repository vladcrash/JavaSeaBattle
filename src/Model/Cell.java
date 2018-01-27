package Model;


public class Cell {
    public enum State {MISS, SHIPPOINT, SHOOT, NOT_SET}
    State state;

    public Cell() {
        state = State.NOT_SET;
    }
}
