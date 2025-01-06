package zad3;

import javax.swing.*;
import java.awt.*;

public class Zad3 extends JFrame {
    public Zad3(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel glowny
        JPanel panelGlowny = new JPanel();
        panelGlowny.setPreferredSize(new Dimension(200, 500));
        panelGlowny.setLayout(new BorderLayout());

        JLabel statusLabel = new JLabel("Status: Gotowe");
        panelGlowny.add(statusLabel, BorderLayout.NORTH);

        // Panel kanwa
        JPanel panelKanwa = new JPanel();
        panelKanwa.setLayout(null);

        Zad3KanwaPanel textPanel = new Zad3KanwaPanel(statusLabel);
        textPanel.setBounds(0, 0, 660, 483);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panelKanwa.add(textPanel);

        add(panelGlowny, BorderLayout.EAST);
        add(panelKanwa, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Zad3 frame = new Zad3("Moje Okno");
            frame.setVisible(true);
        });
    }
}
