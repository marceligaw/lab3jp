package sensors;

import java.util.Random;

public class Sensor implements Runnable {
    private final String name;
    private final SharedBuffer buffer;
    private final int interval; // w ms
    private boolean running;
    private final Random random = new Random();

    public Sensor(String name, SharedBuffer buffer, int interval) {
        this.name = name;
        this.buffer = buffer;
        this.interval = interval;
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            int value = random.nextInt(100); // Losowa wartość 0-99
            buffer.addValue(value); // dodaj wartość do bufora
            try {
                Thread.sleep(interval); // interwał
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }

    public String getName() {
        return name;
    }
}
