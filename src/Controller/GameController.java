package Controller;

import View.*;
import Model.*;
import View.Error;


import java.util.ArrayList;
import java.util.Scanner;

public class GameController {
    private static final GameController gamecontroller = new GameController();
    private boolean isFinish = false;
    private static Show show = Show.getInstance();

    private GameController() {

    }

    public static GameController getInstance() {

        return gamecontroller;
    }

    public void main() {
        Request request = Request.getInstance();
        while (!isFinish) {
            try {
                request.getRequest();
                commandManagement(request, request.getKindOfOrders().get(request.getKindOfOrders().size() - 1));
            } catch (Error e) {
                show.showError(e);
            }
        }
    }
    //TODO INVALID OUTPUT HANDLING

    private void commandManagement(Request request, KindOfOrder kindOfOrder) throws Error {
        switch (kindOfOrder) {
            case COLLECTION:
                collectionCommandManagement(request, request.getCollectionCommand());
                break;
            case MAIN_MENU:
                mainMenuCommandManagement(request, request.getMainCommand());
                break;
            case ACCOUNT:
                accountCommandManagement(request, request.getAccountCommand());
                break;
            case BATTLE:
                battleCommandManagement(request, request.getBattleCommand());
                break;
            case SHOP:
                shopCommandManagement(request, request.getShopCommand());
                break;
            case CARD:
                cardCommandManagement(request, request.getCardCommand());
                break;
            case GRAVEYARD:
                graveYardCommandManagement(request, request.getGraveYardCommand());
                break;
            case COLLECTABLE:
                collectableCommandManagement(request, request.getCollectableCommand());
        }
    }

    private void shopCommandManagement(Request request, ShopCommand shopCommand) throws Error {

        Shop shop = Shop.getInstance();
        Show show = Show.getInstance();

        switch (shopCommand) {
            case BUY:
                shop.buy(shopCommand.getData(), new Account("userName", "password"));
                break;
            case EXIT:
                request.exitLastmenu();
                break;
            case HELP:
                show.showHelp(KindOfOrder.SHOP);
                break;
            case SELL:
                shop.sell(shopCommand.getData(), new Account("userName", "password"));
                break;
            case SHOW:
            case SEARCH:
            case SHOW_MENU:
            case SHOW_COLLECTION:
            case SEARCH_COLLECTION:
        }
    }

    private void mainMenuCommandManagement(Request request, MainCommand mainCommand) {
        switch (mainCommand) {
            case SHOW_MENU:
                show.showHelp(KindOfOrder.MAIN_MENU);
            case ENTER_EXIT:
                request.exitLastmenu();
            case ENTER_HELP:
                show.showHelp(KindOfOrder.MAIN_MENU);
            case ENTER_SHOP:
                request.addNewMenu(KindOfOrder.SHOP);
            case ENTER_BATTLE:
                request.addNewMenu(KindOfOrder.BATTLE);
            case ENTER_COLLECTION:
                request.addNewMenu(KindOfOrder.COLLECTION);
        }
    }

    private void accountCommandManagement(Request request, AccountCommand accountCommand) throws Error {
        switch (accountCommand) {
            case EXIT:
                request.exitLastmenu();
                break;
            case SHOW_MENU:
                show.showHelp(KindOfOrder.ACCOUNT);
            case HELP:
                show.showHelp(KindOfOrder.ACCOUNT);
                break;
            case SAVE:
                //TODO save to file
            case LOGIN:
                login(request, accountCommand);
                break;
            case LOGOUT:
                Account.setLoginedAccount(null);
            case CREATE_ACCOUNT:
                createAccount(request, accountCommand);
                break;
            case SHOW_LEADERBOARD:
        }
    }

