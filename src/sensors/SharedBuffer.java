package sensors;

import java.util.LinkedList;
import java.util.Queue;

public class SharedBuffer {
    private static final int MAX_HISTORY = 10;
    private final Queue<Integer> history = new LinkedList<>();
    private int currentValue;

    public synchronized void addValue(int value) {
        currentValue = value;
        if (history.size() >= MAX_HISTORY) {
            history.poll(); // usuwamy najstarszy element
        }
        history.offer(value); // dodajemy nowy element
    }

    public synchronized int getCurrentValue() {
        return currentValue;
    }

    public synchronized Queue<Integer> getHistory() {
        return new LinkedList<>(history);
    }
}
