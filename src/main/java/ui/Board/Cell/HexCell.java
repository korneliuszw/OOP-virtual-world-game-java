package ui.Board.Cell;

import game.organism.OrganismBase;

import java.awt.*;

public class HexCell extends CellBase {
    public static final int CELL_SIZE = 32;

    public HexCell(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn, int size) {

        super(position, organism, cellClickSpawn);
        int offsetX = size * CELL_SIZE;
        int rowStartX = offsetX - (position.y > size ? 2 * size - position.y : position.y) * CELL_SIZE;
        int startX = rowStartX + position.x * 2 * CELL_SIZE;
        int startY = position.y * CELL_SIZE / 2;
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBounds(startX, startY, CELL_SIZE, CELL_SIZE);
    }

}
