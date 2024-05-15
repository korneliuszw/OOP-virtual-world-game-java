package ui;

import ui.Board.BoardPaneBase;

import javax.swing.*;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    BoardPaneBase boardPane;

    public MainFrame(BoardPaneBase boardPane, KeyListener keyboardManager) {
        super("Wirtualny swiat 198349");
        this.boardPane = boardPane;
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().add(boardPane);
        addKeyListener(keyboardManager);
        pack();
    }

    public void draw() {
        boardPane.redraw();
    }
}
