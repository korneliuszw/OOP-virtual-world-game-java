package ui.Board;

import game.OrganismDAO;
import game.World;
import game.board.IBoardSupplier;
import game.organism.OrganismBase;
import game.organism.animals.Player;
import ui.Board.Cell.CellBase;
import ui.Board.Cell.CellClickSpawn;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public abstract class BoardPaneBase extends JPanel {
    protected OrganismDAO organismsDAO;
    protected int width;
    protected int height;
    protected IBoardSupplier boardSupplier;
    private Player player;

    private Void setPlayer(Player player) {
        this.player = player;
        return null;
    }


    public BoardPaneBase(World world, int width, int height) {
        this.organismsDAO = world.getOrganisms();
        this.player = world.getPlayer();
        this.height = height;
        this.width = width;
        setLayout(null);
    }

    protected OrganismBase getOrganismAt(Point point) {
        Point realPoint = translatePoint(point);
        return organismsDAO.getEntityAt(realPoint);
    }

    protected abstract CellBase createCell(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn);

    public void changeWorld(World world) {
        this.width = world.getWidth();
        this.height = world.getHeight();
        this.organismsDAO = world.getOrganisms();
        this.player = world.getPlayer();
    }

    protected abstract List<Point> getAllPoints();

    protected Point translatePoint(Point point) {
        return point;
    }

    HashMap<Point, Integer> getPlayerNeighbours() {
        HashMap<Point, Integer> neighbours = new HashMap<>();
        for (int i = 0; i < boardSupplier.getNeighbours(); i++) {
            Point neighbour = boardSupplier.getNewPosition(player.getPosition(), i);
            neighbours.put(neighbour, i);
        }
        return neighbours;
    }

    protected void draw() {
        CellClickSpawn cellClickSpawn = new CellClickSpawn(organismsDAO, this::redraw, this::setPlayer);
        HashMap<Point, Integer> playerNeighbours = getPlayerNeighbours();
        for (Point point : getAllPoints()) {
            OrganismBase organism = getOrganismAt(point);
            CellBase cell = createCell(point, organism, cellClickSpawn);
            if (player.isWaitingForInput()) {
                // add text to movable cells informing which button to press
                Integer neighbour = playerNeighbours.get(translatePoint(point));
                if (neighbour != null) {
                    cell.add(new JLabel(String.valueOf(neighbour)));
                    cell.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
            add(cell);
        }
    }

    public void redraw() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Redrawing");
            removeAll();
            draw();
            revalidate();
            repaint();
        });
    }

}
