package View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Request {
    private static final Request request = new Request();
    private ArrayList<KindOfOrder> kindOfOrder = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean flagCollectable = false;
    private boolean flagGraveYard = false;

    private Request() {
        kindOfOrder.add(KindOfOrder.ACCOUNT);
    }

    public static Request getInstance() {
        return request;
    }

    public void getRequest() {
        String command = scanner.nextLine();
        transferCommandToRightPlace(command);
    }

    private void transferCommandToRightPlace(String command) {
        switch (kindOfOrder.get(kindOfOrder.size())) {
            case SHOP:
                commandOfShop(command);
                break;
            case BATTLE:
                commandOfBattle(command);
                break;
            case ACCOUNT:
                commandOfAccount(command);
                break;
            case MAIN_MENU:
                commandOfMainMenu(command);
                break;
            case COLLECTION:
                commandOfCollection(command);
                break;
            case CARD:
                commandOfCard(command);
                break;
            case GRAVEYARD:
                commandOfGraveyard(command);
                break;
            case COLLECTABLE:
                commandOfCollectable(command);
                break;

        }
    }

    private void commandOfShop(String command) {
        for (int i = 0; i < Patterns.shopPatterns.length; i++) {
            Matcher matcher = Patterns.shopPatterns[i].matcher(command);
            {
                //TODO do something
            }
        }
    }

    private void commandOfBattle(String command) {


        if (command.equals("exit")) {
            if (flagCollectable) {
                flagCollectable = false;
            } else if (flagGraveYard) {
                flagGraveYard = false;
            }
        }
        for (int i = 0; i < Patterns.battlePatterns.length; i++) {
            Matcher matcher = Patterns.battlePatterns[i].matcher(command);
            {
                //TODO do something
            }
        }
    }

    private void commandOfCollectable(String command) {
        for (int i = 0; i < Patterns.collectionPatterns.length; i++) {
            Matcher matcher = Patterns.collectionPatterns[i].matcher(command);
            {
                //TODO do something
            }
        }
    }

    private void commandOfCard(String command) {
        for (int i = 0; i < Patterns.cardPatterns.length; i++) {
            Matcher matcher = Patterns.cardPatterns[i].matcher(command);
            {
                //TODO do something
            }
        }
    }

    private void commandOfGraveyard(String command) {
        for (int i = 0; i < Patterns.graveyardPatters.length; i++) {
            Matcher matcher = Patterns.graveyardPatters[i].matcher(command);
            {
                //TODO do something
            }
        }
    }

    private void commandOfAccount(String command) {
        for (int i = 0; i < Patterns.accountPatterns.length; i++) {
            Matcher matcher = Patterns.accountPatterns[i].matcher(command);
            {
                //TODO do something
            }
        }
    }

    private void commandOfMainMenu(String command) {
        for (int i = 0; i < Patterns.mainMenuPatterns.length; i++) {
            Matcher matcher = Patterns.mainMenuPatterns[i].matcher(command);
            {
                //TODO do something
            }
        }
    }

    private void commandOfCollection(String command) {
        for (int i = 0; i < Patterns.collectionPatterns.length; i++) {
            Matcher matcher = Patterns.collectionPatterns[i].matcher(command);
            {
                //TODO do something
            }
        }
    }
}
