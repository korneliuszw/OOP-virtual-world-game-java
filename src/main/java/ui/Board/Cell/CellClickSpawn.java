package ui.Board.Cell;

import game.OrganismDAO;
import game.organism.OrganismBase;
import game.organism.animals.*;
import game.organism.plants.*;
import ui.AbilityStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class CellClickSpawn extends MouseAdapter {
    private final OrganismDAO organismsDAO;
    private final Runnable redrawFunction;

    public CellClickSpawn(OrganismDAO organismsDAO, Runnable redrawFunction) {
        this.organismsDAO = organismsDAO;
        this.redrawFunction = redrawFunction;
    }

    boolean hasPlayer() {
        for (List<OrganismBase> organismBaseList : organismsDAO.getMapper().values()) {
            for (OrganismBase organism : organismBaseList) {
                if (organism instanceof Player) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        HashMap<String, Class<? extends OrganismBase>> organismNames = new HashMap<>();
        if (!hasPlayer())
            organismNames.put("Player", Player.class);
        organismNames.put("Fox", Fox.class);
        organismNames.put("Sheep", Sheep.class);
        organismNames.put("Turtle", Turtle.class);
        organismNames.put("Wolf", Wolf.class);
        organismNames.put("Antelope", Antelope.class);
        organismNames.put("Grass", Grass.class);
        organismNames.put("Dandelion", Dandelion.class);
        organismNames.put("Guarana", Guarana.class);
        organismNames.put("Sosnowsky Hogweed", SosnowskyWeed.class);
        organismNames.put("Belladonna", Belladonna.class);

        String[] options = organismNames.keySet().toArray(new String[0]);

        int selectedOption = JOptionPane.showOptionDialog(null, "Choose organism to spawn", "Spawn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

        if (selectedOption == -1) {
            return;
        }

        String selectedOrganism = options[selectedOption];
        Class<? extends OrganismBase> organismClass = organismNames.get(selectedOrganism);
        try {
            Point gamePositon = ((CellBase) e.getSource()).getGamePosition();
            if (organismClass == Player.class) {
                organismsDAO.spawnOrganism(new Player(gamePositon, AbilityStatus.getInstance()));
            } else {
                OrganismBase organism = organismClass.getDeclaredConstructor(Point.class).newInstance(gamePositon);
                organismsDAO.spawnOrganism(organism);
            }
            redrawFunction.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
