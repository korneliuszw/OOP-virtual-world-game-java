package xyz.kawuka.game.organism.plants;

import xyz.kawuka.game.Logger;
import xyz.kawuka.game.World;
import xyz.kawuka.game.organism.OrganismBase;

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
    protected boolean collide(World world, OrganismBase collider) {
        if (collider instanceof Plant)
            return true;
        collider.kill();
        kill();
        Logger.getInstance().logOrganismAction(collider, "eat and died", this);
        return true;
    }
}
