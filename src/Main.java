import Controller.*;
import Model.*;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.*;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;


public class Main {


    public static void main(String[] args) {

        initAccounts();
        initMinions();
        initHeroes();
        initItems();
        initSpells();

        GameController gamecontroller = GameController.getInstance();
        gamecontroller.main();
    }

    private static void initHeroes() {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();

        Hero[] heroes = new Hero[20];
        Hero[] heroes1 = new Hero[20];
        try (Reader reader = new FileReader("Heroes_YaGson_New.json")) {
            heroes = yaGson.fromJson(reader, Hero[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Reader reader = new FileReader("Heroes_YaGson2_New.json")) {
            heroes1 = yaGson.fromJson(reader, Hero[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Hero hero : heroes) {
            if (hero != null)
                Shop.getInstance().addCard(hero);
        }
        for (Hero hero : heroes1) {
            if (hero != null)
                Shop.getInstance().addCard(hero);
        }
    }

    private static void initMinions() {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        Minion[] cards = new Minion[40];
        try (Reader reader = new FileReader("Minions_YaGson_New.json")) {
            cards = yaGson.fromJson(reader, Minion[].class);
        } catch (IOException e) {
            System.out.println(":DD");
        }
        int i = 1;
        for (Minion minion : cards) {
            Shop.getInstance().addCard(minion);
        }
    }

    private static void initSpells() {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();

        Spell[] spells = new Spell[20];
        try (Reader reader = new FileReader("Spells_YaGson_New.json")) {
            spells = yaGson.fromJson(reader, Spell[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int j = 0; j < spells.length; j++) {
            if (spells[j] != null) {
                Shop.getInstance().addCard(spells[j]);
            }
        }
    }

    private static void initItems() {
        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();

        Item[] items = new Item[20];
        try (Reader reader = new FileReader("Items_YaGson_New.json")) {
            items = yaGson.fromJson(reader, Item[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int j = 0; j < items.length; j++) {
            if (items[j] != null) {
                Shop.getInstance().addItem((UsableItem) items[j]);
            }
        }
    }

    private static void initAccounts() {
        try {
            YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader("accounts.json");
            Account[] accounts = new Account[1000];
            accounts = yaGson.fromJson(reader, (Type) Account[].class);
            if (accounts != null) {
                for (Account account : accounts) {
                    Account.getAccounts().add(account);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}