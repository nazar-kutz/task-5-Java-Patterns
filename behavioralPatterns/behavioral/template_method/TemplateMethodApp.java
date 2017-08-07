package behavioral.template_method;

/**
 * Created by Nazar on 06.08.2017.
 */
public class TemplateMethodApp {
    public static void main(String[] args) {
        C c = new A();
        c.templateMethod();

        System.out.println();

        c = new B();
        c.templateMethod();
    }
}

abstract class C{
    void templateMethod(){
        System.out.print("1");
        differ();
        System.out.print("3");
        differ2();
    }

    abstract void differ();
    abstract void differ2();
}

class A extends C{
    @Override
    void differ() {
        System.out.print("2");
    }

    @Override
    void differ2() {
        System.out.print("5");
    }
}

class B extends C{
    @Override
    void differ() {
        System.out.print("4");
    }

    @Override
    void differ2() {}
}