package rodimov.CGoLonSet;

public class Cell {
    int x;
    int y;
    int age = 0;
    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    void addAge(){
        age++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        return y == cell.y;
    }

    @Override
    public int hashCode() {
        return 31 * y + x;
    }
}
