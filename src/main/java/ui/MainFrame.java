package ui;

import game.World;
import ui.Board.BoardPaneBase;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.file.Files;

public class MainFrame extends JFrame {
    BoardPaneBase boardPane;
    String lastSavePath;
    World world;
    AbilityStatus abilityStatus;
    KeyboardManager keyboardManager;

    public MainFrame(BoardPaneBase boardPane, KeyboardManager keyboardManager, AbilityStatus abilityStatus, World world) {
        super("Wirtualny swiat 198349");
        this.boardPane = boardPane;
        this.world = world;
        this.abilityStatus = abilityStatus;
        this.keyboardManager = keyboardManager;
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        getContentPane().add(abilityStatus);
        getContentPane().add(boardPane);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        addKeyListener(keyboardManager);
        pack();
        createMenu();
    }

    void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.setMnemonic(KeyEvent.CTRL_DOWN_MASK + KeyEvent.VK_N);
        newGame.addActionListener(e -> newGame());
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.CTRL_DOWN_MASK + KeyEvent.VK_S);
        save.addActionListener(e -> save());
        JMenuItem load = new JMenuItem("Load");
        load.setMnemonic(KeyEvent.CTRL_DOWN_MASK + KeyEvent.VK_L);
        load.addActionListener(e -> load());
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.CTRL_DOWN_MASK + KeyEvent.VK_Q);
        exit.addActionListener(e -> quit());
        menu.add(newGame);
        menu.add(save);
        menu.add(load);
        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    void save() {
        String path = lastSavePath;
        if (path == null) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                path = lastSavePath = fileChooser.getSelectedFile().getAbsolutePath();
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(world);
                objectOutputStream.flush();
                JOptionPane.showMessageDialog(this, "Game saved", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Save file doesn't exit or can't write into it", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Saving failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void load() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            try (InputStream input = Files.newInputStream(fileChooser.getSelectedFile().toPath())) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(input)) {
                    world = (World) objectInputStream.readObject();
                    world.getPlayer().setAbilityStatus(abilityStatus);
                    boardPane.changeWorld(world);
                    keyboardManager.setWorld(world);
                    world.setBoardPane(boardPane);
                    boardPane.redraw();
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Loading failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void quit() {
        System.exit(0);
    }

    void newGame() {

    }

}