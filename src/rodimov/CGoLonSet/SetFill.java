package rodimov.CGoLonSet;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Set;
import java.nio.file.*;
import java.io.IOException;

public class SetFill{
    Set<Cell> universe;
    int x = 0;
    int y = 0;

    SetFill(Set<Cell> universe){
        this.universe = universe;
    }

    public Set<Cell> fillRandom() {
        Random rnd = new Random();
        int height = 400;
        int width = 400;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (rnd.nextDouble() < 0.15) {
                    universe.add(new Cell(j, i));
                }
            }
        }
        return universe;
    }

    public Set<Cell> fillGlider(){
        universe.add(new Cell(1,0));
        universe.add(new Cell(2,1));
        for (int i = 0; i < 3; i++) {
            universe.add(new Cell(i,2));
        }
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
                    isNextY = (c == '$');
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
                            universe.add(new Cell(x, y));
                            x++;
                        } else {
                            for (int j = 0; j < Integer.valueOf(num); j++) {
                                universe.add(new Cell(x, y));
                                x++;
                            }
                            num = "";
                        }
                    } else if (isNextY) {
                        x = 0;
                        y++;
                    }
                }
            }
        }
    }

    public Set<Cell> fillCaterPillar() throws IOException{
        Files.lines(Paths.get("caterpillar.rle"),
                StandardCharsets.UTF_8).forEach(e -> checkLine(e));
        return universe;
    }
}
