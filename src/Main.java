import Controller.*;
import Model.*;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.*;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {
        try {
            YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader("accounts.json");
            Account[] accounts = new Account[1000];
            accounts = yaGson.fromJson(reader, (Type) Account[].class);
            for (Account account : accounts) {
                Account.getAccounts().add(account);
            }

        } catch (Exception e) {

        }
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        Minion[] cards = new Minion[40];
        try (Reader reader = new FileReader("Minions_YaGson.json")) {
            cards = yaGson.fromJson(reader, Minion[].class);
        } catch (IOException e) {
            System.out.println(":DD");
        }
        int i = 1;
        for (Minion minion : cards) {
            Shop.getInstance().addCard(minion);
        }


        GameController gamecontroller = GameController.getInstance();
        gamecontroller.main();
    }
}