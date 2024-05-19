package xyz.kawuka.ui.board.cell;

import xyz.kawuka.game.organism.OrganismBase;

import java.awt.*;

public class SquareCell extends CellBase {
    public static final int CELL_SIZE = 32;

    public SquareCell(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn) {
        super(position, organism, cellClickSpawn);
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        this.setBounds(position.x * CELL_SIZE, position.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
}
