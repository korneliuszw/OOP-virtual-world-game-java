package game.organism.animals;

import java.awt.*;

public class Fox extends Animal implements Cloneable {

    public Fox(Point position) {
        super(9, 5, position);
    }

    @Override
    public String getSymbol() {
        return "W";
    }

    @Override
    public Fox clone() {
        return new Fox(getPosition());
    }
}
