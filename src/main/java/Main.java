import game.World;
import game.WorldFactory;
import game.board.BoardType;
import ui.AbilityStatus;
import ui.board.BoardPaneBase;
import ui.board.BoardPaneHolder;
import ui.board.HexBoardPane;
import ui.board.SquareBoardPane;
import ui.QuestionModal;
import ui.KeyboardManager;
import ui.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Cannot set system look and feel, defaulting to cross-platform look and feel");
        }
        BoardType boardType = QuestionModal.askBoardType();
        int width, height;
        if (boardType == BoardType.HEX) {
            width = height = QuestionModal.readDimension("height");
        } else {
            width = QuestionModal.readDimension("width");
            height = QuestionModal.readDimension("height");
        }
        AbilityStatus abilityStatus = AbilityStatus.getInstance();
        World world = WorldFactory.createWorld(width, height, abilityStatus, boardType);
        BoardPaneBase boardPane = boardType == BoardType.SQUARE ? new SquareBoardPane(world, width, height) : new HexBoardPane(world, width, height);
        BoardPaneHolder boardPaneHolder = new BoardPaneHolder(boardPane);
        KeyboardManager keyboardManager = new KeyboardManager(world);
        new MainFrame(boardPaneHolder, keyboardManager, abilityStatus, world);
        abilityStatus.update(AbilityStatus.Status.READY);
        world.setBoardPane(boardPaneHolder);
        boardPaneHolder.getBoardPane().redraw();
    }
}
