package ui.Board.Cell;

import game.organism.OrganismBase;

import java.awt.*;

public class SquareCell extends CellBase {
    public SquareCell(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn) {
        super(position, organism, cellClickSpawn);
    }
}
