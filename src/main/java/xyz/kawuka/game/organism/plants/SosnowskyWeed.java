package xyz.kawuka.game.organism.plants;

import xyz.kawuka.game.Logger;
import xyz.kawuka.game.World;
import xyz.kawuka.game.organism.OrganismBase;
import xyz.kawuka.game.organism.animals.Animal;

import java.awt.*;

public class SosnowskyWeed extends Plant implements Cloneable {

    public SosnowskyWeed(Point position) {
        super(position);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public SosnowskyWeed clone() {
        return new SosnowskyWeed(getPosition());
    }

    @Override
    protected boolean collide(World world, OrganismBase collider) {
        collider.kill();
        kill();
        Logger.getInstance().logOrganismAction(collider, "ate and died", this);
        return true;
    }

    @Override
    public void act(World world) {
        for (int i = 0; i < world.getBoardSupplier().getNeighbours(); i++) {
            Point neighbour = world.getBoardSupplier().getNewPosition(getPosition(), i);
            OrganismBase organism = world.getOrganisms().getEntityAt(neighbour);
            if (organism instanceof Animal) {
                organism.kill();
                Logger.getInstance().logOrganismAction(this, "killed", organism);
            }
        }
    }
}
