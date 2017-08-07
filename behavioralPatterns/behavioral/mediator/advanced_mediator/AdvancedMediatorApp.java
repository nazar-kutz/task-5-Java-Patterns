package behavioral.mediator.advanced_mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nazar on 06.08.2017.
*/

public class AdvancedMediatorApp {
    public static void main(String[] args) {
        TextChat chat = new TextChat();
        User admin = new Admin(chat, "Иван Иваныч");
        User u1 = new SimpleUser(chat, "Ваня");
        User u2 = new SimpleUser(chat, "Вова");
        User u3 = new SimpleUser(chat, "Саша");
        u2.setEnable(false);

        chat.setAdmin(admin);
        chat.addUser(u1);
        chat.addUser(u2);
        chat.addUser(u3);

        u1.sendMessage("Привет");
        admin.sendMessage("Привет");
    }
}

abstract class User{
    Chat chat;
    String name;
    boolean isEnable = true;

    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable(){
        return  isEnable;
    }

    public void setEnable(boolean isEnable){
        this.isEnable = isEnable;
    }

    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    abstract void getMessage(String message);
}

class Admin extends User{
    public Admin(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    void getMessage(String message) {
        System.out.println("Администратор " + getName() + " получаєт сообщение '" + message + "'");
    }
}

class SimpleUser extends User{
    public SimpleUser(Chat chat, String name) {
        super(chat, name);
    }

    @Override
    void getMessage(String message) {
        System.out.println("Пользователь " + getName() + " получаєт сообщение '" + message + "'");
    }
}

interface Chat{
    void sendMessage(String message, User user);
}

class TextChat implements Chat{
    User admin;
    List<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        if(admin!=null && admin instanceof Admin){
            this.admin = admin;
        }
        else {
            throw new RuntimeException("Не хватает прав");
        }
    }

    public void addUser(User user){
        if(admin==null){
            throw new RuntimeException("В чате нет админа!");
        }
        if(user instanceof SimpleUser){
            users.add(user);
        }
        else {
            throw new RuntimeException("Админ не может входить в другой чат!");
        }
    }

    @Override
    public void sendMessage(String message, User user) {
        if(user instanceof Admin){
            for(User u : users){
                u.getMessage(user.getName() + ": " + message);
            }
        }
        if(user instanceof SimpleUser){
            for(User u : users){
                if(u!=user && u.isEnable()){
                    u.getMessage(user.getName() + ": " + message);
                }
            }
            if(admin.isEnable()){
                admin.getMessage(user.getName() + ": " + message);
            }
        }
    }
}
