package game.organism.animals;

import java.awt.*;

public class Fox extends Animal {

    public Fox(Point position) {
        super(9, 5, position);
    }

    @Override
    public String getSymbol() {
        return "W";
    }
}
