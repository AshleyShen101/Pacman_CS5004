package PacManModel;

import PacManCommon.Coordinate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PacManBasicModel implements PacmanModelInterface{

    private boolean inGame;
    private int live;
    private int score;
    private final int dot_size = 24;
    private List<Coordinate> ghostLoc;
    private Coordinate pacmanLoc;
    private Random rnd;
    private int[][] maze;
    private int pacDirection; // challenge 2 solution

    public PacManBasicModel() {
        this.initGame();
    }

    @Override
    public void initGame() {
        maze = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 0},
                {0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

        };
        inGame = false;
        live = 3;
        score = 0;
        rnd = new Random();
        ghostLoc = new ArrayList<>();
        int ghost_num = 12;
        for (int i = 0; i < ghost_num; i++) {
            int x = 0;
            int y = 0;
            while(maze[x / 24][y / 24] == 0 && x / 24 != 338 && y / 24 != 338){
                x = ((rnd.nextInt(29) + 1) * dot_size) + 2;
                y = ((rnd.nextInt(29) + 1) * dot_size) + 2;
            }
            ghostLoc.add(new Coordinate(x, y));
        }
        pacmanLoc = new Coordinate(338, 338);
        pacDirection = 0;

    }

    @Override
    public void startGame(int direction) {
        initGame();
        if (direction == -1) {
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
    public int getPacDirection() {return pacDirection;}

    @Override
    public void run(int direction) {
        int old_x = pacmanLoc.x;
        int old_y = pacmanLoc.y;
        int new_x = old_x;
        int new_y = old_y;
        pacDirection = direction;
        // Change direction: up, down, left, right
        if (direction == 1) {
            new_y -= dot_size;
        }
        else if (direction == 2) {
            new_y += dot_size;
        }
        else if (direction == 3){
            new_x -= dot_size;
        }
        else if (direction == 4){
            new_x += dot_size;
        }

        if (maze[new_x/24][new_y/24] != 0) {
            pacmanLoc.x = new_x;
            pacmanLoc.y = new_y;
        }

        // Pacman eating dots
        if (maze[pacmanLoc.x / 24][pacmanLoc.y / 24] == 2) {
            maze[pacmanLoc.x / 24][pacmanLoc.y / 24] = 1;
            score += 1;
        }

        // Pacman hit ghost
        for(Coordinate g : ghostLoc){
            System.out.println("Pac: " + pacmanLoc.getX() + " " + pacmanLoc.getY());
            System.out.println("GHo: " + g.getX() + " " + g.getY());
            if(pacmanLoc.getX() == g.getX() && pacmanLoc.getY() == g.getY()) {
                live--;
                pacmanLoc.x = 338;
                pacmanLoc.y = 338;
                if (live == 0) {
                    inGame = false;
                }
            }
        }
        // Win the game
        isWinGame();
    }

    @Override
    public void ghostMove(){
        int[] directions = new int[ghostLoc.size()];
        for(int i = 0; i < directions.length; i++){
            directions[i] = rnd.nextInt(4) + 1;
            int new_x = ghostLoc.get(i).getX();
            int new_y = ghostLoc.get(i).getY();
            if (directions[i] == 1) {
                new_y -= dot_size;
            }
            else if (directions[i] == 2) {
                new_y += dot_size;
            }
            else if (directions[i] == 3){
                new_x -= dot_size;
            }
            else if (directions[i] == 4){
                new_x += dot_size;
            }

            if (maze[new_x/24][new_y/24] != 0) {
                ghostLoc.get(i).x = new_x;
                ghostLoc.get(i).y = new_y;
            }

        }

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
    }

}
