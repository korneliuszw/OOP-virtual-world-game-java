package xyz.kawuka.game.organism.animals;

import xyz.kawuka.game.World;
import xyz.kawuka.game.organism.OrganismBase;

import java.awt.*;

public class Fox extends Animal implements Cloneable {

    public Fox(Point position) {
        super(3, 7, position);
    }

    @Override
    public String getSymbol() {
        return "L";
    }

    @Override
    public Fox clone() {
        return new Fox(getPosition());
    }

    @Override
    protected boolean canMoveThere(World world, Point position, boolean skipOccupied) {
        OrganismBase organism = world.getOrganisms().getEntityAt(position);
        if (organism != null && organism.getAge() > getAge())
            return false;
        return super.canMoveThere(world, position, skipOccupied);
    }
}
