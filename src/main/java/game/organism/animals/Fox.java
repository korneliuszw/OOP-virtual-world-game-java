package game.organism.animals;

import game.World;
import game.organism.OrganismBase;

import java.awt.*;

public class Fox extends Animal implements Cloneable {

    public Fox(Point position) {
        super(9, 5, position);
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
