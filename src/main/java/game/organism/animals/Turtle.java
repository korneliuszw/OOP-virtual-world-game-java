package game.organism.animals;

import game.World;
import game.organism.OrganismBase;

import java.awt.*;

public class Turtle extends Animal implements Cloneable {
    public Turtle(Point position) {
        super(2, 1, position);
    }

    @Override
    public String getSymbol() {
        return "T";
    }

    @Override
    public Turtle clone() {
        return new Turtle(getPosition());
    }

    @Override
    protected boolean collide(World world, OrganismBase other) throws CloneNotSupportedException {
        if (other.getAttack() < 5)
            return false;
        return super.collide(world, other);
    }

    @Override
    // 25% to move
    public void act(World world) throws CloneNotSupportedException {
        int rng = (int) Math.round(Math.random() * 5);
        if (rng == 1) {
            super.act(world);
        }
    }
}
