package xyz.kawuka.game.organism.animals;

import xyz.kawuka.game.Logger;
import xyz.kawuka.game.World;
import xyz.kawuka.game.organism.OrganismBase;

import java.awt.*;

public abstract class Animal extends OrganismBase {

    static final int MINIMUM_AGE_TO_MATE = 5;

    boolean didMate = false;

    protected Animal(int attack, int aggressiveness, Point position) {
        super(attack, aggressiveness, position);
    }

    private void mate(World world, Animal other) throws CloneNotSupportedException {
        Point position = generateRandomLegalPosition(world, true);
        if (position == getPosition())
            return;
        Animal child = (Animal) other.clone();
        child.setAge(0);
        child.setPosition(position);
        Logger.getInstance().logOrganismAction(this, "mated with", other);
        world.getOrganisms().spawnOrganism(child);
        didMate = true;
    }

    @Override
    public void act(World world) throws CloneNotSupportedException {
        moveThisOrganism(world, generateRandomLegalPosition(world, false));
    }


    @Override
    public void endTurn() {
        didMate = false;
        super.endTurn();
    }

    @Override
    protected boolean collide(World world, OrganismBase other) throws CloneNotSupportedException {
        if (!other.getClass().equals(getClass()))
            return super.collide(world, other);
        if (other.getAge() >= MINIMUM_AGE_TO_MATE && getAge() >= MINIMUM_AGE_TO_MATE)
            mate(world, (Animal) other);
        return true;
    }
}
