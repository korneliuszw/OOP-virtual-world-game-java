package game;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import game.board.IBoardSupplier;
import game.organism.OrganismBase;
import ui.Board.BoardPaneBase;

import java.awt.*;
import java.util.*;
import java.util.List;

public class World {
    private int width;
    private int height;
    private PriorityQueue<OrganismBase> organismActionQueue;
    private OrganismDAO organisms = new OrganismDAO();
    private boolean changed = true;
    private final IBoardSupplier boardSupplier;

    public World(int width, int height, IBoardSupplier boardSupplier) {
        this.width = width;
        this.height = height;
        this.boardSupplier = boardSupplier;
        this.organismActionQueue = new PriorityQueue<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    boolean isLegalPosition(Point position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    public OrganismDAO getOrganisms() {
        return organisms;
    }

    private void actTurn() throws CloneNotSupportedException {
        while (!organismActionQueue.isEmpty()) {
            OrganismBase organism = organismActionQueue.poll();
            if (organism.isAlive()) {
                organism.act(this);
            }
        }
    }

    private void endTurn() {
        for (Iterator<List<OrganismBase>> listIt = organisms.getAllOrganisms(); listIt.hasNext(); ) {
            List<OrganismBase> list = listIt.next();
            for (Iterator<OrganismBase> it = list.iterator(); it.hasNext(); ) {
                OrganismBase organism = it.next();
                if (organism.isAlive()) {
                    organism.endTurn();
                } else {
                    it.remove();
                }
            }
            if (list.isEmpty()) {
                listIt.remove();
            }
        }
    }

    public void turn(BoardPaneBase boardPaneBase) throws CloneNotSupportedException {
        for (Iterator<List<OrganismBase>> listIt = organisms.getAllOrganisms(); listIt.hasNext(); ) {
            List<OrganismBase> list = listIt.next();
            for (OrganismBase organism : list) {
                if (organism.isAlive()) {
                    organismActionQueue.add(organism);
                }
            }
        }
        actTurn();
        endTurn();
        boardPaneBase.redraw();
    }


    public void commit() {
        changed = false;
    }

    public IBoardSupplier getBoardSupplier() {
        return boardSupplier;
    }
}
