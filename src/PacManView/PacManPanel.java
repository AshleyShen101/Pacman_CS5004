package PacManView;

import PacManCommon.KeyBoardHandler;
import PacManModel.PacManBasicModel;
import PacManModel.PacmanModelInterface;
import PacManCommon.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PacManPanel extends JPanel {

    private boolean inGame;
    private Coordinate pacmanLoc;
//    private boolean winGame;
    private List<Coordinate> ghostLoc;
    private final int blockSize = 24;
    private final int blockNums = 15;
    private final int screenSize = blockSize * blockNums;
    private Image down, left, right, up;
    private Image heart, pacman, ghost;
    private final Font smallFont = new Font("Arial", Font.BOLD, 14);
    private PacmanModelInterface pm = new PacManBasicModel();

    public PacManPanel() {
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
        g2d.fillRect(0, 0, 400, 400);
        drawMaze(g2d);
        drawScore(g2d);
        if (!inGame) {
            showIntroScreen(g2d);
        } else if (inGame) {
            drawPacman(g2d);
            drawGhost(g2d, ghostLoc);
        }
    }

    public void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to start";
        g2d.setColor(new Color(51, 255, 255, 255));
        g2d.drawString(start, 90, 150);
    }

    public void drawScore(Graphics2D g2d) {
        g2d.setFont(smallFont);
        g2d.setColor(new Color(51, 255, 255, 255));
        String s = "Score: " + pm.getScore();
        g2d.drawString(s, 276, 376);
        for (int i = 0; i < pm.getLive(); i++) {
            g2d.drawImage(heart, i * 28 + 8, 361, this);
        }
    }

    public void drawMaze(Graphics2D g2d) {
        int[][] Maze = pm.getMaze();
        int x = 0;
        int y = 0;
        for (int i = 0; i < screenSize; i+=blockSize) {
            for (int j = 0; j < screenSize; j+=blockSize) {
                g2d.setColor(new Color(251, 0, 180, 255));
                //g2d.setStroke(new BasicStroke(5));
                if (Maze[x][y] == 0) g2d.fillRect(i, j, 24, 24);
                if (Maze[x][y] == 2) {
                    g2d.setColor(new Color(58, 51,255, 255));
                    g2d.fillOval(i + 10, j + 10, 6,6);
                }
                x++;
            }
            x = 0;
            y++;
        }
    }

    public void drawPacman(Graphics2D g2d) {
        KeyBoardHandler kb = new KeyBoardHandler();
        if (kb.getDirection() == 1) {
            g2d.drawImage(up, pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        } else if (kb.getDirection() == 2) {
            g2d.drawImage(down, pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        } else if (kb.getDirection() == 3) {
            g2d.drawImage(left,pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        } else if (kb.getDirection() == 4) {
            g2d.drawImage(right, pacmanLoc.getX() + 1, pacmanLoc.getY() + 1, this);
        }
    }

    public void drawGhost(Graphics2D g2d, List<Coordinate> GhostLoc) {
        for (Coordinate c : GhostLoc) {
            int x = c.getX();
            int y = c.getY();
            g2d.drawImage(ghost, x, y, this);
        }
    }

//    public void endGameScreen(Graphics2D g2d, boolean winGame, boolean inGame) {
//        if (winGame && inGame) {
//            String win = "You won!!";
//            g2d.setColor(new Color(51, 255, 255, 255));
//            g2d.drawString(win, 90, 150);
//        } else if (!winGame && !inGame) {
//            String lose = "You lost.";
//            g2d.setColor(new Color(51, 255, 255, 255));
//            g2d.drawString(lose, 90, 150);
//        }
//    }
}
