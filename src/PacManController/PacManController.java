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
        view = new PacManView(gameInput, model); // Challenge 1 solution
        new Timer(110, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = gameInput.getInput();
        String[] tokens = input.split(" ");
        direction = Integer.parseInt(tokens[0]);
        if (!model.inGame() && direction == -1) model.startGame(direction);
        if(model.inGame()){
            model.run(direction);
            model.ghostMove();
            view.paint(model.inGame(), model.getPacManLocation(), model.getGhostLocation());
        }

    }

    public static void main(String[] args) {
        new PacManController();
    }
}
