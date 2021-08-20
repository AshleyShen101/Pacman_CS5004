package PacManCommon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardHandler implements KeyListener, GameInput {

    private int direction;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int KeyCode = e.getKeyCode();
        if (KeyCode == KeyEvent.VK_SPACE) {
            // start the game
            direction = 0;
            // up, down, left, right
        } else if (KeyCode == KeyEvent.VK_UP) {
            direction = 1;
        } else if (KeyCode == KeyEvent.VK_DOWN) {
            direction = 2;
        } else if (KeyCode == KeyEvent.VK_LEFT) {
            direction = 3;
        } else if (KeyCode == KeyEvent.VK_RIGHT) {
            direction = 4;
        }
    }

    public int getDirection() {
        return direction;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public String getInput() {
        return Integer.toString(direction);
    }

    @Override
    public boolean isKeyBoard() {
        return true;
    }
}
