package structural.facade;

/**
 * Created by Nazar on 07.08.2017.
 */
public class FacadeApp {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.copy();
    }
}

class Computer{
    Power power = new Power();
    DVDRom dvd = new DVDRom();
    HDD hdd = new HDD();

    void copy(){
        power.on();
        dvd.load();
        hdd.copyFromDVD(dvd);
    }
}

class Power{
    void on(){
        System.out.println("switch on the power");
    }
    void off(){
        System.out.println("switch off the power");
    }
}

class DVDRom{
    private boolean data = false;

    public boolean hasData(){
        return data;
    }

    void load(){
        data = true;
    }

    void unload(){
        data = false;
    }
}

class HDD{
    void copyFromDVD(DVDRom dvd){
        if(dvd.hasData()){
            System.out.println("Происходит копирование данных с диска");
        }
        else {
            System.out.println("Вставьте диск с данными");
        }
    }
}
