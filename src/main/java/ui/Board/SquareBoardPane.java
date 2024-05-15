package ui.Board;

import game.World;
import game.organism.OrganismBase;
import ui.Board.Cell.CellBase;
import ui.Board.Cell.SquareCell;

import java.awt.*;

public class SquareBoardPane extends BoardPaneBase {
    public SquareBoardPane(World world, int width, int height) {
        super(world, width, height);
        setPreferredSize(new Dimension(width * SquareCell.CELL_SIZE, height * SquareCell.CELL_SIZE));
    }

    @Override
    protected OrganismBase getOrganismAt(Point point) {
        return organismsDAO.getEntityAt(point);
    }

    @Override
    protected CellBase createCell(Point position, OrganismBase organism) {
        return new SquareCell(position, organism);
    }
}
