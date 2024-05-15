import game.World;
import game.board.SquareBoard;
import game.organism.animals.*;
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
        world.getOrganisms().spawnOrganism(new Fox(new Point(5, 4)));
        world.getOrganisms().spawnOrganism(new Fox(new Point(7, 8)));
        world.getOrganisms().spawnOrganism(new Fox(new Point(2, 1)));
        world.getOrganisms().spawnOrganism(new Fox(new Point(10, 5)));
        world.getOrganisms().spawnOrganism(new Wolf(new Point(10, 2)));
        world.getOrganisms().spawnOrganism(new Wolf(new Point(18, 12)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(1, 1)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(4, 3)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(7, 5)));
        world.getOrganisms().spawnOrganism(new Sheep(new Point(7, 2)));
        world.getOrganisms().spawnOrganism(new Antelope(new Point(8, 9)));
        world.getOrganisms().spawnOrganism(new Antelope(new Point(15, 3)));
        world.getOrganisms().spawnOrganism(new Antelope(new Point(2, 3)));
        world.getOrganisms().spawnOrganism(new Turtle(new Point(1, 5)));
        world.getOrganisms().spawnOrganism(new Turtle(new Point(4, 10)));
        world.getOrganisms().spawnOrganism(new Turtle(new Point(6, 10)));
        BoardPaneBase boardPane = new SquareBoardPane(world, width, height);
        KeyboardManager keyboardManager = new KeyboardManager(world, boardPane);
        MainFrame mainFrame = new MainFrame(boardPane, keyboardManager);
        world.turn(boardPane);
    }
}
