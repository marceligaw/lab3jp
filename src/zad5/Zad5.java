package zad5;

import javax.swing.*;
import java.awt.*;

class Zad5 extends JFrame {
    public Zad5(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBoczny = new JPanel();
        panelBoczny.setPreferredSize(new Dimension(200, 500));
        panelBoczny.setLayout(new BorderLayout());

        JLabel statusLabel = new JLabel("Status: Gotowe");
        panelBoczny.add(statusLabel, BorderLayout.NORTH);

        JPanel panelKanwa = new JPanel();
        panelKanwa.setLayout(null);

        Zad5KanwaPanel kanwa = new Zad5KanwaPanel(statusLabel);
        kanwa.setBounds(0, 0, 660, 483);
        kanwa.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panelKanwa.add(kanwa);

        add(panelBoczny, BorderLayout.EAST);
        add(panelKanwa, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Zad5 frame = new Zad5("Moje Okno");
            frame.setVisible(true);
        });
    }
}
