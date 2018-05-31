package rodimov.CGoLonArray;

import edu.princeton.cs.introcs.StdDraw;
import rodimov.CGoL;

import java.awt.Color;
import java.util.Random;

public class CGoLonArray implements CGoL {
    int resh;
    int resw;
    public CGoLonArray(int WIDTH, int HEIGTH){
        resh = HEIGTH;
        resw = WIDTH;
    }

    public void game(char q){
        double xc = (resw - 1) / 2;
        double yc = (resh - 1) / 2;
        double zoom = 1;
        byte[][] a = new byte[resh][resw];
        byte[][] b = new byte[resh][resw];
        byte[][] c1;
        byte[][] c = new byte[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int h;
        int w;
        int s = 0;
        Random rnd = new Random();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (rnd.nextDouble() < 0.15) {
                    a[i][j] = 1;
                }
            }
        }
        StdDraw.setCanvasSize(resw, resh);
        StdDraw.setXscale(0, (resw - 1));
        StdDraw.setYscale(0, (resh - 1));
        StdDraw.enableDoubleBuffering();
        while (true){
            long tStart = System.currentTimeMillis();
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[i].length; j++) {
                    for (int t = 0; t < 8; t++) {
                        h = i + c[t][0];
                        w = j + c[t][1];
                        if ((h >= 0) && (w >= 0) && (h < a.length) && (w < a[i].length)) {
                            s += a[h][w];
                        }
                    }
                    if (s == 3) {
                        b[i][j] = 1;
                    } else if ((a[i][j] == 1) && (s == 2)) {
                        b[i][j] = 1;
                    } else {
                        b[i][j] = 0;
                    }
                    s = 0;
                }
            }
            c1 = a;
            a = b;
            b = c1;
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
            for(int i = 0;i<b.length;i++){
                for(int j = 0;j<b[i].length;j++){
                    if(b[i][j] == 1) {
                        StdDraw.filledCircle( ((resw - 1) / 2) + (j - xc) * zoom,
                                ((resh - 1) / 2) + (((resh - 1) - i) - yc) * zoom,
                                0.5 * zoom);
                    }
                }
            }
            StdDraw.setPenColor(Color.BLACK);
            long tFrame = System.currentTimeMillis() - tStart;
            String time = "frame: " + tFrame + "ms";
            String fps = "fps: " + Math.round(1000.0 / tFrame);
            StdDraw.textLeft(20, 20, time);
            StdDraw.textLeft(20, 40, fps);
            StdDraw.show();
        }
    }
}
