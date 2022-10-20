package ladder.step2.domain;

public class Position {
    private final int position;
    
    public Position(final int position) {
        this.position = position;
    }
    
    public int moveLeft() {
        return position - 1;
    }
    
    public int moveRight() {
        return position + 1;
    }
    
    public int pass() {
        return position;
    }
    
    public Position createNext() {
        return new Position(position + 1);
    }
    
    @Override
    public String toString() {
        return "Position{" +
                "position=" + position +
                '}';
    }
}
