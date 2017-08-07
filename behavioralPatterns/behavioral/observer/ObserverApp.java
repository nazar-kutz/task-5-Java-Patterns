package behavioral.observer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nazar on 06.08.2017.
 */
public class ObserverApp {
    public static void main(String[] args) {
        MeteoStation station = new MeteoStation();
        station.addObserver(new ConsoleObserver());
        station.addObserver(new FileObserver());

        station.setMeasurements(25, 760);
        station.setMeasurements(29, 800);
    }
}

interface Observered{
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}

class MeteoStation implements Observered{
    int temperature;
    int pressure;

    List<Observer> observers = new ArrayList<>();

    public void setMeasurements(int t, int p){
        temperature = t;
        pressure = p;
        notifyObserver();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer o : observers){
            o.handleEvent(temperature, pressure);
        }
    }
}

interface Observer{
    void handleEvent(int temperature, int pressure);
}

class ConsoleObserver implements Observer{
    @Override
    public void handleEvent(int temperature, int pressure) {
        System.out.println("Погода изменилась. Температура = " + temperature + ", Давление = " + pressure + ".");
    }
}

class FileObserver implements Observer{
    @Override
    public void handleEvent(int temperature, int pressure) {
        File f;
        try{
            f = File.createTempFile("TempPressure", "_txt");
            PrintWriter pw = new PrintWriter(f);
            pw.print("Погода изменилась. Температура = " + temperature + ", Давление = " + pressure + ".");
            pw.println();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}