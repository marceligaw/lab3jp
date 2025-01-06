package zad5;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class Zad5KanwaPanel extends JPanel implements MouseInputListener {
    private boolean drawCircle = true; // true = koło, false = prostokąt
    private int mouseX = 0;
    private int mouseY = 0;
    private int figureSize = 50; // rozmiar figury
    private JLabel statusLabel; // etykieta do wyświetlania komunikatów w panelu bocznym

    public Zad5KanwaPanel(JLabel statusLabel) {
        this.statusLabel = statusLabel;

        setFocusable(true);
        requestFocusInWindow();

        // Obsługa klawiatury
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    drawCircle = true; // Rysuj koła
                    statusLabel.setText("Wybrano: koła");
                } else if (e.getKeyCode() == KeyEvent.VK_R) {
                    drawCircle = false; // Rysuj prostokąty
                    statusLabel.setText("Wybrano: prostokąty");
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    figureSize = Math.min(200, figureSize + 5); // Zwiększ rozmiar, max 200
                    statusLabel.setText("Zwiększono rozmiar figury: " + figureSize + " px.");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    figureSize = Math.max(10, figureSize - 5); // Zmniejsz rozmiar, min 10
                    statusLabel.setText("Zmniejszono rozmiar figury: " + figureSize + " px.");
                }
                repaint();
            }
        });

        // Obsługa myszy
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Pozycja myszy: (" + mouseX + ", " + mouseY + ")", 10, 20);

        // Rysowanie figury (tam, gdzie kliknięto)
        if (drawCircle) {
            g.setColor(Color.RED);
            g.fillOval(mouseX - figureSize / 2, mouseY - figureSize / 2, figureSize, figureSize); // Koło
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(mouseX - figureSize / 2, mouseY - figureSize / 2, figureSize, figureSize); // Prostokąt
        } // X, Y, szerokość elipsy, wysokość elipsy
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //mouseX = e.getX();
        //mouseY = e.getY();
        //statusLabel.setText("Kliknięto myszą na obszarze rysowania.");
        //repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        statusLabel.setText("Przycisk myszy został naciśnięty.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        statusLabel.setText("Przycisk myszy został zwolniony.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        statusLabel.setText("Kursor jest w obszarze rysowania.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        statusLabel.setText("Kursor opuścił obszar rysowania.");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }
}
