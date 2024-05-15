package game.board;

import java.awt.*;

public class SquareBoard implements IBoardSupplier {

    final int width;
    final int height;

    public SquareBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Point getNewPosition(Point current, int move) {
        if (move == 0) {
            return new Point(current.x - 1, current.y);
        } else if (move == 1) {
            return new Point(current.x + 1, current.y);
        } else if (move == 2) {
            return new Point(current.x, current.y - 1);
        } else if (move == 3) {
            return new Point(current.x, current.y + 1);
        }
        return null;
    }

    @Override
    public int getNeighbours() {
        return 4;
    }

    @Override
    public boolean isLegalPosition(Point position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }
}