    private void createAccount(Request request, AccountCommand accountCommand) {
        String userName = accountCommand.getData();
        for (Account account :
                Account.getAccounts()) {
            if (account.getUsername().equals(userName)) {
                throw new Error(ConstantMessages.USERNAME_EXIST.getMessage());
            }
        }

        show.getPassword();
        String passWord;
        Scanner scanner = request.getScanner();
        passWord = scanner.nextLine();
        while (passWord.length() < 4) {
            show.unreliablePassWord();
            show.getPassword();
            passWord = scanner.nextLine();
        }
        Account account = new Account(userName, passWord);
        show.createdAccount(userName);
        Account.setLoginedAccount(account);
        request.addNewMenu(KindOfOrder.MAIN_MENU);
        show.showMainMenu();
    }

    private void login(Request request, AccountCommand accountCommand) {
        String userName = accountCommand.getData();
        Account trueAccount = null;
        for (Account account :
                Account.getAccounts()) {
            if (account.getUsername().equals(userName)) {
                trueAccount = account;
            }
        }
        if (trueAccount == null) {
            throw new Error(ConstantMessages.USERNAME_NOT_EXIST.getMessage());
        }
        show.getYourPasWord();
        String passWord;
        Scanner scanner = request.getScanner();
        passWord = scanner.nextLine();
        while (!passWord.equals(trueAccount.getPassword())) {
            show.incorrectPassWord();
            show.getYourPasWord();
            passWord = scanner.nextLine();
        }
        Account.setLoginedAccount(trueAccount);
        request.addNewMenu(KindOfOrder.MAIN_MENU);
        show.showMainMenu();

    }

    private void battleCommandManagement(Request request, BattleCommand battleCommand) {

        Battle.getRunningBattle().setCurrentTurnPlayer();

        switch (battleCommand) {
            case HELP:
                show.showHelp(KindOfOrder.BATTLE);
                break;
            case INSERT:
                battleInsert(battleCommand);
                break;
            case SELECT:
                battleSelect(battleCommand);
                break;
            case END_GAME:
                battleEndGame();
                break;
            case END_TURN:
                battleEndTurn();
                break;
            case GAME_INFO:
                battleGameInfo();
                break;
            case SHOW_HAND:
                battleShowHand();
                break;
            case SHOW_CARD_INFO:
                battleShowCardInfo();
                break;
            case SHOW_NEXT_CARD:
                battleShowNextCard();
                break;
            case ENTER_GRAVE_YARD:
                battleEnterGraveyard();
                break;
            case SHOW_MY_MINIONS:
                battleShowMinion(true);
                //TODO PASS THIS TO SHOW
                return;
            case SHOW_COLLECTABLE:
            case SHOW_OPPONENT_MINIONS:
                battleShowMinion(false);
                //TODO PASS THIS TO SHOW
        }
    }

    private ArrayList<String> battleShowMinion(boolean isYoursMinion) {
        Account account;
        Battle battle = Battle.getRunningBattle();
        if (isYoursMinion) {
            account = battle.getCurrentTurnPlayer();
        } else {
            account = battle.getOtherTurnPlayer();
        }
        Map map = battle.getMap();
        ArrayList<String> output = new ArrayList<>();
        for (int i = 0; i < map.MAX_ROW; i++) {
            for (int j = 0; j < map.MAX_COLUMN; j++) {
                Cell cell = map.getCell(i, j);
                if (cell.getCard().getAccount().equals(account)) {
                    output.add(cell.getCard().toString() + "- Position: " + i + 1 + " , " + j + 1);
                }
            }
        }
        return output;
    }

    private void battleEnterGraveyard() {
        Battle.getRunningBattle().enterGraveYard();
    }

    private void battleShowNextCard() {
        Battle battle = Battle.getRunningBattle();
        battle.showNextCard(); //TODO NEED TO BE CHANGED.
    }

    private void battleShowCardInfo() {
        Card card = Battle.getRunningBattle().getSelectedCard();
        //TODO SHOW SOMETHING
    }

    private void battleShowHand() {
        Battle battle = Battle.getRunningBattle();
        if (battle.getTurn() % 2 == 1) {
            battle.getFirstPlayerHand();
            //TODO SHOW SOMETHING
        } else {
            battle.getSecondPlayerHand();
            //TODO SHOW SOMETHING
        }
    }

