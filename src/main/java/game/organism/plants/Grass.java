package game.organism.plants;

import java.awt.*;

public class Grass extends Plant implements Cloneable {

    public Grass(Point position) {
        super(position);
    }

    @Override
    public String getSymbol() {
        return "T";
    }

    @Override
    public Grass clone() {
        return new Grass(getPosition());
    }
}
