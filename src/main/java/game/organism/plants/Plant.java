package game.organism.plants;

import game.World;
import game.organism.OrganismBase;

import java.awt.*;

public abstract class Plant extends OrganismBase {
    // 12.5% chance to spawn
    protected int spawnRateUpperBound = 8;
    static final int MINIMUM_SPAWN_AGE = 5;

    protected Plant(Point position) {
        super(0, 0, position);
    }

    @Override
    public void act(World world) throws CloneNotSupportedException {
        int rng = (int) Math.round(Math.random() * spawnRateUpperBound);
        if (getAge() < MINIMUM_SPAWN_AGE || rng != 1)
            return;
        Point position = generateRandomLegalPosition(world, true);
        if (position == getPosition())
            return;
        Plant child = (Plant) clone();
        child.setAge(0);
        child.setPosition(position);
        // TODO: Log?
        world.getOrganisms().spawnOrganism(child);
    }
}
