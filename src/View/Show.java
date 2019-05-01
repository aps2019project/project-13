package View;

import Model.*;

import java.util.ArrayList;
import java.util.Collections;

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
        System.out.println(error.getMessage());
    }

    public void showMainMenu() {
        System.out.println(ConstantMessages.MAIN_MENU.getMessage());
    }

    public void showShopCards() {
        Shop shop = Shop.getInstance();
        showInProperFormat("Shop", null, shop);
    }

    public void showCardId(String cardId) {
        System.out.println(cardId);
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
            System.out.println(card.toString());
        }
    }

}