package View;

import Model.Battle;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Request {
    private static final Request request = new Request();
    private ArrayList<KindOfOrder> kindOfOrder = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private AccountCommand accountCommand;
    private BattleCommand battleCommand;
    private CardCommand cardCommand;
    private CollectableCommand collectableCommand;
    private CollectionCommand collectionCommand;
    private GraveYardCommand graveYardCommand;
    private MainCommand mainCommand;
    private ShopCommand shopCommand;

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
                    setCommandOfMainMenu(i);
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
        if (i > 3) {
            shopCommand = ShopCommand.values()[i].setData(matcher.group(1));
        } else shopCommand = ShopCommand.values()[i];
    }

    private void setCommandOfAccount(Matcher matcher, int i) {
        if (i == 3 || i == 4) {
            accountCommand = AccountCommand.values()[i].setData(matcher.group(1));
        } else accountCommand = AccountCommand.values()[i];
    }

    private void setCommandOfCollection(Matcher matcher, int i) {
        if (i == 4 || (i > 5 && i < 11) || i == 13) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(matcher.group(1));
            if (i == 8 || i == 9) {
                strings.add(matcher.group(2));
            }
            collectionCommand = CollectionCommand.values()[i].setData(strings);
        } else
            collectionCommand = CollectionCommand.values()[i];
    }

    private void setCommandOfMainMenu(int i) {
        mainCommand = MainCommand.values()[i];
    }

    private void setCommandOfGraveyard(Matcher matcher, int i) {
        if (i == 2) {
            graveYardCommand = GraveYardCommand.values()[i].setData(matcher.group(1));
        } else graveYardCommand = GraveYardCommand.values()[i];
    }

    private void setCommandOfCard(Matcher matcher, int i) {
        if (i > 1) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(matcher.group(1));
            if (i != 3) {
                strings.add(matcher.group(2));
            }
            cardCommand = CardCommand.values()[i].setData(strings);
        } else
            cardCommand = CardCommand.values()[i];
    }

    private void setCommandOfCollectable(Matcher matcher, int i) {
        if (i == 3) {
            collectableCommand = CollectableCommand.values()[i].setData(matcher.group(1));
        } else collectableCommand = CollectableCommand.values()[i];
    }

    private void setCommandOfBattle(Matcher matcher, int i) {
        if (i > 2 && i < 6) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(matcher.group(1));
            if (i == 5) {
                strings.add(matcher.group(2));
                strings.add(matcher.group(3));
            }
            battleCommand = BattleCommand.values()[i].setData(strings);
        }
        battleCommand = BattleCommand.values()[i];
    }

    public AccountCommand getAccountCommand() {
        return accountCommand;
    }

    public ArrayList<KindOfOrder> getKindOfOrder() {
        return kindOfOrder;
    }

    public BattleCommand getBattleCommand() {
        return battleCommand;
    }

    public CardCommand getCardCommand() {
        return cardCommand;
    }

    public CollectableCommand getCollectableCommand() {
        return collectableCommand;
    }

    public CollectionCommand getCollectionCommand() {
        return collectionCommand;
    }

    public GraveYardCommand getGraveYardCommand() {
        return graveYardCommand;
    }

    public MainCommand getMainCommand() {
        return mainCommand;
    }

    public ShopCommand getShopCommand() {
        return shopCommand;
    }
}
