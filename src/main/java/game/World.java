package game;

import game.board.IBoardSupplier;
import game.organism.OrganismBase;
import ui.board.BoardPaneBase;
import game.organism.animals.Player;
import ui.board.BoardPaneHolder;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class World implements Serializable {
    private final int width;
    private final int height;
    private final PriorityQueue<OrganismBase> organismActionQueue;
    private final OrganismDAO organisms = new OrganismDAO();
    private final IBoardSupplier boardSupplier;
    private Player player;
    private transient BoardPaneHolder boardPane;

    private static final long serialVersionUID = 1L;


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

    public OrganismDAO getOrganisms() {
        return organisms;
    }

    private void actTurn() throws CloneNotSupportedException {
        while (!organismActionQueue.isEmpty()) {
            OrganismBase organism = organismActionQueue.poll();
            if (organism instanceof Player) {
                player = (Player) organism;
            }
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
        boardPane.getBoardPane().redraw();
    }

    public IBoardSupplier getBoardSupplier() {
        return boardSupplier;
    }

    public Player getPlayer() {
        return player;
    }

    public BoardPaneBase getBoardPane() {
        return boardPane.getBoardPane();
    }

    public void setBoardPane(BoardPaneHolder boardPane) {
        this.boardPane = boardPane;
    }


}
