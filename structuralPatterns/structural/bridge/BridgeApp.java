package structural.bridge;

/**
 * Created by Nazar on 07.08.2017.
 */
public class BridgeApp {
    public static void main(String[] args) {
        Car c = new Sedan(new Kia());
        c.showDetails();

        c = new Hatchbach(new Skoda());
        c.showDetails();
    }
}

abstract class Car{
    Make make;

    public Car(Make make) {
        this.make = make;
    }

    abstract void showType();
    void showDetails(){
        showType();
        make.setMake();
    }
}

class Sedan extends Car{
    public Sedan(Make make) {
        super(make);
    }

    @Override
    void showType() {
        System.out.println("Sedan");
    }
}

class Hatchbach extends Car{
    public Hatchbach(Make make) {
        super(make);
    }

    @Override
    void showType() {
        System.out.println("Hatchback");
    }
}

interface Make{
    void setMake();
}

class Kia implements Make{
    @Override
    public void setMake() {
        System.out.println("Kia");
    }
}

class Skoda implements Make{
    @Override
    public void setMake() {
        System.out.println("Skoda");
    }
}