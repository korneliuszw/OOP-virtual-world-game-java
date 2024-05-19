package ui.board;

import game.World;
import game.board.HexBoard;
import game.organism.OrganismBase;
import ui.board.cell.CellBase;
import ui.board.cell.CellClickSpawn;
import ui.board.cell.HexCell;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class HexBoardPane extends BoardPaneBase {
    public HexBoardPane(World world, int width, int height) {
        super(world, width, height);
        boardSupplier = new HexBoard(width, height);
        setPreferredSize(new Dimension(2 * width * HexCell.CELL_SIZE, (height + 1) * HexCell.CELL_SIZE));
    }

    @Override
    protected Point translatePoint(Point point) {
        Point realPoint;
        if (point.y >= height)
            realPoint = new Point(point.x, point.x + point.y - height + 1);
        else
            realPoint = new Point(height - point.y + point.x - 1, point.x);
        return realPoint;
    }


    @Override
    protected CellBase createCell(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn) {
        return new HexCell(position, organism, cellClickSpawn, height - 1);
    }

    @Override
    protected List<Point> getAllPoints() {
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j <= i; j++) {
                Point point = new Point(j, i);
                points.add(point);
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width - i - 1; j++) {
                Point point = new Point(j, i + width);
                points.add(point);
            }
        }
        return points;
    }
}