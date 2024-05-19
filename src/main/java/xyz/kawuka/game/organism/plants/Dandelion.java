package xyz.kawuka.game.organism.plants;

import xyz.kawuka.game.World;

import java.awt.*;

public class Dandelion extends Plant implements Cloneable {

    public Dandelion(Point position) {
        super(position);
    }

    @Override
    public String getSymbol() {
        return "M";
    }

    @Override
    public Dandelion clone() {
        return new Dandelion(getPosition());
    }

    @Override
    // 3x more likely to spawn
    public void act(World world) throws CloneNotSupportedException {
        super.act(world);
        super.act(world);
        super.act(world);
    }
}
