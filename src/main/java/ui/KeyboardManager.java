package ui;

import game.World;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {
    private World world;
    private Thread gameThread;

    public KeyboardManager(World world) {
        this.world = world;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0; i < world.getBoardSupplier().getNeighbours(); i++) {
            if (e.getKeyChar() == '0' + i) {
                world.getPlayer().move(world, i);
                return;
            }
        }
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_E) {
            world.getPlayer().activateAbility();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            if (gameThread != null && gameThread.isAlive()) return;
            gameThread = new Thread(() -> {
                try {
                    world.turn();
                } catch (CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
            });
            gameThread.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void reset() {
        if (gameThread != null && gameThread.isAlive())
            gameThread.interrupt();
    }

}
