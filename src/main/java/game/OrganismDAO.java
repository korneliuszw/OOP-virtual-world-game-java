package game;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import game.organism.OrganismBase;

import java.awt.*;
import java.util.*;
import java.util.List;

public class OrganismDAO {
    private HashMap<Point, List<OrganismBase>> mapper = new HashMap<>();
    private HashMap<String, Integer> organismCounter = new HashMap<>();

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
        Point position = organism.getPosition();
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
}
