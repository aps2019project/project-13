import Controller.*;

import java.io.*;
import java.util.ArrayList;

import Model.*;
import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;


public class Main {


    public static void main(String[] args) {


        GameController gamecontroller = GameController.getInstance();
        gamecontroller.main();
    }
}
