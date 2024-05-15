package ui;

import game.World;
import ui.Board.BoardPaneBase;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
    private final World world;
    private final BoardPaneBase boardPane;

    public KeyboardManager(World world, BoardPaneBase boardPane) {
        this.world = world;
        this.boardPane = boardPane;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(String.format("Key pressed %s", e.getKeyChar()));
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                world.turn(boardPane);
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
