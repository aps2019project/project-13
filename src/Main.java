import Controller.*;
import Model.Account;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {
        try{
            YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader("accounts.json");
            Account[] accounts = new Account[1000];
            accounts = yaGson.fromJson(reader, (Type) Account[].class);
            for (Account account : accounts) {
                Account.getAccounts().add(account);
            }

        }catch (Exception e){

        }
        GameController gamecontroller = GameController.getInstance();
        gamecontroller.main();
    }
}