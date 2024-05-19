package ui;

import game.board.BoardType;

import javax.swing.*;

public class QuestionModal {
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

    public static BoardType askBoardType() {
        while (true) {
            String[] options = {"Square", "Hex"};
            int choice = JOptionPane.showOptionDialog(null, "Choose the board type", "Board type", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (choice == 0) {
                return BoardType.SQUARE;
            } else if (choice == 1) {
                return BoardType.HEX;
            }
        }
    }
}
