package Model;

import View.ConstantMessages;
import View.Error;

import java.util.ArrayList;

public class Shop {

    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<UsableItem> items = new ArrayList<>();
    private static Shop instance;

    private Shop() {

    }

    public static Shop getInstance() {
        if (instance == null)
            instance = new Shop();
        return instance;
    }

    public void buy(String name, Account account) {
        Card card = searchAndGetCard(name);
        UsableItem item = searchAndGetItem(name);
        if (card == null)
            System.out.println(":FFFFFFFF");
        if (card == null && item == null)
            throw new Error(ConstantMessages.NOT_IN_SHOP.getMessage());

        if (card != null) {
            if (card.getDarikCost() <= account.getDarick()) {
                account.getCardCollection().addCard(card);
                cards.remove(card);
                account.decreaseDarick(card.getDarikCost());
            } else
                throw new Error(ConstantMessages.NOT_ENOUGH_MONEY.getMessage());
        } else {
            if (validBuyLimitOfItem(Account.getLoginedAccount())) {
                if (item.getDarickCost() <= account.getDarick()) {
                    account.getCardCollection().addItem(item);
                    items.remove(item);
                    account.decreaseDarick(item.getDarickCost());
                } else
                    throw new Error(ConstantMessages.NOT_ENOUGH_MONEY.getMessage());
            } else
                throw new Error(ConstantMessages.MORE_THAN_3_ITEM.getMessage());
        }

    }

    private boolean validBuyLimitOfItem(Account account) {
        return account.getCardCollection().getItems().size() <= 3;
    }

    public void sell(String cardId, Account account) {
        Card card = account.getCardCollection().findCard(cardId);
        if (card != null) {
            account.increaseDarick(card.getDarikCost());
            account.getCardCollection().removeCard(card);
            addCard(card);
        } else {
            throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
        }
    }

    private Card searchAndGetCard(String name) {
        return Card.findCardInArrayListByName(name, cards);
    }

    public UsableItem searchAndGetItem(String name) {
        return UsableItem.findUsableItemInArrayList(name, getItems());
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<UsableItem> getItems() {
        return items;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addItem(UsableItem item) {
        items.add(item);
    }

    public String searchCardInShop(String cardName) {
        for (Card card :
                Shop.getInstance().getCards()) {
            if (card.getCardName().equals(cardName)) {
                return card.getCardId();
            }
        }
        throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
    }

}
