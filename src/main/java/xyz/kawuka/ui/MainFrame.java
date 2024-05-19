package xyz.kawuka.ui;

import xyz.kawuka.game.Logger;
import xyz.kawuka.game.World;
import xyz.kawuka.game.board.HexBoard;
import xyz.kawuka.ui.board.BoardPaneHolder;
import xyz.kawuka.ui.board.HexBoardPane;
import xyz.kawuka.ui.board.SquareBoardPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;

public class MainFrame extends JFrame {
    private final BoardPaneHolder boardPane;
    private String lastSavePath;
    private World world;
    private final AbilityStatus abilityStatus;
    private final KeyboardManager keyboardManager;

    public MainFrame(BoardPaneHolder boardPane, KeyboardManager keyboardManager, AbilityStatus abilityStatus, World world) {
        super("Wirtualny swiat 198349");
        this.boardPane = boardPane;
        this.world = world;
        this.abilityStatus = abilityStatus;
        this.keyboardManager = keyboardManager;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        createContentPane();
    }

    void createContentPane() {
        Container container = new Container();
        container.add(abilityStatus);
        container.add(boardPane.getBoardPane());
        Container boardContainer = new Container();
        boardContainer.setLayout(new BoxLayout(boardContainer, BoxLayout.X_AXIS));
        boardContainer.add(boardPane.getBoardPane());
        Logger.init(boardContainer);
        container.add(boardContainer);
        setContentPane(container);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        scrollable();
        createMenu();
        pack();
        requestFocusInWindow();
        removeKeyListener(keyboardManager);
        addKeyListener(keyboardManager);
    }

    void scrollable() {
        // set maximum size to screen size
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = env.getMaximumWindowBounds();
        setMaximumSize(new Dimension(bounds.width, bounds.height));
        // add scroll bars
        JScrollPane scrollPane = new JScrollPane(getContentPane());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(scrollPane);
    }

    void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.VK_S);
        save.addActionListener(e -> save());
        JMenuItem load = new JMenuItem("Load");
        load.setMnemonic(KeyEvent.VK_L);
        load.addActionListener(e -> load());
        JMenuItem exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_Q);
        exit.addActionListener(e -> quit());
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
        assert path != null;
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(world);
                objectOutputStream.flush();
                JOptionPane.showMessageDialog(this, "Game saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                Logger.getInstance().log("Game saved");
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
                    boardPane.setBoardPane(world.getBoardSupplier() instanceof HexBoard ? new HexBoardPane(world, world.getWidth(), world.getHeight()) : new SquareBoardPane(world, world.getWidth(), world.getHeight()));
                    boardPane.getBoardPane().changeWorld(world);
                    keyboardManager.setWorld(world);
                    world.setBoardPane(boardPane);
                    keyboardManager.reset();
                    createContentPane();
                    getContentPane().revalidate();
                    getContentPane().repaint();
                    boardPane.getBoardPane().redraw();
                    Logger.getInstance().clear();
                    Logger.getInstance().log("Game loaded");
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

}