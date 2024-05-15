package ui;

import ui.Board.BoardPaneBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class MainFrame extends JFrame {
    BoardPaneBase boardPane;

    public MainFrame(BoardPaneBase boardPane, KeyListener keyboardManager, AbilityStatus abilityStatus) {
        super("Wirtualny swiat 198349");
        this.boardPane = boardPane;
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().add(abilityStatus);
        getContentPane().add(boardPane);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        addKeyListener(keyboardManager);
        pack();
    }
}
