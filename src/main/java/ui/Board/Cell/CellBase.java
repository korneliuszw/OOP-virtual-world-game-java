package ui.Board.Cell;

import game.organism.OrganismBase;
import game.organism.animals.Animal;
import game.organism.animals.Player;
import game.organism.plants.Plant;
import ui.Board.BoardPaneBase;

import javax.swing.*;
import java.awt.*;

public class CellBase extends JLabel {
    private final Point gamePosition;

    public CellBase(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn) {
        this.gamePosition = position;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (organism instanceof Player) {
            setForeground(((Player) organism).isWaitingForInput() ? Color.RED : Color.BLUE);
        }
        if (organism instanceof Plant) {
            setBackground(Color.GREEN);
        }
        if (organism instanceof Animal) {
            setBackground(Color.YELLOW);
        }
        if (organism != null) {
            setText(organism.getSymbol());
        } else {
            addMouseListener(cellClickSpawn);
        }
    }

    public Point getGamePosition() {
        return gamePosition;
    }
}
