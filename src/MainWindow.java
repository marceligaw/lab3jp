import controller.AppController;
import sensors.SharedBuffer;
import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class MainWindow extends JFrame {
    private final JLabel tempLabel = new JLabel("Temperatura: 0");
    private final JLabel humLabel = new JLabel("Wilgotność: 0");
    private final JLabel pressLabel = new JLabel("Ciśnienie: 0");
    private final SharedBuffer tempBuffer = new SharedBuffer();
    private final SharedBuffer humBuffer = new SharedBuffer();
    private final SharedBuffer pressBuffer = new SharedBuffer();
    private final AppController controller;
    private Timer guiTimer;

    public MainWindow() {
        setTitle("System monitorowania czujników");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));

        add(tempLabel);
        add(humLabel);
        add(pressLabel);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton historyButton = new JButton("Pokaż historię");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(historyButton);
        add(buttonPanel);

        controller = new AppController(tempBuffer, humBuffer, pressBuffer);

        startButton.addActionListener(e -> startSensors());
        stopButton.addActionListener(e -> stopSensors());
        historyButton.addActionListener(e -> showHistory());
    }

    private void startSensors() {
        controller.startSensors();
        guiTimer = new Timer(500, e -> updateLabels());
        guiTimer.start();
    }

    private void stopSensors() {
        controller.stopSensors();
        if (guiTimer != null) guiTimer.stop();
    }

    private void updateLabels() {
        tempLabel.setText("Temperatura: " + tempBuffer.getCurrentValue() + "°C");
        humLabel.setText("Wilgotność: " + (humBuffer.getCurrentValue()) + "%");
        pressLabel.setText("Ciśnienie: " + pressBuffer.getCurrentValue() + "hPa");
    }

    private void showHistory() {
        StringBuilder historyText = new StringBuilder("Historia pomiarów:\n");
        historyText.append("Temperatura: ").append(tempBuffer.getHistory()).append("\n");
        historyText.append("Wilgotność: ").append(humBuffer.getHistory()).append("\n");
        historyText.append("Ciśnienie: ").append(pressBuffer.getHistory()).append("\n");

        JOptionPane.showMessageDialog(this, historyText.toString(), "Historia", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });
    }
}
