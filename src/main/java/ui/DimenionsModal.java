package ui;

import javax.swing.*;

public class DimenionsModal {
    public static int readDimension(String dimension) {
        while (true) {
            String width = JOptionPane.showInputDialog(String.format("Enter the %s of the board", dimension));
            if (width == null) {
                System.exit(0);
            }
            System.out.println(width);
            try {
                int numericValue = Integer.parseInt(width);
                if (numericValue > 0) {
                    return numericValue;
                } else {
                    JOptionPane.showMessageDialog(null, "Value must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Value must be a number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
