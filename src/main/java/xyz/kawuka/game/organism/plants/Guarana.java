package xyz.kawuka.game.organism.plants;

import xyz.kawuka.game.Logger;
import xyz.kawuka.game.World;
import xyz.kawuka.game.organism.OrganismBase;

import java.awt.*;

public class Guarana extends Plant implements Cloneable {
    public Guarana(Point position) {
        super(position);
        spawnRateUpperBound = 10;
    }

    @Override
    public String getSymbol() {
        return "G";
    }

    @Override
    public Guarana clone() {
        return new Guarana(getPosition());
    }

    @Override
    protected boolean collide(World world, OrganismBase collider) {
        collider.setAttack(collider.getAttack() + 3);
        kill();
        Logger.getInstance().logOrganismAction(collider, "ate and gained straight", this);
        return true;
    }
}
