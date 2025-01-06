package controller;

import sensors.Sensor;
import sensors.SharedBuffer;
import java.util.ArrayList;
import java.util.List;

public class AppController {
    private final List<Sensor> sensors = new ArrayList<>();
    private final List<Thread> sensorThreads = new ArrayList<>(); // do łatwiejszego zarządzania wątkami
    private final SharedBuffer tempBuffer;
    private final SharedBuffer humBuffer;
    private final SharedBuffer pressBuffer;

    public AppController(SharedBuffer tempBuffer, SharedBuffer humBuffer, SharedBuffer pressBuffer) {
        this.tempBuffer = tempBuffer;
        this.humBuffer = humBuffer;
        this.pressBuffer = pressBuffer;
    }

    public void startSensors() {
        sensors.add(new Sensor("Temperatura", tempBuffer, 4000));
        sensors.add(new Sensor("Wilgotność", humBuffer, 4000));
        sensors.add(new Sensor("Ciśnienie", pressBuffer, 4000));

        for (Sensor sensor : sensors) {
            Thread thread = new Thread(sensor);
            sensorThreads.add(thread);
            thread.start();
        }
    }

    public void stopSensors() {
        for (Sensor sensor : sensors) {
            sensor.stop();
        }
        sensors.clear();
    }
}
