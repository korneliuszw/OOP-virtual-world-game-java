import game.World;
import game.WorldFactory;
import game.board.SquareBoard;
import game.organism.animals.*;
import game.organism.plants.*;
import ui.AbilityStatus;
import ui.Board.BoardPaneBase;
import ui.Board.SquareBoardPane;
import ui.KeyboardManager;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Cannot set system look and feel, defaulting to cross-platform look and feel");
        }
//        int width = DimenionsModal.readDimension("width");
//        int height = DimenionsModal.readDimension("height");
        int width = 20;
        int height = 20;
        AbilityStatus abilityStatus = new AbilityStatus();
        World world = WorldFactory.createWorld(width, height, abilityStatus);
        BoardPaneBase boardPane = new SquareBoardPane(world, width, height);
        KeyboardManager keyboardManager = new KeyboardManager(world, boardPane);
        MainFrame mainFrame = new MainFrame(boardPane, keyboardManager, abilityStatus, world);
        abilityStatus.update(AbilityStatus.Status.READY);
        world.setBoardPane(boardPane);
    }
}
