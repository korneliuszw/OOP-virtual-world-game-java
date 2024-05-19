package ui.Board;

import game.World;
import game.board.SquareBoard;
import game.organism.OrganismBase;
import ui.Board.Cell.CellBase;
import ui.Board.Cell.CellClickSpawn;
import ui.Board.Cell.SquareCell;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SquareBoardPane extends BoardPaneBase {
    public SquareBoardPane(World world, int width, int height) {
        super(world, width, height);
        boardSupplier = new SquareBoard(width, height);
        setPreferredSize(new Dimension(width * SquareCell.CELL_SIZE, height * SquareCell.CELL_SIZE));
    }

    @Override
    protected CellBase createCell(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn) {
        return new SquareCell(position, organism, cellClickSpawn);
    }

    @Override
    protected List<Point> getAllPoints() {
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Point point = new Point(i, j);
                points.add(point);
            }
        }
        return points;
    }


}
