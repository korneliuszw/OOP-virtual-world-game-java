package ui;


import javax.swing.*;

public class AbilityStatus extends JLabel {

    public enum Status {
        READY, IN_PROGRESS, COOLDOWN
    }

    public AbilityStatus() {
        super();
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    public void update(Status status) {
        String message = "";
        switch (status) {
            case READY:
                message = "Całopalenie jest gotowe do użycia!";
                break;
            case IN_PROGRESS:
                message = "Całopalenie jest aktywne!";
                break;
            case COOLDOWN:
                message = "Całopalenie jest w trakcie odnowienia!";
                break;
        }
        setText(message);
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }
}
