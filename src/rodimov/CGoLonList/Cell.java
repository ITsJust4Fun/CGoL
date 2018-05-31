package rodimov.CGoLonList;

public class Cell {
    int x;
    int age = 0;

    Cell(int x) {
        this.x = x;
    }

    void addAge() {
        age++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return x == cell.x;
    }

    @Override
    public int hashCode() {
        return x;
    }

    public int compareTo(Cell cell){
        int dif = this.x - cell.x;
        if (dif > 0){
            return 1;
        } else if (dif < 0){
            return -1;
        } else {
            return 0;
        }
    }
}
