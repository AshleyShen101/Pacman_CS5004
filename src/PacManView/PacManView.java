package PacManView;

import PacManCommon.Coordinate;
import PacManCommon.GameInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

public class PacManView implements PacManViewInterface {

    private JFrame frame;
    private PacManPanel panel;

    public PacManView(GameInput kb) {
        frame = new JFrame();
        panel = new PacManPanel();
        panel.setPreferredSize(new Dimension(360, 400));
        if (kb.isKeyBoard()) {
            panel.addKeyListener((KeyListener) kb);
        }
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void paint(boolean inGame, Coordinate PacmanLoc, List<Coordinate> ghostLoc) {
        panel.paint(inGame, PacmanLoc, ghostLoc);
    }
}
