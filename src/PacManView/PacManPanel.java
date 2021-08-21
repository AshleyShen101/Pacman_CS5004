package PacManView;

import PacManModel.PacmanModelInterface;
import PacManCommon.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PacManPanel extends JPanel {

    private boolean inGame;
    private Coordinate pacmanLoc;
    private List<Coordinate> ghostLoc;
    private final int blockSize = 24;
    private final int blockNums = 30;
    private final int screenSize = blockSize * blockNums;
    private Image down, left, right, up;
    private Image heart, pacman, ghost;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private PacmanModelInterface pm;
    // private PacmanModelInterface pm = new PacManBasicModel();

    public PacManPanel(PacmanModelInterface model ) { // Challenge 1 solution
        pm = model; // Challenge 1 solution
        this.setFocusable(true);
        try {
            down = new ImageIcon("src/images/down.gif").getImage();
            up = new ImageIcon("src/images/up.gif").getImage();
            left = new ImageIcon("src/images/left.gif").getImage();
            right = new ImageIcon("src/images/right.gif").getImage();
            ghost = new ImageIcon("src/images/ghost.gif").getImage();
            heart = new ImageIcon("src/images/heart.png").getImage();
            pacman = new ImageIcon("src/images/pacman.png").getImage();
        } catch (Exception e) {
            System.out.println("The picture is unavailable");
        }
    }

    public void paint(boolean inGame, Coordinate pacmanLocation, List<Coordinate> ghostLocation) {
        this.inGame = inGame;
        this.pacmanLoc = pacmanLocation;
        this.ghostLoc = ghostLocation;
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 800, 800);
        drawMap(g2d);
        Score(g2d);
        if (!pm.inGame()) {
            startMenu(g2d);
        } else if (pm.inGame()) {
            pacmanImage(g2d);
            ghostImage(g2d, ghostLoc);
        }
    }

    public void startMenu(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(new Color(51, 255, 255, 255));
        g2d.drawString(start, 280, 380);
    }

    public void Score(Graphics2D g2d) {
        g2d.setFont(smallFont);
        g2d.setColor(new Color(51, 255, 255, 255));
        String s = "Score: " + pm.getScore();
        g2d.drawString(s, 650, 740);
        for (int i = 0; i < pm.getLive(); i++) {
            g2d.drawImage(heart, i * 28 + 8, 722, this); //
        }
    }

    public void drawMap(Graphics2D g2d) {
        int[][] Maze = pm.getMaze();
        int x = 0;
        int y = 0;
        for (int i = 0; i < screenSize; i+=blockSize) {
            for (int j = 0; j < screenSize; j+=blockSize) {
                g2d.setColor(new Color(251, 0, 180, 255));
                if (Maze[x][y] == 0) g2d.fillRect(i, j, 24, 24);
                if (Maze[x][y] == 2) {
                    g2d.setColor(new Color(58, 51,255, 255));
                    g2d.fillOval(i + 10, j + 10, 6,6);
                }
                y++;
            }
            y = 0;
            x++;
        }
    }

    public void pacmanImage(Graphics2D g2d) {
//        KeyBoardHandler kb = new KeyBoardHandler(); old version mistake
        // if(kb.getDirection == 1){ ... }
        if (pm.getPacDirection() == 1) { //challenge 2 solution
            g2d.drawImage(up, pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        } else if (pm.getPacDirection() == 2) {
            g2d.drawImage(down, pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        } else if (pm.getPacDirection() == 3) {
            g2d.drawImage(left,pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        } else if (pm.getPacDirection() == 4) {
            g2d.drawImage(right, pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        } else{
            g2d.drawImage(pacman, pacmanLoc.getX(), pacmanLoc.getY(), this);
        }
    }

    public void ghostImage(Graphics2D g2d, List<Coordinate> GhostLoc) {
        for (Coordinate c : GhostLoc) {
            int x = c.getX();
            int y = c.getY();
            g2d.drawImage(ghost, x, y, this);
        }
    }
}
