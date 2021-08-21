package PacManView;

import PacManCommon.Coordinate;
import PacManCommon.GameInput;
import PacManModel.PacmanModelInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.List;

public class PacManView implements PacManViewInterface {

    private JFrame frame;
    private PacManPanel panel;

    public PacManView(GameInput kb, PacmanModelInterface model) {
        frame = new JFrame();
        panel = new PacManPanel(model);
        panel.setPreferredSize(new Dimension(720, 760));
        if (kb.isKeyBoard()) {
            panel.addKeyListener((KeyListener) kb);
        }
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    @Override
    public void paint(boolean inGame, Coordinate PacmanLoc, List<Coordinate> ghostLoc) {
        panel.paint(inGame, PacmanLoc, ghostLoc);
    }
}
