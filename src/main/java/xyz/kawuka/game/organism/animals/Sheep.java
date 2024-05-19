package xyz.kawuka.game.organism.animals;

import java.awt.*;

public class Sheep extends Animal implements Cloneable {
    public Sheep(Point position) {
        super(4, 4, position);
    }

    @Override
    public String getSymbol() {
        return "O";
    }

    @Override
    public Sheep clone() {
        return new Sheep(getPosition());
    }
}
