package rodimov;

import rodimov.CGoLonArray.CGoLonArray;
import rodimov.CGoLonList.CGoLonList;
import rodimov.CGoLonSet.CGoLonSet;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartGame {
    char q;
    CGoL g;
    GUI gui = new GUI();
    public void StartGUI(){
        gui.setVisible(true);
        gui.bSG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Dimension p = gui.panelMM.getSize();
                if (gui.isOnSet()) {
                    g = new CGoLonSet((int)p.getWidth(), ((int)p.getHeight() - 30));
                } else if (gui.isOnList()) {
                    g = new CGoLonList((int)p.getWidth(), ((int)p.getHeight() - 30));
                } else {
                    g = new CGoLonArray((int)p.getWidth(), ((int)p.getHeight() - 30));
                }
                if (gui.isRnd()) {
                    q = 'r';
                } else if (gui.isCP()) {
                    q = 'c';
                } else {
                    q = 'g';
                }
                gui.dispose();
            }
        });
    }

    public boolean GUIisVisible(){
        return gui.isVisible();
    }

    public void StartGame(){
        try {
            g.game(q);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
