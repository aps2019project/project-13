package View;

import java.util.ArrayList;
import java.util.Scanner;

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

        }
    }

    private void commandOfShop(String command) {
        if (command.equals("exit")) {

        } else if (command.equals("show")) {

        } else if (command.equals("show collection")) {

        } else if (command.equals("help")) {

        } else if (command.matches("search \\s+")) {

        } else if (command.matches("search collection \\s+")) {

        } else if (command.matches("buy \\s+")) {

        } else if (command.matches("sell \\s+")) {

        }
    }

    private void commandOfBattle(String command) {


        if (command.equals("exit")) {
            if (flagCollectable) {
                flagCollectable = false;
            } else if (flagGraveYard) {
                flagGraveYard = false;
            }
        } else if (command.equals("Game info")) {

        } else if (command.equals("Show my minions")) {

        } else if (command.equals("help")) {

        } else if (command.equals("Show opponent minions")) {

        } else if (command.matches("Show card info \\s+")) {

        } else if (command.matches("select \\s+") && !flagCollectable) {

        } else if (command.matches("move to (\\d+, \\d+)")) {

        } else if (command.matches("Attack \\s+")) {

        } else if (command.matches("Attack combo \\s+")) {

        } else if (command.matches("Use special power (\\d+, \\d+)")) {

        } else if (command.matches("Insert \\s+ in (\\d+, \\d+)")) {

        } else if (command.equals("Show hand")) {

        } else if (command.equals("End turn")) {

        } else if (command.equals("Show collectables")) {
            flagCollectable = true;
        } else if (command.matches("select \\s+") && flagCollectable) {

        } else if (command.equals("Show info")&& flagCollectable) {

        } else if (command.matches("Use (\\d+, \\d+)")&& flagCollectable) {

        } else if (command.equals("Show Next Card")) {

        } else if (command.equals("Enter graveyard")) {
            flagGraveYard = true;
        } else if (command.matches("Show info \\s+")&& flagGraveYard) {

        } else if (command.equals("Show cards") && flagGraveYard) {

        } else if (command.equals("End Game")) {

        } else if (command.equals("Show menu")) {

        }
    }

    private void commandOfAccount(String command) {
        if (command.equals("help")) {

        } else if (command.equals("logout")) {

        } else if (command.equals("show leaderboard")) {

        } else if (command.equals("save")) {

        } else if (command.matches("login \\s+")) {

        } else if (command.matches("create account \\s+")) {

        }
    }

    private void commandOfMainMenu(String command) {
        if (command.matches("Enter \\s+")) {

        }
    }

    private void commandOfCollection(String command) {
        if (command.equals("exit")) {

        } else if (command.equals("show")) {

        } else if (command.equals("save")) {

        } else if (command.equals("show all deck")) {

        } else if (command.equals("help")) {

        } else if (command.matches("search \\s+")) {

        } else if (command.matches("create deck \\s+")) {

        } else if (command.matches("delete deck \\s+")) {

        } else if (command.matches("add \\s+ to deck \\s+")) {

        } else if (command.matches("remove \\s+ from deck \\s+")) {

        } else if (command.matches("validate deck \\s+")) {

        } else if (command.matches("select deck \\s+")) {

        } else if (command.matches("show deck \\s+")) {

        }
    }
}
