package PacManModel;

import PacManCommon.Coordinate;

import java.util.List;

public interface PacmanModelInterface {
    void initGame();
    void startGame(int direction);
    Coordinate getPacManLocation();
    List<Coordinate> getGhostLocation();
    int getScore();
    int getLive();
    int[][] getMaze();
    boolean inGame();
    void run(int direction);
    void ghostMove();
    int getPacDirection();
}
