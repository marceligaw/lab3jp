package zad3;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Zad3KanwaPanel extends JPanel {
    private JTextField textField;
    private boolean isMouseOutside = false; // stan myszy

    public Zad3KanwaPanel(JLabel statusLabel) {
        setLayout(null);

        // Pole tekstowe
        textField = new JTextField();
        textField.setBounds(50, 50, 300, 30);
        add(textField);

        // Listener dla klawiatury
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isMouseOutside) { // Reagujemy tylko, gdy mysz jest poza ekranem
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_A:
                            handleTextChange(statusLabel, "A");
                            break;
                        case KeyEvent.VK_B:
                            handleTextChange(statusLabel, "B");
                            break;
                        case KeyEvent.VK_C:
                            handleTextChange(statusLabel, "C");
                            break;
                    }
                }
            }
        });

        // Obsługa zmiany stanu myszy
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseOutside = false; // Mysz weszła do panelu
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseOutside = true; // Mysz wyszła z panelu
                requestFocusInWindow(); // Panel przejmuje focus
            }
        });

        setFocusable(true); // Panel musi być focusowalny
    }

    // Jedna metoda obsługująca zmiany tekstu
    private void handleTextChange(JLabel statusLabel, String currentValue) {
        if (currentValue.endsWith("A")) {
            textField.setForeground(Color.RED);
            statusLabel.setText("Tekst zmienił kolor na czerwony");
        } else if (currentValue.endsWith("B")) {
            textField.setForeground(Color.BLUE);
            statusLabel.setText("Tekst zmienił kolor na niebieski");
        } else if (currentValue.endsWith("C")) {
            textField.setText("");
            statusLabel.setText("Pole tekstowe wyczyszczone");
        }
    }
}
