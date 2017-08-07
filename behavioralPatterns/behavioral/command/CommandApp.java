package behavioral.command;

/**
 * Created by Nazar on 06.08.2017.
 */
public class CommandApp {
    public static void main(String[] args) {
        Comp comp = new Comp();
        User user = new User(new StartCommand(comp), new StopCommand(comp), new ResetCommand(comp));

        user.startComputer();
        user.stopComputer();
        user.resetComputer();
    }
}

interface Command{
    void execute();
}

//Reciver
class Comp{
    void start(){
        System.out.println("Start");
    }

    void stop(){
        System.out.println("Stop");
    }

    void reset(){
        System.out.println("Reset");
    }
}

//concreteCommand
class StartCommand implements Command{
    Comp computer;

    public StartCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.start();
    }
}

class StopCommand implements Command{
    Comp computer;

    public StopCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.stop();
    }
}

class ResetCommand implements Command{
    Comp computer;

    public ResetCommand(Comp computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.reset();
    }
}

//Invoker
class User{
    Command start;
    Command stop;
    Command reset;

    public User(Command start, Command stop, Command reset) {
        this.start = start;
        this.stop = stop;
        this.reset = reset;
    }

    void startComputer(){
        start.execute();
    }

    void stopComputer(){
        stop.execute();
    }

    void resetComputer(){
        reset.execute();
    }
}
