public class Cell {
    private boolean alive;

    public Cell() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String toString() {
        if (alive)
            return "X";
        else
            return "_";
    }
}
