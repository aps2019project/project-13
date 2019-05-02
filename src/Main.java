import Controller.*;
import Model.Card;
import Model.Minion;
import Model.Shop;
import Model.UsableItem;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class Main {


    public static void main(String[] args) {

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
            System.out.println(i + ": " + minion.toString());
            i++;
        }
        GameController gamecontroller = GameController.getInstance();
        gamecontroller.main();
    }
}