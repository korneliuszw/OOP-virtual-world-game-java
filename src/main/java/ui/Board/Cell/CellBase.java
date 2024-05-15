package ui.Board.Cell;

import game.organism.OrganismBase;

import javax.swing.*;
import java.awt.*;

public class CellBase extends JLabel {
    public static final int CELL_SIZE = 32;
    private final Point center;

    public CellBase(Point position, OrganismBase organism) {
        this.center = new Point((position.x + CELL_SIZE) / 2, (position.y + 32) / 2);
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        this.setBounds(position.x * CELL_SIZE, position.y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setText(organism.getSymbol());
    }
}
