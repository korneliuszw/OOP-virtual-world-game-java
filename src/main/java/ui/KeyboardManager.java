package ui;

import game.World;
import ui.Board.BoardPaneBase;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CompletableFuture;

public class KeyboardManager implements KeyListener {
    private World world;
    CompletableFuture<Void> gameTurn;

    public KeyboardManager(World world, BoardPaneBase boardPane) {
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        new Thread(() -> {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_E:
                    world.getPlayer().activateAbility();
                    break;
                case KeyEvent.VK_W:
                case KeyEvent.VK_UP:
                    world.getPlayer().move(new Point(0, -1));
                    break;
                case KeyEvent.VK_S:
                case KeyEvent.VK_DOWN:
                    world.getPlayer().move(new Point(0, 1));
                    break;
                case KeyEvent.VK_A:
                case KeyEvent.VK_LEFT:
                    world.getPlayer().move(new Point(-1, 0));
                    break;
                case KeyEvent.VK_D:
                case KeyEvent.VK_RIGHT:
                    world.getPlayer().move(new Point(1, 0));
                    break;
                case KeyEvent.VK_ENTER:
                    if (gameTurn != null && !gameTurn.isDone()) return;
                    gameTurn = new CompletableFuture<>();
                    try {
                        world.turn();
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    } finally {
                        gameTurn.complete(null);
                    }
                    break;
            }
        }).start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setWorld(World world) {
        this.world = world;
    }

}
