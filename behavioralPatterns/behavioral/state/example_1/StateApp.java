package behavioral.state.example_1;

/**
 * Created by Nazar on 06.08.2017.
 */
public class StateApp {
    public static void main(String[] args) {
        Station dfm = new RadioDFM();
        Radio radio = new Radio();
        radio.setStation(dfm);

        for (int i = 0; i < 10; i++) {
            radio.play();
            radio.nextStation();
        }
    }
}

interface Station {
    void play();
}

class Radio7 implements Station {
    @Override
    public void play() {
        System.out.println("Radio 7...");
    }
}

class RadioDFM implements Station {
    @Override
    public void play() {
        System.out.println("Radio DFM...");
    }
}

class NewsFM implements Station {
    @Override
    public void play() {
        System.out.println("Radio NEWS...");
    }
}

//Context
class Radio{
    Station station;
    void setStation(Station station){
        this.station = station;
    }

    void nextStation(){
        if(station instanceof Radio7){
            setStation(new RadioDFM());
        }
        else if(station instanceof  RadioDFM){
            setStation(new NewsFM());
        }
        else if(station instanceof NewsFM){
            setStation(new Radio7());
        }
    }

    void play(){
        station.play();
    }
}
