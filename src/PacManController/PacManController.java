package PacManController;

import PacManCommon.GameInput;
import PacManCommon.KeyBoardHandler;
import PacManModel.PacManBasicModel;
import PacManModel.PacmanModelInterface;
import PacManView.PacManView;
import PacManView.PacManViewInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacManController implements ActionListener {

    private PacmanModelInterface model;
    private PacManViewInterface view;
    private GameInput gameInput;
    private int direction;

    public PacManController() {
        gameInput = new KeyBoardHandler();
        model = new PacManBasicModel();
        view = new PacManView(gameInput);
        new Timer(200, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.startGame(direction);
        model.run(direction);
    }

    public static void main(String[] args) {
        new PacManController();
    }
}
