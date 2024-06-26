package xyz.kawuka.game;

import xyz.kawuka.game.organism.OrganismBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

public class OrganismDAO implements Serializable {
    private final HashMap<Point, List<OrganismBase>> mapper = new HashMap<>();
    private final HashMap<String, Integer> organismCounter = new HashMap<>();

    private void insertOrganism(@NotNull OrganismBase organism) {
        Point position = organism.getPosition();
        if (mapper.containsKey(position)) {
            mapper.get(position).add(organism);
        } else {
            mapper.put(position, new LinkedList<>(Collections.singletonList(organism)));
        }
    }

    public void spawnOrganism(@NotNull OrganismBase organism) {
        String name = organism.getSymbol();
        int id = 0;
        if (organismCounter.containsKey(name)) {
            id = organismCounter.get(name);
        }
        organismCounter.put(name, id + 1);
        organism.setId(id);
        insertOrganism(organism);
    }

    @Nullable
    public OrganismBase getEntityAt(@NotNull Point position) {
        List<OrganismBase> list = mapper.get(position);
        if (list != null) {
            return list.stream().filter(OrganismBase::isAlive).findFirst().orElse(null);
        }
        return null;
    }

    public void moveOrganism(@NotNull OrganismBase organism, @NotNull Point oldPosition) {
        List<OrganismBase> list = mapper.get(oldPosition);
        if (list == null) return;
        if (list.size() == 1) {
            mapper.remove(oldPosition);
        } else {
            list.remove(organism);
        }
        insertOrganism(organism);
    }

    Iterator<List<OrganismBase>> getAllOrganisms() {
        return mapper.values().stream().iterator();
    }

    public HashMap<Point, List<OrganismBase>> getMapper() {
        return mapper;
    }

}
