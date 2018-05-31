package rodimov.CGoLonList;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;
import java.nio.file.*;
import java.io.IOException;

public class ListFill{
    List<List<Cell>> universe;
    List<Cell> a = new ArrayList<>();
    int x = 0;
    int y = 0;

    ListFill(List<List<Cell>> universe){
        this.universe = universe;
    }

    public List<List<Cell>> fillRandom() {
        Random rnd = new Random();
        int height = 300;
        int width = 400;
        for (int i = 0; i < height; i++) {
            List<Cell> a = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                if (rnd.nextDouble() < 0.15) {
                    a.add(new Cell(j));
                }
            }
            universe.add(a);
        }
        return universe;
    }

    public List<List<Cell>> fillGlider(){
        List<Cell> a = new ArrayList<>();
        a.add(new Cell(1));
        universe.add(a);
        a = new ArrayList<>();
        a.add(new Cell(2));
        universe.add(a);
        a = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            a.add(new Cell(i));
        }
        universe.add(a);
        return universe;
    }

    void checkLine(String line){
        Integer b;
        String num = "";
        boolean isNum;
        boolean isAlive;
        boolean isDead;
        boolean isNextY;
        if (line.length() != 0) {
            if ((line.charAt(0) != '#') && (line.charAt(0) != 'x')) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    isNum = Character.isDigit(c);
                    isAlive = (c == 'o');
                    isDead = (c == 'b');
                    isNextY = (c == '$' || c == '!');
                    if (isNum) {
                        num += c;
                    } else if (isDead) {
                        if ("".equals(String.valueOf(num))) {
                            x++;
                        } else {
                            b = Integer.valueOf(num);
                            x += b;
                            num = "";
                        }
                    } else if (isAlive) {
                        if ("".equals(String.valueOf(num))) {
                            a.add(new Cell(x));
                            x++;
                        } else {
                            for (int j = 0; j < Integer.valueOf(num); j++) {
                                a.add(new Cell(x));
                                x++;
                            }
                            num = "";
                        }
                    } else if (isNextY) {
                        x = 0;
                        y++;
                        universe.add(a);
                        a = new ArrayList<>();
                    }
                }
            }
        }
    }

    public List<List<Cell>> fillCaterPillar() throws IOException{
        Files.lines(Paths.get("caterpillar.rle"),
                StandardCharsets.UTF_8).forEach(e -> checkLine(e));
        return universe;
    }
}