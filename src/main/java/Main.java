import game.World;
import game.board.SquareBoard;
import game.organism.animals.Fox;
import ui.Board.BoardPaneBase;
import ui.Board.SquareBoardPane;
import ui.DimenionsModal;
import ui.KeyboardManager;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, InterruptedException, CloneNotSupportedException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        int width = DimenionsModal.readDimension("width");
//        int height = DimenionsModal.readDimension("height");
        int width = 20;
        int height = 20;
        World world = new World(width, height, new SquareBoard(width, height));
        world.getOrganisms().spawnOrganism(new Fox(new Point(2, 2)));
        world.getOrganisms().spawnOrganism(new Fox(new Point(3, 4)));
        BoardPaneBase boardPane = new SquareBoardPane(world, width, height);
        KeyboardManager keyboardManager = new KeyboardManager(world, boardPane);
        MainFrame mainFrame = new MainFrame(boardPane, keyboardManager);
        world.turn(boardPane);
    }
}
