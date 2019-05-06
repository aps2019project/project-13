package View;

import Controller.GameController;
import Model.*;

import java.util.ArrayList;

public class Show {
    private static final Show show = new Show();

    private Show() {

    }

    public static Show getInstance() {
        return show;
    }

    public void printAMessage(String message) {
        System.out.println(message);
    }

    public void showHelp(KindOfOrder kindOfOrder) {
        switch (kindOfOrder) {
            case COLLECTABLE:
                showCollectabletHelp();
                break;
            case GRAVEYARD:
                showGraveYardHelp();
                break;
            case SHOP:
                showShopHelp();
                break;
            case BATTLE:
                showBattleHelp();
                break;
            case ACCOUNT:
                showAccountHelp();
                break;
            case MAIN_MENU:
                showMainMenuHelp();
                break;
            case COLLECTION:
                showCollectionHelp();
                break;
        }
    }

    private void showAccountHelp() {
        System.out.println(ConstantMessages.HELP_SHOW.getMessage());
    }

    public void getPassword() {
        System.out.println(ConstantMessages.GET_PASSWORD.getMessage());
    }

    public void unreliablePassWord() {
        System.out.println(ConstantMessages.UNRELIABLE_PASSWORD.getMessage());
    }

    public void createdAccount(String username) {
        System.out.println("new account " + username + " is created");
    }

    public void getYourPasWord() {
        System.out.println(ConstantMessages.YOUR_PASSWORD.getMessage());
    }

    public void incorrectPassWord() {
        System.out.println(ConstantMessages.INVALID_PASSWORD.getMessage());
    }

    private void showCollectabletHelp() {
        System.out.println(ConstantMessages.COLLECTABLE_HELP.getMessage());
    }

    private void showGraveYardHelp() {
        System.out.println(ConstantMessages.GRAVEYARD_HELP.getMessage());
    }

    private void showShopHelp() {
        System.out.println(ConstantMessages.SHOP_HELP.getMessage());
    }

    private void showBattleHelp() {
        System.out.println(ConstantMessages.BATTLE_HELP.getMessage());
    }

    private void showCollectionHelp() {
        System.out.println(ConstantMessages.COLLECTION_HELP.getMessage());
    }

    private void showMainMenuHelp() {
        System.out.println(ConstantMessages.MAIN_MENU_HELP.getMessage());
    }

    public void showError(Exception error) {
        System.out.println(error.toString());
        if (error.toString().equals(ConstantMessages.INVALID_DECK.getMessage())) {
            GameController.getInstance().exitFromBattle();
        }
    }

    public void showMainMenu() {
        System.out.println(ConstantMessages.MAIN_MENU.getMessage());
    }

    public void showShopCards() {
        Shop shop = Shop.getInstance();
        showInProperFormat("Shop", null, shop);
    }

    public void successfulSell() {
        System.out.println(ConstantMessages.SELL_SUCCESSFUL);
    }

    public void successfulBuy() {
        System.out.println(ConstantMessages.BUY_SUCCESSFUL);
    }

    public void showCardId(String cardId) {
        System.out.println(cardId);
    }

    public void showCardId(ArrayList<String> cardIds) {
        for (String cardId : cardIds) {
            System.out.println(cardId);
        }
    }

    public void showCard(Card card) {
        System.out.println(card.toString());
    }


    public void showCollection(Account player) {
        CardCollection cardCollection = player.getCardCollection();
        showInProperFormat("Collection", cardCollection, null);
    }

    private void showInProperFormat(String kind, CardCollection cardCollection, Shop shop) {

        ArrayList<Card> cards;
        ArrayList<Item> items;

        if (kind.equals("Shop")) {
            cards = shop.getCards();
            items = new ArrayList<>(shop.getItems());
        } else {
            cards = cardCollection.getCards();
            items = cardCollection.getItems();
        }
        for (Card card : cards) {
            if (card.getCardKind() == CardKind.HERO)
                System.out.println(card.toString() + " DarickValue : " + card.getDarikCost());
        }
        for (Item item : items) {
            System.out.println(item.toString() + " DarickValue : " + ((UsableItem) item).getDarickCost());
        }
        for (Card card : cards) {
            if (card.getCardKind() == CardKind.MINION)
                System.out.println(card.toString() + " DarickValue : " + card.getDarikCost());
        }
    }

    public void showLeaderBoard() {
        Account.sortAccounts();
        for (Account account : Account.getAccounts()) {
            System.out.println(account.toString());
        }

    }

    public void enterInBattle() {
        System.out.println(ConstantMessages.GAME_MODE.getMessage());
    }

