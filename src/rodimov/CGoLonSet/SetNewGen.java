package rodimov.CGoLonSet;

import java.util.HashSet;
import java.util.Set;

public class SetNewGen {
    private Set<Cell> universe2;

    private int calcNeighbours(Set<Cell> universe, Cell cell, boolean flag){
        int neighbours = 0;
        for (int i = -1; i < 2; i++){
            for (int j = -1; j < 2; j++){
                if ((i != 0) || (j != 0)){
                    int cellnx = cell.x + j;
                    int cellny = cell.y + i;
                    Cell a = new Cell(cellnx, cellny);
                    if (universe.contains(a)){
                        neighbours++;
                    } else if (flag) {
                        int neighbuors2 = calcNeighbours(universe, a, false);
                        if (neighbuors2 == 3){
                            universe2.add(a);
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    public Set<Cell> calcNewGen(Set<Cell> universe){
        universe2 = new HashSet<>();
        int neighbours;
        for (Cell cell : universe) {
            neighbours = calcNeighbours(universe, cell, true);
            if ((neighbours == 2) || (neighbours == 3)) {
                universe2.add(cell);
            }
        }
        return universe2;
    }
}
