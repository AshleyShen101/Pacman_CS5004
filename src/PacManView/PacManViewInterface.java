package PacManView;

import PacManCommon.Coordinate;

import java.util.List;

public interface PacManViewInterface {
    void paint(boolean inGame,
               Coordinate PacmanLoc,
               List<Coordinate> ghostLoc);
}