    public void invalidNumberForMode() {
        System.out.println(ConstantMessages.NUMBER_OF_GAME_MODE);
    }

    public void enterInBattleSecondStep() {
        System.out.println(ConstantMessages.GAME_GOAL.getMessage());
    }

    public void invalidNumberForGoal() {
        System.out.println(ConstantMessages.NUMBER_OF_GAME_GOAL.getMessage());
    }

    public void battleShowAnStringArrayList(ArrayList<String> minions) {
        for (String string : minions) {
            System.out.println(string);
        }
    }

    public void showHand(ArrayList<Card> cards) {
        for (Card card : cards) {
            if (card.getCardKind() == CardKind.SPELL)
                System.out.println(card.toString() + " CardId: " + card.getCardId());
        }
        for (Card card : cards) {
            if (card.getCardKind() == CardKind.MINION)
                System.out.println(card.toString() + " CardId: " + card.getCardId());
        }
    }

    public void battleShowNextCard(String nextCardInfo) {
        System.out.println(nextCardInfo);
    }

    public void showDeck(String deck) {
        System.out.println(deck);
    }

    public void showBattleInfo(String battleInfo) {
        System.out.println(battleInfo);
    }

    public void numberOfFlag() {
        System.out.println(ConstantMessages.GAME_FLAG.getMessage());
    }

    public void invalidNumberForFlag() {
        System.out.println(ConstantMessages.NUMBER_OF_GAME_FLAG.getMessage());
    }

    public void showMenu(KindOfOrder kindOfOrder) {
        System.out.println(" *** " + kindOfOrder.name() + " Menu *** ");
    }

    public void showBattleCardInfo(Card card) {
        if (card.getCardKind() == CardKind.HERO) {
            System.out.println("HeroL:\nName: " + card.getCardName() + "\n" + "Cost: "
                    + card.getDarikCost() + "\n" + "Desc: " + card.getCardDescription());
        } else if (card.getCardKind() == CardKind.MINION) {
            System.out.println("Minion:\n" + "Name: " + card.getCardName() + "\n" + "HP: " + ((Warrior) card).getHealthPoint()
                    + "AP: " + ((Warrior) card).getActionPower() + "MP: " + card.getManaCost() + "\n" + "Range: " + ((Warrior) card).getAttackRange() +
                    "\n" + "Cost: " + card.getDarikCost() + "Desc: " + card.getCardDescription());
        } else {
            System.out.println("Spell: \n" + "MP: " + card.getManaCost() + "\n" +
                    "Cost: " + card.getDarikCost() + "\n" + "Desc: " + card.getCardDescription());
        }

    }

    public void showAccountsForMultiPlayer(Account account1) {
        System.out.println("select name of second Player");
        for (Account account :
                Account.getAccounts()) {
            if (!account.equals(account1))
                System.out.println("- " + account.getUsername());
        }
    }

    public void notFoundSecondPlayer() {
        System.out.println("second player not exist ! try again");
    }

    public void chooseSelfForBattle() {
        System.out.println("This is You :| ");
    }

    public void startBattle() {
        String s = "Loading Battle...";
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public void kindOFSinglePlayer() {
        System.out.println("1.Story \n2.Custom\n(1 , 2) ? ");
    }

    public void invalidKindOfSinglePlayer() {
        System.out.println(ConstantMessages.NUMBER_OF_GAME_FLAG.getMessage());
    }

    public void chooseDeck() {
        System.out.println("Choose a deck : ");
        for (Deck deck :
                Account.getLoginedAccount().getDecks()) {
            System.out.println("- "+deck.getDeckName());
        }
    }
    public void invalidDeck(){
        System.out.println("not exist deck ! Try again ");
    }
    public void chooseAi(){
        System.out.println("we have 3 computer to play Game , choose (1 , 2 , 3)?");
    }
    public void invalidComputer(){
        System.out.println("1 or 2 or 3 ?! try again!");
    }

    public void showWinner(String userName){
        System.out.println(userName + " Win Game :)");
    }
    public void showMap() {
        Battle battle = Battle.getRunningBattle();
        Cell[][] cells = battle.getMap().getCells();
        System.out.println("*************************");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].getCard() != null) {
                    if (cells[i][j].getCard().getAccount() == null)
                        System.out.print(" * ");
                    else if (cells[i][j].getCard().getAccount().equals(battle.getFirstPlayer()))
                        System.out.print(" 1 ");
                    else System.out.print(" 2 ");
                } else System.out.print(" 0 ");
            }
            System.out.println();
        }
        System.out.println("*************************");
        System.out.println();
    }
}