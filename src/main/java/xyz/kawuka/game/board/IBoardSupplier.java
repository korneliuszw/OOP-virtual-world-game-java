package xyz.kawuka.game.board;

import java.awt.*;

public interface IBoardSupplier {
    Point getNewPosition(Point current, int move);

    int getNeighbours();

    boolean isLegalPosition(Point position);
}
