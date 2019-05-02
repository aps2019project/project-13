package View;

import Controller.GameController;
import Model.*;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class Show {
    private static final Show show = new Show();

    private Show() {

    }

    public static Show getInstance() {
        return show;
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

    public void showError(Error error) {
        System.out.println(error.toString());
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
                System.out.println(card.toString());
        }
        for (Item item : items) {
            System.out.println(item.toString());
        }
        for (Card card : cards) {
            if (card.getCardKind() == CardKind.MINION)
                System.out.println(card.toString());
        }
    }

    public void showLeaderBoard() {
        Account.sortAccounts();
        for (Account account : Account.getAccounts()) {
            System.out.println(account.toString());
        }

    }

    public void enterInBattle() {
        System.out.println("Enter Game Mode :\n1.Single Player\n2.Multi Player\n(1 , 2)? ");
    }

    public void invalidNumberForMode() {
        System.out.println("1 or 2 !!? :)");
    }

    public void enterInBattleSecondStep() {
        System.out.println("Enter Game Goal :\n1.Hold Flag\n2.Collect Flag\n3.Kill Hero\n(1,2,3)?");
    }

    public void invalidNumberForGoal() {
        System.out.println("1 or 2 or 3 !!? :)");
    }

    public void battleShowAnStringArrayList(ArrayList<String> minions) {
        for (String string : minions) {
            System.out.println(string);
        }
    }
    public void showHand(ArrayList<Card> cards){
        for (Card card : cards) {
            if (card.getCardKind() == CardKind.HERO)
                System.out.println(card.toString());
        }
        for (Card card : cards) {
            if (card.getCardKind() == CardKind.MINION)
                System.out.println(card.toString());
        }
    }

    public void battleShowNextCard(String nextCardInfo) {
        System.out.println(nextCardInfo);
    }

    public void showDeck(String deck){
        System.out.println(deck);
    }

}