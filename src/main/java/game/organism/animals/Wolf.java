package game.organism.animals;

import java.awt.*;

public class Wolf extends Animal implements Cloneable {
    public Wolf(Point position) {
        super(9, 5, position);
    }

    @Override
    public String getSymbol() {
        return "W";
    }

    @Override
    public Wolf clone() {
        return new Wolf(getPosition());
    }
}
