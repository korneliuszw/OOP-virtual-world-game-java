package xyz.kawuka;

import xyz.kawuka.game.World;
import xyz.kawuka.game.WorldFactory;
import xyz.kawuka.game.board.BoardType;
import xyz.kawuka.ui.AbilityStatus;
import xyz.kawuka.ui.board.BoardPaneBase;
import xyz.kawuka.ui.board.BoardPaneHolder;
import xyz.kawuka.ui.board.HexBoardPane;
import xyz.kawuka.ui.board.SquareBoardPane;
import xyz.kawuka.ui.QuestionModal;
import xyz.kawuka.ui.KeyboardManager;
import xyz.kawuka.ui.MainFrame;

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
