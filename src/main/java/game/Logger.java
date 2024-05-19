package game;

import game.organism.OrganismBase;

import javax.swing.*;
import java.awt.*;

public class Logger {
    private final JTextArea textArea;
    private static Logger instance;

    Logger(Container container) {
        JTextArea textArea = new JTextArea(5, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setEditable(false);
        scrollPane.setPreferredSize(new Dimension(200, 300 + scrollPane.getHorizontalScrollBar().getPreferredSize().height));
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(scrollPane);
        container.add(panel);
        textArea.setFocusable(false);
        this.textArea = textArea;
    }

    public void logOrganismAction(OrganismBase organism, String message, OrganismBase target) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s%d] %s", organism.getSymbol(), organism.getId(), message));
        if (target != null) {
            sb.append(String.format(" (%s%d)", target.getSymbol(), target.getAge()));
        }
        log(sb.toString());
    }

    public void log(String message) {
        textArea.append(message + "\n");
        System.out.println(message);
        textArea.setCaretPosition(textArea.getDocument().getLength());
        textArea.revalidate();
        textArea.repaint();
    }

    public static void init(Container container) {
        instance = new Logger(container);
    }

    public void clear() {
        textArea.setText("");
    }

    public static Logger getInstance() {
        return instance;
    }

}
