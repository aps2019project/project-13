import Controller.*;

import java.io.*;
import java.util.ArrayList;

import Model.*;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;


public class Main {


    public static void main(String[] args) {

        YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
        ArrayList<Hero> heroes = new ArrayList<>();
//        heroes.add(new Hero("Dive Sefid", "1",1 , 8000,
//                "Has continous power buff with 4 units of AP" , HeroName.DIVE_SEFID,AttackKind.MELEE,
//                50,4,1,
//                new Spell("NoName" , "1000",1,0,)));
//        GameController gamecontroller = GameController.getInstance();
//        gamecontroller.main();
    }
}
