package xyz.kawuka.game.organism;

import xyz.kawuka.game.Logger;
import xyz.kawuka.game.World;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class OrganismBase implements Comparable<OrganismBase>, Serializable {
    public static final int NON_MOVABLE_ORGANISM = -1;
    private boolean alive = true;
    private Point position;
    private final int aggressiveness;
    private int age = 0;

    private int attack;

    private int id = 0;

    protected OrganismBase(int attack, int aggressiveness, Point position) {
        this.attack = attack;
        this.aggressiveness = aggressiveness;
        this.position = position;
    }

    @Override
    public int compareTo(@NotNull OrganismBase o2) {
        OrganismBase o1 = this;
        if (o1.equals(o2)) {
            return 0;
        } else if (o1.aggressiveness == NON_MOVABLE_ORGANISM && o2.aggressiveness != NON_MOVABLE_ORGANISM) {
            return 1;
        } else if (o2.aggressiveness == NON_MOVABLE_ORGANISM && o1.aggressiveness != NON_MOVABLE_ORGANISM) {
            return -1;
        } else if (o1.aggressiveness == o2.aggressiveness)
            return o1.age < o2.age ? 1 : -1;
        return o1.aggressiveness < o2.aggressiveness ? 1 : -1;
    }

    public void kill() {
        this.alive = false;
    }

    protected void moveThisOrganism(World world, Point newPoint) throws CloneNotSupportedException {
        if (!world.getBoardSupplier().isLegalPosition(newPoint)) {
            return;
        }
        OrganismBase colidee = world.getOrganisms().getEntityAt(newPoint);
        Point oldPosition = (Point) this.position.clone();
        this.position = newPoint;
        world.getOrganisms().moveOrganism(this, oldPosition);
        if (colidee != null && !colidee.equals(this)) {
            if (!colidee.collide(world, this)) {
                // both organisms are alive so we need to move them back
                Point newPosition = (Point) this.position.clone();
                this.position = oldPosition;
                world.getOrganisms().moveOrganism(this, newPosition);
            }
        }
        if (this.alive) {
            Logger.getInstance().logOrganismAction(this, String.format("moved to (%d, %d)", newPoint.x, newPoint.y), null);
        }
    }


    protected boolean collide(World world, OrganismBase collider) throws CloneNotSupportedException {
        Logger.getInstance().logOrganismAction(this, "collided with", collider);
        if (collider.attack > this.attack)
            this.kill();
        else
            collider.kill();
        return true;
    }

    protected boolean canMoveThere(World world, Point position, boolean skipOccupied) {
        return !skipOccupied || world.getOrganisms().getEntityAt(position) == null;
    }

    protected Point generateRandomLegalPosition(World world, boolean skipOccupied) {
        ArrayList<Point> legalPositions = new ArrayList<>();
        for (int i = 0; i < world.getBoardSupplier().getNeighbours(); i++) {
            Point newPosition = world.getBoardSupplier().getNewPosition(this.position, i);
            if (world.getBoardSupplier().isLegalPosition(newPosition) && canMoveThere(world, newPosition, skipOccupied)) {
                legalPositions.add(newPosition);
            }
        }
        if (legalPositions.isEmpty()) {
            return position;
        }
        int random = (int) Math.round(Math.random() * (legalPositions.size() - 1));
        return legalPositions.get(random);
    }

    public void endTurn() {
        age++;
    }

    public abstract void act(World world) throws CloneNotSupportedException;


    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }


    public Point getPosition() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getAge() {
        return age;
    }

    public int getAttack() {
        return attack;
    }

    public int getId() {
        return id;
    }


    public abstract String getSymbol();


}
