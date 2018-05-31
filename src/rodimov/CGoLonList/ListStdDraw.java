package rodimov.CGoLonList;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.util.List;

public class ListStdDraw {
    double zoom = 1;
    int resw;
    int resh;
    double x;
    double y;
    double xc;
    double yc;

    ListStdDraw(int WIDTH, int HEIGTH, char q){
        resw = WIDTH;
        resh = HEIGTH;
        xc = (resw - 1) / 2;
        yc = (resh - 1) / 2;
        if (q == 'c') {
            zoom = 0.01;
            yc -= 250 / zoom;
        }
    }

    public void canvasStdDraw(){
        StdDraw.setCanvasSize(resw, resh);
        StdDraw.setXscale(0, (resw - 1));
        StdDraw.setYscale(0, (resh - 1));
        StdDraw.enableDoubleBuffering();
    }

    public void winStdDraw(List<List<Cell>> universe, int fList){
        StdDraw.clear(Color.WHITE);
        if(StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            if (key == '+') {
                zoom *= 1.3;
            }
            if (key == '-') {
                zoom /= 1.3;
            }
            if (key == 'w') {
                yc += 20 / zoom;
            }
            if (key == 's') {
                yc -= 20 / zoom;
            }
            if (key == 'd') {
                xc += 20 / zoom;
            }
            if (key == 'a') {
                xc -= 20 / zoom;
            }
        }
        for (int i = 0; i < universe.size(); i++){
            for (Cell cell : universe.get(i)) {
                x = ((resw - 1) / 2) + (cell.x - xc) * zoom;
                y = ((resh - 1) / 2) + (((resh - 1) - (i + fList)) - yc) * zoom;
                if ((Math.abs(x) <= resw) || (Math.abs(y) <= resh)) {
                    if (cell.age <= 1) {
                        StdDraw.setPenColor(Color.BLACK);
                    } else if (cell.age <= 10) {
                        StdDraw.setPenColor(Color.MAGENTA);
                    } else if (cell.age <= 25) {
                        StdDraw.setPenColor(Color.BLUE);
                    } else if (cell.age <= 50) {
                        StdDraw.setPenColor(Color.GREEN);
                    } else if (cell.age <= 75) {
                        StdDraw.setPenColor(Color.YELLOW);
                    } else {
                        StdDraw.setPenColor(Color.RED);
                    }
                    StdDraw.filledCircle(x, y, 0.5 * zoom);
                }
                cell.addAge();
            }
        }
    }

    public void fpsStdDraw(long tStart){
        StdDraw.setPenColor(Color.BLACK);
        long tFrame = System.currentTimeMillis() - tStart;
        String time = "frame: " + tFrame + "ms";
        String fps = "fps: " + Math.round(1000.0 / tFrame);
        StdDraw.textLeft(20, 20, time);
        StdDraw.textLeft(20, 40, fps);
        StdDraw.show();
    }

}