import Controller.*;

import java.io.*;
import java.sql.BatchUpdateException;
import java.util.ArrayList;

import Model.*;
import com.google.gson.*;

public class Main {


    public static void main(String[] args) {

        GameController gameController = GameController.getInstance();
        gameController.main();

    }
}
