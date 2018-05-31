package rodimov.CGoLonList;

import rodimov.CGoL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CGoLonList implements CGoL {
    int resw;
    int resh;

    public CGoLonList(int WIDTH, int HEIGTH){
        resh = HEIGTH;
        resw = WIDTH;
    }

    public void game(char q) throws IOException{
        List<List<Cell>> universe = new ArrayList<>();
        ListStdDraw d = new ListStdDraw(resw, resh, q);
        ListNewGen n = new ListNewGen();
        ListFill f = new ListFill(universe);
        d.canvasStdDraw();
        if (q == 'r'){
            universe = f.fillRandom();
        } else if(q == 'c'){
            universe = f.fillCaterPillar();
        } else {
            universe = f.fillGlider();
        }
        while (true){
            long tStart  = System.currentTimeMillis();
            universe = n.calcNewGen(universe);
            d.winStdDraw(universe, n.getfList());
            d.fpsStdDraw(tStart);
        }
    }
}
