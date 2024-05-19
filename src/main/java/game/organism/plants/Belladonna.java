package game.organism.plants;

import game.World;
import game.organism.OrganismBase;

import java.awt.*;

public class Belladonna extends Plant implements Cloneable {

    public Belladonna(Point position) {
        super(position);
    }

    @Override
    public String getSymbol() {
        return "J";
    }

    @Override
    public Belladonna clone() {
        return new Belladonna(getPosition());
    }

    @Override
    protected boolean collide(World world, OrganismBase collider) throws CloneNotSupportedException {
        if (collider instanceof Plant)
            return true;
        collider.kill();
        kill();
        // TODO?: Log
        return true;
    }
}
