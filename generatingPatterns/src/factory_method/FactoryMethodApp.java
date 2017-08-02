package factory_method;

import java.util.Date;

/**
 * Created by Nazar on 02.08.2017.
 */
public class FactoryMethodApp {
    public static void main(String[] args) {
        /*Watch watch = new DigitalWatch();
        watch.showTime();

        watch = new RomeWatch();
        watch.showTime();*/

        /*watchMaker maker = new DigitalWatchMaker();
        Watch watch = maker.createWatch();
        watch.showTime();

        maker = new RomeWatchMaker();
        watch = maker.createWatch();
        watch.showTime();*/

        watchMaker maker = getMakerByName("Digital");
        Watch watch = maker.createWatch();
        watch.showTime();

        maker = getMakerByName("Rome");
        watch = maker.createWatch();
        watch.showTime();
    }

    public static watchMaker getMakerByName(String maker){
        if(maker.equals("Digital")){
            return new DigitalWatchMaker();
        } else if (maker.equals("Rome")){
            return new RomeWatchMaker();
        }
        throw new RuntimeException("Не поддержываемая производственная линия часов " + maker);
    }
}

interface Watch{
    void showTime();
}

class DigitalWatch implements Watch{
    @Override
    public void showTime() {
        System.out.println(new Date());
    }
}

class RomeWatch implements Watch{
    @Override
    public void showTime() {
        System.out.println("VII-XX");
    }
}

interface watchMaker{
    Watch createWatch();
}

class DigitalWatchMaker implements watchMaker{
    @Override
    public Watch createWatch() {
        return new DigitalWatch();
    }
}

class RomeWatchMaker implements watchMaker{
    @Override
    public Watch createWatch() {
        return new RomeWatch();
    }
}