    private void battleGameInfo() {
        Battle battle = Battle.getRunningBattle();
        if (battle.getGameGoal() == GameGoal.KILL_HERO) {
            //TODO SHOW SOMETHING
        } else if (battle.getGameGoal() == GameGoal.COLLECT_FLAG) {
            //TODO SHOW SOMETHING
        } else if (battle.getGameGoal() == GameGoal.HOLD_FLAG) {
            //TODO SHOW SOMETHING ELSE
        }
    }

    private void battleEndTurn() {
        Battle battle = Battle.getRunningBattle();
        battle.endTurn();
    }

    private void battleEndGame() {
        Battle battle = Battle.getRunningBattle();
        battle.endGame();
    }

    private void battleSelect(BattleCommand battleCommand) {
        String cardName = battleCommand.getData().get(0);
        Battle battle = Battle.getRunningBattle();
        Account account = battle.getCurrentTurnPlayer();
        ArrayList<Card> hand = new ArrayList<>();
        if (battle.getTurn() % 2 == 1) {
            hand = battle.getFirstPlayerHand();
        } else {
            hand = battle.getSecondPlayerHand();
        }
        Card card = Card.findCardInArrayList(cardName, hand);
        if (card != null) {
            battle.selectCard(cardName);
        } else {
            throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
        }
    }

    private void battleInsert(BattleCommand battleCommand) throws Error {

        String cardName = battleCommand.getData().get(0);
        int x = Integer.parseInt(battleCommand.getData().get(1));
        int y = Integer.parseInt(battleCommand.getData().get(2));
        Battle battle = Battle.getRunningBattle();
        battle.insertCard(cardName, x, y);

    }

    private void collectionCommandManagement(Request request, CollectionCommand collectionCommand) {
        switch (collectionCommand) {
            case HELP:
                collectionHelp();
                break;
            case SAVE:
            case SHOW_MENU:
            case EXIT:
                request.getKindOfOrders().remove(request.getKindOfOrders().size() - 1);
                break;
            case SEARCH:
                collectionSearch(request, collectionCommand);
                break;
            case SHOW:
                collectionShow(request);
                break;
            case SHOW_DECK:
                collectionShowDeck(request, collectionCommand);
                break;
            case ADD_TO_DECK:
                collectionAddToDeck(request, collectionCommand);
                break;
            case CREATE_DECK:
                collectionCreateDeck(request, collectionCommand);
                break;
            case DELETE_DECK:
                collectionDeleteDeck(request, collectionCommand);
                break;
            case SELECTE_DECK:
                collectionSelectMainDeck(request, collectionCommand);
                break;
            case SHOW_ALL_DECK:
                collectionShowAllDeck(request);
                break;
            case VALIDATE_DECK:
                collectionValidateDeck(request, collectionCommand);
                break;
            case REMOVE_FROM_DECK:
                collectionRemoveCardFromDeck(request, collectionCommand);
                break;
        }
    }

    private void collectionHelp() {
        //TODO CALL A FUNCTION
    }

    private void collectionSearch(Request request, CollectionCommand collectionCommand) {
        Account account = request.getAccount();
        String name = collectionCommand.getData().get(0);
        account.getCardCollection().search(name);
        //TODO CALL SHOW METHOD
    }

    private void collectionShow(Request request) {
        Account account = request.getAccount();
        //TODO CALL A SHOW METHOD
    }

    private void collectionShowDeck(Request request, CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = request.getAccount().findDeck(deckName);
        if (deck != null) {
            //TODO A METHOD FROM SHOW
            System.out.println(deck.toString());
        }
    }

    private void collectionRemoveCardFromDeck(Request request, CollectionCommand collectionCommand) {
        String cardName = collectionCommand.getData().get(0);
        String deckName = collectionCommand.getData().get(1);
        Deck deck = request.getAccount().findDeck(deckName);
        if (deck != null) {
            deck.removeCard(cardName);
        }
        //TODO MAYBE ERRORS?!
    }

    private void collectionShowAllDeck(Request request) {
        Account account = request.getAccount();
        for (int i = 0; i < account.getDecks().size(); i++) {
            Deck deck = account.getDecks().get(i);
            if (deck != null) {

                //TODO THIS MUST BE DONE IN VIEW
                System.out.println(deck.toString());
            }
        }
    }

