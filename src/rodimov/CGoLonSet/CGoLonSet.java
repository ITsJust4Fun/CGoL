package rodimov.CGoLonSet;

import rodimov.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CGoLonSet implements CGoL {
    int resw;
    int resh;

    public CGoLonSet(int WIDTH, int HEIGTH){
        resh = HEIGTH;
        resw = WIDTH;
    }

    public void game(char q) throws IOException{
        Set<Cell> universe = new HashSet<>();
        SetStdDraw d = new SetStdDraw(resw, resh, q);
        SetNewGen n = new SetNewGen();
        SetFill f = new SetFill(universe);
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
            d.winStdDraw(universe);
            d.fpsStdDraw(tStart);
        }
    }
}
