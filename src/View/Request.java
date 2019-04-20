package View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Request {
    private static final Request request = new Request();
    private ArrayList<KindOfOrder> kindOfOrder = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

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
                if (matcher.matches()) {
                    setCommandOfShop(matcher, i);
                }
            }
        }
    }

    private void commandOfBattle(String command) {

        for (int i = 0; i < Patterns.battlePatterns.length; i++) {
            Matcher matcher = Patterns.battlePatterns[i].matcher(command);
            {
                if (matcher.matches()) {
                    setCommandOfBattle(matcher, i);
                }
            }
        }
    }

    private void commandOfCollectable(String command) {
        for (int i = 0; i < Patterns.collectablePatterns.length; i++) {
            Matcher matcher = Patterns.collectablePatterns[i].matcher(command);
            {
                if (matcher.matches()) {
                    setCommandOfCollectable(matcher, i);
                }
            }
        }
    }

    private void commandOfCard(String command) {
        for (int i = 0; i < Patterns.cardPatterns.length; i++) {
            Matcher matcher = Patterns.cardPatterns[i].matcher(command);
            {
                if (matcher.matches()) {
                    setCommandOfCard(matcher, i);
                }
            }
        }
    }

    private void commandOfGraveyard(String command) {
        for (int i = 0; i < Patterns.graveyardPatters.length; i++) {
            Matcher matcher = Patterns.graveyardPatters[i].matcher(command);
            {
                if (matcher.matches()) {
                    setCommandOfGraveyard(matcher, i);
                }
            }
        }
    }

    private void commandOfAccount(String command) {
        for (int i = 0; i < Patterns.accountPatterns.length; i++) {
            Matcher matcher = Patterns.accountPatterns[i].matcher(command);
            {
                if (matcher.matches()) {
                    setCommandOfAccount(matcher, i);
                }
            }
        }
    }

    private void commandOfMainMenu(String command) {
        for (int i = 0; i < Patterns.mainMenuPatterns.length; i++) {
            Matcher matcher = Patterns.mainMenuPatterns[i].matcher(command);
            {
                if (matcher.matches()) {
                    setCommandOfMainMenu(matcher, i);
                }
            }
        }
    }

    private void commandOfCollection(String command) {
        for (int i = 0; i < Patterns.collectionPatterns.length; i++) {
            Matcher matcher = Patterns.collectionPatterns[i].matcher(command);
            {
                if (matcher.matches()) {
                    setCommandOfCollection(matcher, i);
                }
            }
        }
    }

    private void setCommandOfShop(Matcher matcher, int i) {

    }

    private void setCommandOfAccount(Matcher matcher, int i) {

    }

    private void setCommandOfCollection(Matcher matcher, int i) {

    }

    private void setCommandOfMainMenu(Matcher matcher, int i) {

    }

    private void setCommandOfGraveyard(Matcher matcher, int i) {

    }

    private void setCommandOfCard(Matcher matcher, int i) {

    }

    private void setCommandOfCollectable(Matcher matcher, int i) {

    }

    private void setCommandOfBattle(Matcher matcher, int i) {

    }

}