    private void collectionValidateDeck(Request request, CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = request.getAccount().findDeck(deckName);
        if (deck != null) {
            Deck.validateDeck(deck);
            System.out.println(ConstantMessages.VALID_DECK);
        }
    }

    private void collectionSelectMainDeck(Request request, CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = request.getAccount().findDeck(deckName);
        if (deck != null) {
            request.getAccount().setMainDeck(deck);
        }
    }

    private void collectionDeleteDeck(Request request, CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = request.getAccount().findDeck(deckName);
        if (deck != null) {
            if (request.getAccount().getMainDeck().equals(deck)) {
                request.getAccount().setMainDeck(null);
            }
            request.getAccount().getDecks().remove(deck);
        }
    }

    private void collectionCreateDeck(Request request, CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        if (request.getAccount().findDeck(deckName) == null) {
            Deck.createDeck(deckName, request.getAccount());
        }
    }

    private void collectionAddToDeck(Request request, CollectionCommand collectionCommand) {
        String cardName = collectionCommand.getData().get(0);
        String deckName = collectionCommand.getData().get(1);
        Deck deck = request.getAccount().findDeck(deckName);
        Card card = Card.findCardInArrayList(cardName, request.getAccount().getCardCollection().getCards());
        if (deck != null && card != null) {
            deck.addCard(card);
        }
    }

    private void cardCommandManagement(Request request, CardCommand cardCommand) throws Error {
        switch (cardCommand) {
            case EXIT:
                request.getKindOfOrders().remove(request.getKindOfOrders().size() - 1);
                break;
            case MOVE:
                cardCommandMove(cardCommand);
                break;
            case ATTACK: {
                Battle battle = Battle.getRunningBattle();
                String cardName = cardCommand.getData().get(0);
                Cell cell = battle.getMap().findCardCell(cardName);
                if (cell != null) {
                    battle.attack(cell);
                } else {
                    throw new Error(ConstantMessages.INVALID_TARGET.getMessage());
                }

            }
            case USE_SPECIAL_POWER:
                //TODO
        }
    }

    private void cardCommandMove(CardCommand cardCommand) throws Error {
        Battle battle = Battle.getRunningBattle();
        Card card = battle.getSelectedCard();
        if (card == null) {
            throw new Error(ConstantMessages.NO_CARD_SELECTED.getMessage());
        }
        try {
            int x = Integer.parseInt(cardCommand.getData().get(0));
            int y = Integer.parseInt(cardCommand.getData().get(1));
            battle.moveCard(battle.getMap().getCell(x, y));
        } catch (Exception e) {
            //TODO CHECK THIS TRY CATCH
        }
    }

    private void graveYardCommandManagement(Request request, GraveYardCommand graveYardCommand) {
        switch (graveYardCommand) {
            case EXIT:
                request.getKindOfOrders().remove(request.getKindOfOrders().size() - 1);
                break;
            case SHOW_CARDS:
                //TODO IT IS ONLY RELATED TO SHOW. IT MUST SHOW ALL CARDS OF BATTLE GRAVEYARD SO A FOR ON CARDS AND SOUT!

                break;
            case SHOW_INFO:
                graveyardShowInfo(graveYardCommand);
                break;
        }
    }

    private void graveyardShowInfo(GraveYardCommand graveYardCommand) throws Error {
        String cardName = graveYardCommand.getData();
        Battle battle = Battle.getRunningBattle();
        Card card = Card.findCardInArrayList(cardName, battle.getGraveYardCards());
        if (card != null) {
            //TODO SHOW Item using CARD . toString
        } else {
            throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
        }
    }

    private void collectableCommandManagement(Request request, CollectableCommand collectableCommand) {
        switch (collectableCommand) {
            //TODO IN BAKHSH ZEDEH NASHODEH
            case SHOW_INFO:
            case EXIT:
                request.getKindOfOrders().remove(request.getKindOfOrders().size() - 1);
                break;
            case USE:
        }
    }


}
