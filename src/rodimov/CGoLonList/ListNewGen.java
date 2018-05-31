package rodimov.CGoLonList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListNewGen {
    private static int fList = 0;

    private boolean SearchCell(List<Cell> list, int x) {
        if ((list != null) && (list.size() != 0)) {
            int low = 0;
            int high = list.size();
            int mid;
            while(low < high) {
                mid = (low + high) / 2;
                if (list.get(mid).x == x) {
                    return true;
                } else if (x < list.get(mid).x) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private int calcNeighbours(List<Cell> prevList, List<Cell> corrList,
                       List<Cell> nextList, int x) {
        int neighbours = 0;
        int a;
        for (int j = -1; j < 2; j++){
            a = x + j;
            if (SearchCell(prevList, a)){
                neighbours++;
            }
            if (SearchCell(corrList, a) && (j != 0)) {
                neighbours ++;
            }
            if (SearchCell(nextList, a)) {
                neighbours++;
            }
        }
        return neighbours;
    }

    private List<Cell> calcList(List<Cell> prevList, List<Cell> corrList,
                        List<Cell> nextList) {
        List<Cell> list = new ArrayList<>();
        if (corrList != null) {
            for (Cell cell : corrList) {
                int neighbours = calcNeighbours(prevList, corrList, nextList, cell.x);
                if ((neighbours == 2 || neighbours == 3)) {
                    list.add(cell);
                }
            }
        }
        if (prevList != null) {
            for (Cell cell : prevList) {
                int a;
                for (int j = -1; j < 2; j++) {
                    a = cell.x + j;
                    if (!SearchCell(list, a)) {
                        int neighbours = calcNeighbours(prevList, corrList, nextList, a);
                        if (neighbours == 3) {
                            Cell n = new Cell(a);
                            list.add(n);
                            Collections.sort(list, (t, b) -> t.compareTo(b));
                        }
                    }
                }
            }
        }
        if (corrList != null) {
            for (Cell cell : corrList) {
                int a;
                for (int j = -1; j < 2; j++) {
                    a = cell.x + j;
                    if (!SearchCell(list, a)) {
                        int neighbours = calcNeighbours(prevList, corrList, nextList, a);
                        if (neighbours == 3) {
                            Cell n = new Cell(a);
                            list.add(n);
                            Collections.sort(list, (t, b) -> t.compareTo(b));
                        }
                    }
                }
            }
        }
        if (nextList != null) {
            for (Cell cell : nextList) {
                int a;
                for (int j = -1; j < 2; j++) {
                    a = cell.x + j;
                    if (!SearchCell(list, a)) {
                        int neighbours = calcNeighbours(prevList, corrList, nextList, a);
                        if (neighbours == 3) {
                            Cell n = new Cell(a);
                            list.add(n);
                            Collections.sort(list, (t, b) -> t.compareTo(b));
                        }
                    }
                }
            }
        }
        return list;
    }

    List<List<Cell>> calcNewGen(List<List<Cell>> universe) {
        List<List<Cell>> universe2 = new ArrayList<>();
        List<Cell> prevList;
        List<Cell> corrList;
        List<Cell> nextList;
        fList--;
        for (int i = -1; i < universe.size() + 1; i++) {
            List<Cell> list;
            if ((i - 1) < 0){
                prevList = null;
            } else {
                prevList = universe.get(i - 1);
            }
            if ((i + 1) >= universe.size()){
                nextList = null;
            } else {
                nextList = universe.get(i + 1);
            }
            if ((i < 0) || (i >= universe.size())) {
                corrList = null;
            } else {
                corrList = universe.get(i);
            }
            list = calcList(prevList, corrList, nextList);
            universe2.add(list);
        }
        if (universe2.size() > 0) {
            while (universe2.get(0).size() == 0) {
                universe2.remove(0);
                fList++;
                if (universe2.size() == 0) {
                    break;
                }
            }
        }
        if (universe2.size() > 0) {
            while (universe2.get(universe2.size() - 1).size() == 0) {
                universe2.remove(universe2.size() - 1);
                if (universe2.size() == 0) {
                    break;
                }
            }
        }
        return universe2;
    }

    public int getfList(){
        return fList;
    }
}