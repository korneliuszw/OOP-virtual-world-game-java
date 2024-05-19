package ui.Board.Cell;

import game.organism.OrganismBase;

import java.awt.*;
import java.awt.geom.Area;

public class HexCell extends CellBase {
    public static final int CELL_SIZE = 32;
    private final Point start;

    public HexCell(Point position, OrganismBase organism, CellClickSpawn cellClickSpawn, int size) {

//        hexagon.addPoint(position.x * CELL_SIZE / 2, position.y * CELL_SIZE);
//        hexagon.addPoint(position.x * CELL_SIZE / 2 + CELL_SIZE / 2, position.y * CELL_SIZE + CELL_SIZE / 2);
//        hexagon.addPoint(position.x * CELL_SIZE / 2 - CELL_SIZE / 2, position.y * CELL_SIZE + CELL_SIZE / 2);
//        hexagon.addPoint(position.x * CELL_SIZE / 2 + CELL_SIZE, position.y * CELL_SIZE + CELL_SIZE);
        super(position, organism, cellClickSpawn);
        int offsetX = size * CELL_SIZE;
        int rowStartX = offsetX - (position.y > size ? 2 * size - position.y : position.y) * CELL_SIZE;
        int startX = rowStartX + position.x * 2 * CELL_SIZE;
        int startY = position.y * CELL_SIZE / 2;
        start = new Point(startX, startY);
        this.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBounds(startX, startY, CELL_SIZE, CELL_SIZE);
//        setBorder(null);
    }

    Polygon createHex(Point start, int offset, int padding) {
        Polygon hexagon = new Polygon();
        hexagon.addPoint(start.x + offset + padding, start.y + padding);
        hexagon.addPoint(start.x + padding, start.y + CELL_SIZE / 2);
        hexagon.addPoint(start.x + offset + padding, start.y + CELL_SIZE - padding);
        hexagon.addPoint(start.x + CELL_SIZE - offset - padding, start.y + CELL_SIZE - padding);
        hexagon.addPoint(start.x + CELL_SIZE - padding, start.y + CELL_SIZE / 2);
        hexagon.addPoint(start.x + CELL_SIZE - offset - padding, start.y + padding);
        hexagon.addPoint(start.x + offset + padding, start.y + padding);
        return hexagon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        Polygon areaPolygon = createHex(start, 2, 0);
//        g2d.setStroke(new BasicStroke(3));
//        g2d.setColor(getBackground());
//        g2d.fillPolygon(areaPolygon);
//        g2d.setColor(Color.BLACK);
//        g2d.drawPolygon(areaPolygon);
//        g2d.setBackground(getBackground());
//        g2d.fillPolygon(areaPolygon);
//
//        FontMetrics fm = g2d.getFontMetrics();
//        int textWidth = fm.stringWidth(getText());
//        int textHeight = fm.getAscent();
//        int textX = (getWidth() - textWidth) / 2;
//        int textY = (getHeight() + textHeight) / 2 - 4; // Adjust vertical alignment
//        g2d.setColor(getForeground());
//        g2d.drawString(getText(), textX, textY);
    }
}
