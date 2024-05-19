package game.organism.plants;

import game.World;
import game.organism.OrganismBase;
import game.organism.animals.Animal;

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
    protected boolean collide(World world, OrganismBase collider) throws CloneNotSupportedException {
        collider.kill();
        kill();
        // TODO?: Log
        return true;
    }

    @Override
    public void act(World world) throws CloneNotSupportedException {
        for (int i = 0; i < world.getBoardSupplier().getNeighbours(); i++) {
            Point neighbour = world.getBoardSupplier().getNewPosition(getPosition(), i);
            OrganismBase organism = world.getOrganisms().getEntityAt(neighbour);
            if (organism instanceof Animal) {
                organism.kill();
            }
        }
    }
}
