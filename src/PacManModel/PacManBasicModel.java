package PacManModel;

import PacManCommon.Coordinate;
import PacManCommon.GameInput;
import PacManCommon.KeyBoardHandler;
import PacManView.PacManView;
import PacManView.PacManViewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PacManBasicModel implements PacmanModelInterface{

    private boolean inGame;
    private boolean winGame;
    private int ghost_num = 6;
    private int live;
    private int score;
    private int dot_size = 24;
    private List<Coordinate> ghostLoc;
    private Coordinate pacmanLoc;
    private Random rnd;
    private int[][] maze;

    public PacManBasicModel() {
        this.initGame();
    }

    @Override
    public void initGame() {
        inGame = false;
        winGame = false;
        live = 3;
        score = 0;
        rnd = new Random();
        ghostLoc = new ArrayList<>();
        for (int i = 0; i < ghost_num; i++) {
            ghostLoc.add(new Coordinate(rnd.nextInt(400), rnd.nextInt(400)));
        }
        pacmanLoc = new Coordinate(50, 50);
        maze = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    @Override
    public void startGame(int direction) {
        initGame();
        if (direction == 0) {
            inGame = true;
        }
    }

    @Override
    public Coordinate getPacManLocation() {
        return pacmanLoc;
    }

    @Override
    public List<Coordinate> getGhostLocation() {
        return ghostLoc;
    }

    @Override
    public int getScore() { return score; }

    @Override
    public int getLive() { return live; }

    @Override
    public int[][] getMaze() { return maze; }

    @Override
    public boolean inGame() {
        return inGame;
    }

    @Override
    public void run(int direction) {
        int old_x = pacmanLoc.x;
        int old_y = pacmanLoc.y;
        int new_x = old_x;
        int new_y = old_y;

        // Change direction: up, down, left, right
        if (direction == 1) new_y -= dot_size;
        else if (direction == 2) new_y += dot_size;
        else if (direction == 3) new_x -= dot_size;
        else if (direction == 4) new_x += dot_size;
        pacmanLoc.x = new_x;
        pacmanLoc.y = new_y;

        // Pacman eating dots
        if (maze[pacmanLoc.x % 24][pacmanLoc.y % 24] == 2) {
            maze[pacmanLoc.x % 24][pacmanLoc.y % 24] = 1;
            score += 1;
        }

        // Pacman hit walls and standstill
        if (maze[pacmanLoc.x % 24 + 1][pacmanLoc.y % 24] == 0 || maze[pacmanLoc.x % 24 - 1][pacmanLoc.y % 24] == 0
                || maze[pacmanLoc.x % 24][pacmanLoc.y % 24 + 1] == 0 || maze[pacmanLoc.x % 24][pacmanLoc.y % 24 - 1] == 0) {
            pacmanLoc.x = old_x;
            pacmanLoc.y = old_y;
        }

        // Pacman hit ghost
        if (pacmanLoc.equals(ghostLoc)) {
            live--;
            if (live == 0) {
                inGame = false;
                winGame = false;
            }
        }

        // Win the game
        isWinGame();
    }

    private void isWinGame() {
        boolean dotsRemain = false;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 2 && live != 0) {
                    dotsRemain = true;
                    break;
                }
            }
            if (dotsRemain) {
                break;
            }
        }
        if (!dotsRemain) winGame = true;
    }

}
