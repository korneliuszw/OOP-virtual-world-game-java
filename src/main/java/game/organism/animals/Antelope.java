package game.organism.animals;

import game.World;
import game.organism.OrganismBase;

import java.awt.*;

public class Antelope extends Animal implements Cloneable {
    public Antelope(Point position) {
        super(4, 4, position);
    }

    @Override
    public String getSymbol() {
        return "A";
    }

    @Override
    public Antelope clone() {
        return new Antelope(getPosition());
    }

    @Override
    public void act(World world) throws CloneNotSupportedException {
        // this is hacky but we need to keep original position to revert in case of push backs
        // so we need to save it, set pos to position + 1
        // then get second position because it moves by two
        Point currentPosition = (Point) getPosition().clone();
        setPosition(generateRandomLegalPosition(world, false));
        Point newPosition = generateRandomLegalPosition(world, false);
        setPosition(currentPosition);
        moveThisOrganism(world, newPosition);
    }

    @Override
    protected boolean collide(World world, OrganismBase other) throws CloneNotSupportedException {
        int rng = (int) Math.round(Math.random());
        if (rng == 1) {
            Point pos = generateRandomLegalPosition(world, true);
            if (pos != getPosition()) {
                // TODO: log?
                moveThisOrganism(world, pos);
                return true;
            }
        }
        return super.collide(world, other);
    }
}
