package ui.Board;

import game.OrganismDAO;
import game.World;
import game.organism.OrganismBase;
import ui.Board.Cell.CellBase;
import ui.Board.Cell.SquareCell;

import javax.swing.*;
import java.awt.*;

public abstract class BoardPaneBase extends JPanel {
    protected final OrganismDAO organismsDAO;
    final int width;
    final int height;

    public BoardPaneBase(World world, int width, int height) {
        this.organismsDAO = world.getOrganisms();
        this.height = height;
        this.width = width;
        setLayout(null);
    }

    protected abstract OrganismBase getOrganismAt(Point point);

    protected abstract CellBase createCell(Point position, OrganismBase organism);

    protected void draw() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point point = new Point(i, j);
                OrganismBase organism = getOrganismAt(point);
                if (organism == null) {
                    continue;
                }
                CellBase cell = createCell(new Point(i, j), organism);
                add(cell);
            }
        }
    }

    public void redraw() {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Redrawing");
            System.out.println(Thread.currentThread());
            removeAll();
            draw();
            revalidate();
            repaint();
        });
    }

}
