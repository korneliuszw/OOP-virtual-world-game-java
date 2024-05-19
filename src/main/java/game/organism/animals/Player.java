package game.organism.animals;

import game.World;
import game.organism.OrganismBase;
import ui.AbilityStatus;

import java.awt.*;
import java.io.Serializable;
import java.util.Optional;

public class Player extends Animal {
    private class Ability implements Serializable {
        int cooldownUntil = 0;
        int availableUntil = 0;
        final int DURATION = 5;

        public boolean isAvailable() {
            return availableUntil <= getAge() && cooldownUntil <= getAge();
        }

        public boolean isActivated() {
            return availableUntil > getAge();
        }

        public boolean activate() {
            if (!isAvailable())
                return false;
            // TODO: Log?
            availableUntil = getAge() + DURATION;
            return true;
        }

        public void update(World world) {
            if (!isActivated()) return;
            for (int i = 0; i < world.getBoardSupplier().getNeighbours(); i++) {
                Point neighbour = world.getBoardSupplier().getNewPosition(getPosition(), i);
                OrganismBase organism = world.getOrganisms().getEntityAt(neighbour);
                if (organism != null) {
                    organism.kill();
                    // TODO: Log?
                }
            }
        }

        public void updateTimers() {
            if (availableUntil != -0 && availableUntil <= getAge() && availableUntil > cooldownUntil) {
                // TODO: Log?
                cooldownUntil = availableUntil + DURATION;
            }
        }
    }

    boolean waitingForInput = false;
    private final Ability ability = new Ability();
    private transient AbilityStatus abilityStatus;
    private Point pendingMove = null;

    public Player(Point position, AbilityStatus abilityStatus) {
        super(5, 4, position);
        this.abilityStatus = abilityStatus;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public void showAbilityStatus() {
        abilityStatus.update(ability.isActivated() ? AbilityStatus.Status.IN_PROGRESS : ability.isAvailable()
                ? AbilityStatus.Status.READY : AbilityStatus.Status.COOLDOWN);
    }

    @Override
    public void act(World world) throws CloneNotSupportedException {
        ability.updateTimers();
        waitingForInput = true;
        world.getBoardPane().redraw();
        while (pendingMove == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Player move: " + pendingMove);
        waitingForInput = false;
        Point direction = pendingMove;
        Point newPosition = new Point(getPosition().x + direction.x, getPosition().y + direction.y);
        moveThisOrganism(world, newPosition);
        pendingMove = null;
        ability.update(world);
    }

    @Override
    protected boolean collide(World world, OrganismBase other) throws CloneNotSupportedException {
        return super.collide(world, other);
    }

    public void activateAbility() {
        if (ability.isAvailable()) {
            ability.activate();
            showAbilityStatus();
        }
    }

    public void move(Point direction) {
        pendingMove = direction;
    }

    public boolean isWaitingForInput() {
        return waitingForInput;
    }

    public void setAbilityStatus(AbilityStatus abilityStatus) {
        this.abilityStatus = abilityStatus;
    }

}
