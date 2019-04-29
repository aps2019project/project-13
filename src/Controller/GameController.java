package Controller;

import View.*;
import Model.*;


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
            request.getRequest();
            commandManagement(request, request.getKindOfOrder().get(request.getKindOfOrder().size() - 1));
        }
    }

    //TODO INVALID INPUT HANDLING
    //TODO INVALID OUTPUT HANDLING

    private void commandManagement(Request request, KindOfOrder kindOfOrder) {
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

    private void shopCommandManagement(Request request, ShopCommand shopCommand) {
        Shop shop = Shop.getInstance();
        Show show = Show.getInstance();
        switch (shopCommand) {
            case BUY:
                shop.buy(shopCommand.getData(), new Account("userName", "password"));
                break;
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case HELP:
                show.showHelp(KindOfOrder.SHOP);
            case SELL:
                shop.sell(shopCommand.getData(), new Account("userName", "password"));
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
                //TODO bde b show
            case ENTER_EXIT:
                isFinish = true;
                break;
            case ENTER_HELP:
                //TODO bde b show
            case ENTER_SHOP:
                //TODO bde b show
            case ENTER_BATTLE:
                //TODO bde b show
            case ENTER_COLLECTION:
                //TODO bde b show
        }
    }

    private void accountCommandManagement(Request request, AccountCommand accountCommand) {
        switch (accountCommand) {
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case SHOW_MENU:
            case HELP:
                show.showHelp(KindOfOrder.ACCOUNT);
                break;
            case SAVE:
                //TODO save to file
            case LOGIN:
                login(request, accountCommand);
                break;
            case LOGOUT:
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
                //TODO send error
                return;
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
        new Account(userName, passWord);
        show.createdAccount(userName);
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
            //TODO send error
            return;
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
        //TODO set account

    }

    private void battleCommandManagement(Request request, BattleCommand battleCommand) {
        switch (battleCommand) {
            case HELP:
                show.showHelp(KindOfOrder.BATTLE);
                break;
            case INSERT: {
                String cardName =  battleCommand.getData().get(0);
                int x = Integer.parseInt( battleCommand.getData().get(1));
                int y = Integer.parseInt(battleCommand.getData().get(2));
                Battle battle = Battle.getRunningBattle();


            }
            break;

            case SELECT:
            case END_GAME:
            case END_TURN:
            case GAME_INFO:
            case SHOW_HAND:
            case SHOW_CARD_INFO:
            case SHOW_NEXT_CARD:
            case ENTER_GRAVE_YARD:
            case SHOW_MY_MINIONS:
            case SHOW_COLLECTABLE:
            case SHOW_OPPONENT_MINIONS:
        }
    }

    private void collectionCommandManagement(Request request, CollectionCommand collectionCommand) {
        switch (collectionCommand) {
            case HELP:
                collectionHelp();
                break;
            case SAVE:
            case SHOW_MENU:
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
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
            //TODO SHOW SOMETHING
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

    private void cardCommandManagement(Request request, CardCommand cardCommand) {
        switch (cardCommand) {
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case MOVE:
            case ATTACK:
            case USE_SPECIAL_POWER:
        }
    }

    private void graveYardCommandManagement(Request request, GraveYardCommand graveYardCommand) {
        switch (graveYardCommand) {
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case SHOW_CARD:
            case SHOW_INFO:
        }
    }

    private void collectableCommandManagement(Request request, CollectableCommand collectableCommand) {
        switch (collectableCommand) {
            case SHOW_INFO:
            case EXIT:
                request.getKindOfOrder().remove(request.getKindOfOrder().size() - 1);
                break;
            case USE:
        }
    }


}
