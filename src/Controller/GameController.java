package Controller;

import View.*;
import Model.*;
import View.Error;


import java.util.ArrayList;

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
        showMenu(KindOfOrder.ACCOUNT);
        Request request = Request.getInstance();
        while (!isFinish) {
            try {
                request.getRequest();
                commandManagement(request, request.getKindOfOrders().get(request.getKindOfOrders().size() - 1));
            } catch (Error | CloneNotSupportedException e) {
                show.showError(e);
            }
        }
    }

    private void commandManagement(Request request, KindOfOrder kindOfOrder) throws Error, CloneNotSupportedException {
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
                battleCommandManagement(request.getBattleCommand());
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

    public void showMenu(KindOfOrder kindOfOrder) {
        show.showMenu(kindOfOrder);
    }

    private void shopCommandManagement(Request request, ShopCommand shopCommand) throws Error, CloneNotSupportedException {

        Shop shop = Shop.getInstance();
        Show show = Show.getInstance();

        switch (shopCommand) {
            case BUY:
                shop.buy(shopCommand.getData(), Account.getLoginedAccount());
                show.successfulBuy();
                break;
            case EXIT:
                request.exitLastmenu();
                break;
            case HELP:
                show.showHelp(KindOfOrder.SHOP);
                break;
            case SELL:
                shop.sell(shopCommand.getData(), Account.getLoginedAccount());
                show.successfulSell();
                break;
            case SHOW:
                show.showShopCards();
                break;
            case SEARCH:
                show.showCardId(shop.searchCardInShop(shopCommand.getData()));
                break;
            case SHOW_COLLECTION:
                show.showCollection(Account.getLoginedAccount());
                break;
            case SEARCH_COLLECTION:
                show.showCardId(Account.getLoginedAccount().getCardCollection().searchCardInCollection(shopCommand.getData()));
        }
    }

    private void mainMenuCommandManagement(Request request, MainCommand mainCommand) {
        switch (mainCommand) {
            case ENTER_HELP:
                show.showHelp(KindOfOrder.MAIN_MENU);
                break;
            case ENTER_EXIT:
                request.exitLastmenu();
                break;
            case ENTER_SHOP:
                request.addNewMenu(KindOfOrder.SHOP);
                break;
            case ENTER_BATTLE:
                request.addNewMenu(KindOfOrder.BATTLE);
                startBattle(request);
                break;
            case ENTER_COLLECTION:
                request.addNewMenu(KindOfOrder.COLLECTION);
        }
    }

    private void accountCommandManagement(Request request, AccountCommand accountCommand) throws Error {
        switch (accountCommand) {
            case EXIT:
                isFinish = true;
                break;
            case HELP:
                show.showHelp(KindOfOrder.ACCOUNT);
                break;
            case SAVE:
                Account.saveAccount();
                break;
            case LOGIN:
                Account.login(request, accountCommand);
                break;
            case LOGOUT:
                Account.setLoginedAccount(null);
                break;
            case CREATE_ACCOUNT:
                Account.createAccount(request, accountCommand);
                break;
            case SHOW_LEADERBOARD:
                show.showLeaderBoard();
        }
    }


    private void battleCommandManagement(BattleCommand battleCommand) throws Error {

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
                battleShowCardInfo(battleCommand.getData().get(0));
                break;
            case SHOW_NEXT_CARD:
                battleShowNextCard();
                break;
            case ENTER_GRAVE_YARD:
                battleEnterGraveyard();
                break;
            case SHOW_MY_MINIONS:
                show.battleShowAnStringArrayList(battleShowMinion(true));
                break;
            case SHOW_COLLECTABLE:
                show.battleShowAnStringArrayList(battleShowCollectables());
                break;
            case SHOW_OPPONENT_MINIONS:
                show.battleShowAnStringArrayList(battleShowMinion(false));
                break;
            case EXIT:
                Request.getInstance().exitLastmenu();
                if (Battle.getRunningBattle() != null) {
                    Battle.getRunningBattle().showMap();
                }
        }
    }

    public void exitFromBattle() {
        battleCommandManagement(BattleCommand.EXIT);
    }

    private void startBattle(Request request) throws Error {
        show.enterInBattle();
        String gameModeNumber = request.getNumberForKindOfBattle();
        gameModeNumber = getGameMode(request, gameModeNumber);
        show.enterInBattleSecondStep();
        String gameGoalNumber = request.getNumberForKindOfBattle();
        gameGoalNumber = getGameGoal(request, gameGoalNumber);
        GameGoal gameGoal;
        GameMode gameMode;
        Account account = null;
        gameMode = getGameMode(gameModeNumber);
        account = getSecondAccount(request, gameMode, account);
        gameGoal = getGameGoal(gameGoalNumber);
        if (setBattleForCollectFlag(request, gameGoal, gameMode, account)) return;
        setBattle(gameGoal, gameMode, account);

    }

    private void setBattle(GameGoal gameGoal, GameMode gameMode, Account account) throws Error {
        show.startBattle();
        if (gameMode == GameMode.SINGLEPLAYER)
            new Battle(Account.getLoginedAccount(), null, gameMode, gameGoal);//TODO AI
        else new Battle(Account.getLoginedAccount(), account, gameMode, gameGoal);
    }

    private boolean setBattleForCollectFlag(Request request, GameGoal gameGoal, GameMode gameMode, Account account) throws Error {
        show.startBattle();
        if (gameGoal == GameGoal.COLLECT_FLAG) {
            int numberOfFlagForWin = 0;
            while (numberOfFlagForWin < 1) {
                show.numberOfFlag();
                String numberOfFlag = request.getNumberOfFlag();

                try {
                    numberOfFlagForWin = Integer.parseInt(numberOfFlag);
                } catch (Exception e) {
                    show.invalidNumberForFlag();
                }
            }
            if (gameMode == GameMode.SINGLEPLAYER)
                new Battle(Account.getLoginedAccount(), null, gameMode, gameGoal).setNumberOfFlagForWin(numberOfFlagForWin);//TODO AI
            else
                new Battle(Account.getLoginedAccount(), account, gameMode, gameGoal).setNumberOfFlagForWin(numberOfFlagForWin);
            return true;
        }
        return false;
    }

    private GameGoal getGameGoal(String gameGoalNumber) {
        GameGoal gameGoal;
        if (gameGoalNumber.equals("1")) {
            gameGoal = GameGoal.HOLD_FLAG;
        } else if (gameGoalNumber.equals("2")) {
            gameGoal = GameGoal.COLLECT_FLAG;
        } else gameGoal = GameGoal.KILL_HERO;
        return gameGoal;
    }

    private Account getSecondAccount(Request request, GameMode gameMode, Account account) {
        if (gameMode == GameMode.MULTIPLAYER) {
            show.showAccountsForMultiPlayer(Account.getLoginedAccount());
            String nameOfSecondPlayer = request.getNameOfSecondPlayer();
            account = Account.getAccount(nameOfSecondPlayer, null);
            while (account == null || account.equals(Account.getLoginedAccount())) {
                if (account == null) {
                    show.notFoundSecondPlayer();
                } else show.chooseSelfForBattle();
                show.showAccountsForMultiPlayer(Account.getLoginedAccount());
                nameOfSecondPlayer = request.getNameOfSecondPlayer();
                account = Account.getAccount(nameOfSecondPlayer, null);
            }
        }
        return account;
    }

    private GameMode getGameMode(String gameModeNumber) {
        GameMode gameMode;
        if (gameModeNumber.equals("1")) {
            gameMode = GameMode.SINGLEPLAYER;
        } else gameMode = GameMode.MULTIPLAYER;
        return gameMode;
    }

    private String getGameMode(Request request, String gameModeNumber) {
        while (!gameModeNumber.equals("1") && !gameModeNumber.equals("2")) {
            show.invalidNumberForMode();
            show.enterInBattle();
            gameModeNumber = request.getNumberForKindOfBattle();
        }
        return gameModeNumber;
    }

    private String getGameGoal(Request request, String gameGoalNumber) {
        while (!gameGoalNumber.equals("1") && !gameGoalNumber.equals("2") && !gameGoalNumber.equals("3")) {
            show.invalidNumberForGoal();
            show.enterInBattleSecondStep();
            gameGoalNumber = request.getNumberForKindOfBattle();
        }
        return gameGoalNumber;
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
        for (int i = 0; i < Map.MAX_ROW; i++) {
            for (int j = 0; j < Map.MAX_COLUMN; j++) {
                Cell cell = map.getCell(i, j);
                if (cell.getCard().getAccount().equals(account)) {
                    output.add(cell.getCard().toString() + "- Position: " + i + 1 + " , " + j + 1);
                }
            }
        }
        return output;
    }

    private ArrayList<String> battleShowCollectables() {
        ArrayList<String> outPut = new ArrayList<>();
        for (Item item : Account.getLoginedAccount().getCollectableItems()) {
            outPut.add(item.toString());
        }
        return outPut;
    }

    private void battleEnterGraveyard() {
        Request.getInstance().addNewMenu(KindOfOrder.GRAVEYARD);
    }

    private void battleShowNextCard() {
        Battle battle = Battle.getRunningBattle();
        show.battleShowNextCard(battle.getNextCard().toString());
    }

    private void battleShowCardInfo(String cardID) {
        Battle battle = Battle.getRunningBattle();
        Card card = Card.findCardInArrayList(cardID, battle.getFirstPlayerInGameCards());
        if (card != null) {
            Show.getInstance().showBattleCardInfo(card);
        } else {
            card = Card.findCardInArrayList(cardID, battle.getSecondPlayerInGameCards());
            if (card != null)
                Show.getInstance().showBattleCardInfo(card);
        }
        if (card == null)
            throw new Error(ConstantMessages.INVALID_CARD_ID.getMessage());
    }

    private void battleShowHand() {
        Battle battle = Battle.getRunningBattle();
        if (battle.getTurn() % 2 == 1) {
            show.showHand(battle.getFirstPlayerHand());

        } else {
            show.showHand(battle.getSecondPlayerHand());
        }
    }

    private void battleGameInfo() {
        Battle battle = Battle.getRunningBattle();
        show.showBattleInfo(battle.toString());
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
        ArrayList<Card> hand;
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
        int x, y;
        try {
            x = Integer.parseInt(battleCommand.getData().get(1));
            y = Integer.parseInt(battleCommand.getData().get(2));
        } catch (Exception e) {
            throw new Error(ConstantMessages.INVALID_COMMAND.getMessage());
        }
        Battle battle = Battle.getRunningBattle();
        battle.insertCard(cardName, x, y);
    }

    private void collectionCommandManagement(Request request, CollectionCommand collectionCommand) throws Error {
        switch (collectionCommand) {
            case HELP:
                show.showHelp(KindOfOrder.COLLECTION);
                break;
            case SAVE:
                Account.saveAccount();
                break;
            case EXIT:
                request.exitLastmenu();
                break;
            case SEARCH:
                collectionSearch(collectionCommand);
                break;
            case SHOW:
                collectionShow();
                break;
            case SHOW_DECK:
                collectionShowDeck(collectionCommand);
                break;
            case ADD_TO_DECK:
                collectionAddToDeck(collectionCommand);
                break;
            case CREATE_DECK:
                collectionCreateDeck(collectionCommand);
                break;
            case DELETE_DECK:
                collectionDeleteDeck(collectionCommand);
                break;
            case SELECTE_DECK:
                collectionSelectMainDeck(collectionCommand);
                break;
            case SHOW_ALL_DECK:
                collectionShowAllDeck();
                break;
            case VALIDATE_DECK:
                collectionValidateDeck(collectionCommand);
                break;
            case REMOVE_FROM_DECK:
                collectionRemoveCardFromDeck(collectionCommand);
                break;
        }
    }

    private void collectionSearch(CollectionCommand collectionCommand) throws Error {
        Account account = Account.getLoginedAccount();
        String name = collectionCommand.getData().get(0);
        ArrayList<String> ID = account.getCardCollection().search(name);
        if (ID != null) {
            show.showCardId(ID);
        } else
            throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
    }

    private void collectionShow() {
        show.showCollection(Account.getLoginedAccount());
    }

    private void collectionShowDeck(CollectionCommand collectionCommand) throws Error {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = Account.getLoginedAccount().findDeck(deckName);
        if (deck != null) {
            show.showDeck(deck.toString());
        } else throw new Error(ConstantMessages.DECK_NOT_EXIST.getMessage());

    }

    private void collectionRemoveCardFromDeck(CollectionCommand collectionCommand) throws Error {
        String cardName = collectionCommand.getData().get(0);
        String deckName = collectionCommand.getData().get(1);
        Deck deck = Account.getLoginedAccount().findDeck(deckName);
        if (deck != null) {
            deck.removeCard(cardName);
        } else throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
    }

    private void collectionShowAllDeck() {
        Account account = Account.getLoginedAccount();
        for (int i = 0; i < account.getDecks().size(); i++) {
            Deck deck = account.getDecks().get(i);
            if (deck != null) {
                show.showDeck(deck.toString());
            }
        }
    }

    private void collectionValidateDeck(CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = Account.getLoginedAccount().findDeck(deckName);
        if (deck == null)
            throw new Error(ConstantMessages.DECK_NOT_EXIST.getMessage());
        Deck.validateDeck(deck);
        show.printAMessage(ConstantMessages.VALID_DECK.getMessage());
    }

    private void collectionSelectMainDeck(CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = Account.getLoginedAccount().findDeck(deckName);
        if (deck != null) {
            Account.getLoginedAccount().setMainDeck(deck);
            show.printAMessage(ConstantMessages.MAIN_DECK_SELECTED.getMessage());
        } else
            throw new Error(ConstantMessages.DECK_NOT_EXIST.getMessage());
    }

    private void collectionDeleteDeck(CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        Deck deck = Account.getLoginedAccount().findDeck(deckName);
        if (deck != null) {
            if (Account.getLoginedAccount().getMainDeck().equals(deck)) {
                Account.getLoginedAccount().setMainDeck(null);
            }
            Account.getLoginedAccount().getDecks().remove(deck);
        }
    }

    private void collectionCreateDeck(CollectionCommand collectionCommand) {
        String deckName = collectionCommand.getData().get(0);
        if (Account.getLoginedAccount().findDeck(deckName) == null) {
            Deck.createDeck(deckName, Account.getLoginedAccount());
        } else
            throw new Error(ConstantMessages.DECK_EXIST.getMessage());
    }

    private void collectionAddToDeck(CollectionCommand collectionCommand) {
        String cardId = collectionCommand.getData().get(0);
        String deckName = collectionCommand.getData().get(1);
        Deck deck = Account.getLoginedAccount().findDeck(deckName);
        Card card = Card.findCardInArrayList(cardId, Account.getLoginedAccount().getCardCollection().getCards());
        if (deck == null)
            throw new Error(ConstantMessages.DECK_NOT_EXIST.getMessage());
        if (card == null)
            throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
        if (CardCollection.getCountOfCard(deck.getCards(), card) < CardCollection.getCountOfCard(Account.getLoginedAccount().getCardCollection().getCards(), card))
            deck.addCard(card);
        else
            throw new Error(ConstantMessages.NOT_ENOUGH_CARD_TO_ADD_TO_DECK.getMessage());
    }

    private void cardCommandManagement(Request request, CardCommand cardCommand) throws Error {
        switch (cardCommand) {
            case EXIT:
                request.exitLastmenu();
                break;
            case MOVE:
                cardCommandMove(cardCommand);
                break;
            case ATTACK:
                Battle battle = Battle.getRunningBattle();
                battle.attack(cardCommand.getData().get(0), (Warrior) battle.getSelectedCard(), true);
                break;
            case USE_SPECIAL_POWER:
                useSpecialPower(cardCommand);
        }
    }

    private void useSpecialPower(CardCommand cardCommand) throws Error {
        Battle battle = Battle.getRunningBattle();
        int x = Integer.parseInt(cardCommand.getData().get(0));
        int y = Integer.parseInt(cardCommand.getData().get(1));
        battle.useSpecialPower(null, x, y);
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
            Cell cell = battle.getMap().getCell(x, y);
            if (cell != null)
                battle.moveCard(battle.getMap().getCell(x, y));
            throw new Error(ConstantMessages.INVALID_CELL_TO_MOVE.getMessage());
        } catch (Error e) {
            throw new Error(ConstantMessages.INVALID_CELL_TO_MOVE.getMessage());
        }
    }

    private void graveYardCommandManagement(Request request, GraveYardCommand graveYardCommand) {
        switch (graveYardCommand) {
            case EXIT:
                request.exitLastmenu();
                break;
            case SHOW_CARDS:
                showCardsOfGraveYard();
                break;
            case SHOW_INFO:
                graveyardShowInfo(graveYardCommand);
                break;
        }
    }

    private void showCardsOfGraveYard() {
        Battle battle = Battle.getRunningBattle();
        int turn = battle.getTurn();
        ArrayList<Card> cards;
        if (turn % 2 == 1) {
            cards = battle.getFirstPlayerGraveYard();
        } else cards = battle.getSecondPlayerGraveYard();
        show.showHand(cards);
    }

    private void graveyardShowInfo(GraveYardCommand graveYardCommand) throws Error {
        String cardId = graveYardCommand.getData();
        Battle battle = Battle.getRunningBattle();
        if (battle.getTurn() % 2 == 1) {
            Card card = Card.findCardInArrayList(cardId, battle.getFirstPlayerGraveYard());
            if (card != null) {
                show.showCard(card);
            } else {
                throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
            }
        } else {
            Card card = Card.findCardInArrayList(cardId, battle.getSecondPlayerInGameCards());
            if (card != null) {
                show.showCard(card);
            } else {
                throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
            }
        }
    }

    private void collectableCommandManagement(Request request, CollectableCommand collectableCommand) {
        switch (collectableCommand) {
            case SHOW_INFO:

            case EXIT:
                request.getKindOfOrders().remove(request.getKindOfOrders().size() - 1);
                break;
            case USE:

        }
    }


}
