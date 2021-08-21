package PacManCommon;

public class Coordinate {

    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {

            Coordinate other = (Coordinate) obj;
            if (other.x == this.x && other.y == this.y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

}
