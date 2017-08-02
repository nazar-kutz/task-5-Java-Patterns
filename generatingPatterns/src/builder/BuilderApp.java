package builder;

/**
 * Created by Nazar on 02.08.2017.
 */
public class BuilderApp {
    public static void main(String[] args) {
        /*Car car = new CarBuilder().buildMake("Mercedes")
                .buildTransmission(Transmission.AUTO)
                .buildMaxSpeed(280).build();

        System.out.println(car);*/

        Director director = new Director();
        director.setBuilder(new SubaruBuilder());
        Car car = director.BuildCar();
        System.out.println(car);

        director.setBuilder(new FordModelBuilder());
        car = director.BuildCar();
        System.out.println(car);
    }
}

enum Transmission{
    MANUAL, AUTO
}

class Car{
    String make;
    Transmission transmission;
    int maxSpeed;

    public void setMake(String make) {
        this.make = make;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", transmission=" + transmission +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}

/*
class CarBuilder{
    String m = "Default";
    Transmission t = Transmission.MANUAL;
    int s = 120;

    CarBuilder buildMake(String m){
        this.m = m;
        return this;
    }

    CarBuilder buildTransmission(Transmission t){
        this.t = t;
        return this;
    }

    CarBuilder buildMaxSpeed(int s){
        this.s = s;
        return this;
    }

    Car build(){
        Car car = new Car();
        car.setMake(m);
        car.setTransmission(t);
        car.setMaxSpeed(s);
        return car;
    }
}*/

abstract class CarBuilder{
    Car car;
    void createCar(){car = new Car();}

    abstract void buildMake();
    abstract void buildTransmission();
    abstract void buildMaxSpeed();

    Car getCar(){
        return car;
    }
}

class FordModelBuilder extends CarBuilder{
    @Override
    void buildMake() {
        car.setMake("Ford Mondeo");
    }

    @Override
    void buildTransmission() {
        car.setTransmission(Transmission.AUTO);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(260);
    }
}

class SubaruBuilder extends CarBuilder{
    @Override
    void buildMake() {
        car.setMake("Subaru");
    }

    @Override
    void buildTransmission() {
        car.setTransmission(Transmission.MANUAL);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(320);
    }
}

class Director{
    CarBuilder builder;

    public void setBuilder(CarBuilder builder) {
        this.builder = builder;
    }

    Car BuildCar(){
        builder.createCar();
        builder.buildMake();
        builder.buildTransmission();
        builder.buildMaxSpeed();

        Car car = builder.getCar();
        return car;
    }
}
