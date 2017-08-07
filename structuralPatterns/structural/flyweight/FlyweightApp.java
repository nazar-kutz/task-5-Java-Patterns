package structural.flyweight;

import java.util.*;

/**
 * Created by Nazar on 07.08.2017.
 */
public class FlyweightApp {
    public static void main(String[] args) {
        ShapeFacoty shapeFacoty = new ShapeFacoty();

        List<Shape> shapes = new ArrayList<>();

        shapes.add(shapeFacoty.getShape("квадрат"));
        shapes.add(shapeFacoty.getShape("круг"));
        shapes.add(shapeFacoty.getShape("круг"));
        shapes.add(shapeFacoty.getShape("точка"));
        shapes.add(shapeFacoty.getShape("квадрат"));
        shapes.add(shapeFacoty.getShape("круг"));

        Random rand = new Random();
        for(Shape shape : shapes){
            int x = rand.nextInt(100);
            int y = rand.nextInt(100);
            shape.draw(x, y);
        }
    }
}

//Flyweight
interface Shape{
    void draw(int x, int y);
}

class Point implements Shape{
    @Override
    public void draw(int x, int y) {
        System.out.println("(" + x + ", " + y + "): рисуем точку");
    }
}

class Circle implements Shape{
    int r = 5;
    @Override
    public void draw(int x, int y) {
        System.out.println("(" + x + ", " + y + "): рисуем круг радиусом " + r);
    }
}

class Square implements Shape{
    int a = 10;
    @Override
    public void draw(int x, int y) {
        System.out.println("(" + x + ", " + y + "): рисуем квадрат со стороной " + a);
    }
}

class ShapeFacoty{
    private static final Map<String, Shape> shapes = new HashMap<>();
    public Shape getShape(String shapeName){
        Shape shape = shapes.get(shapeName);
        if(shape==null){
            switch (shapeName){
                case "круг":
                    shape = new Circle();
                    break;
                case "квадрат":
                    shape = new Square();
                    break;
                case "точка":
                    shape = new Point();
                    break;
            }
            shapes.put(shapeName, shape);
        }
        return shape;
    }
}