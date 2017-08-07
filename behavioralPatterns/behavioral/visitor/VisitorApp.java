package behavioral.visitor;

/**
 * Created by Nazar on 06.08.2017.
 */
public class VisitorApp {
    public static void main(String[] args) {
        Element car = new CarElement();

        car.accept(new HooliganVisitor());
        car.accept(new MechanicVisitor());
    }
}

interface Visitor{
    void visit(EngineElement engine);
    void visit(BodyElement body);
    void visit(CarElement car);
    void visit(WheelElement wheel);
}

interface Element{
    void accept(Visitor visitor);
}

class BodyElement implements Element{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class EngineElement implements Element{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class HooliganVisitor implements Visitor{
    @Override
    public void visit(CarElement car) {
        System.out.println("Покурил внутри машины");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Пнул " + wheel.getName() + " колесо");
    }

    @Override
    public void visit(EngineElement engine) {
        System.out.println("Завел двигатель");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Постучал по корпусу");
    }
}

class MechanicVisitor implements Visitor{
    @Override
    public void visit(CarElement car) {
        System.out.println("Проверил внешний вид автомобиля");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("Подкачал " + wheel.getName() + " колесо");
    }

    @Override
    public void visit(EngineElement engine) {
        System.out.println("Проверил двигатель");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Отполировал кузов");
    }
}

class WheelElement implements Element{
    private String name;

    public WheelElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {

    }
}

class CarElement implements Element{
    Element[] elements;

    public CarElement() {
        this.elements = new Element[]{new WheelElement("переднее левое"),
                new WheelElement("переднее правое"), new WheelElement("заднее левое"),
                new WheelElement("заднее правое"), new BodyElement(), new EngineElement()};
    }

    @Override
    public void accept(Visitor visitor) {
        for(Element elem : elements){
            elem.accept(visitor);
        }
        visitor.visit(this);
    }
}