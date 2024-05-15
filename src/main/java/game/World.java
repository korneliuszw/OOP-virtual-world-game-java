package game;

import game.board.IBoardSupplier;
import game.organism.OrganismBase;
import ui.Board.BoardPaneBase;
import game.organism.animals.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

public class World {
    private int width;
    private int height;
    private final PriorityQueue<OrganismBase> organismActionQueue;
    private final OrganismDAO organisms = new OrganismDAO();
    private final IBoardSupplier boardSupplier;
    private final Player player;
    private BoardPaneBase boardPane;


    public World(int width, int height, IBoardSupplier boardSupplier, Player player) {
        this.width = width;
        this.height = height;
        this.boardSupplier = boardSupplier;
        this.organismActionQueue = new PriorityQueue<>();
        this.player = player;
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
        HashMap<Point, List<OrganismBase>> mapper = organisms.getMapper();
        List<Point> keys = new LinkedList<>(mapper.keySet());
        for (Point key : keys) {
            List<OrganismBase> list = mapper.get(key);
            list.removeIf(organism -> !organism.isAlive());
            list.forEach(OrganismBase::endTurn);
            if (list.isEmpty()) {
                mapper.remove(key);
            }
        }
    }

    public void turn() throws CloneNotSupportedException {
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
        boardPane.redraw();
    }


    public void commit() {
    }

    public IBoardSupplier getBoardSupplier() {
        return boardSupplier;
    }

    public Player getPlayer() {
        return player;
    }

    public BoardPaneBase getBoardPane() {
        return boardPane;
    }

    public void setBoardPane(BoardPaneBase boardPane) {
        this.boardPane = boardPane;
    }
}
