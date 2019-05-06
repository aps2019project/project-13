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

    public Object buy(String name, Account account) throws CloneNotSupportedException {
        Card card = searchAndGetCard(name);
        UsableItem item = searchAndGetItem(name);

        if (card == null && item == null)
            throw new Error(ConstantMessages.NOT_IN_SHOP.getMessage());

        if (card != null) {
            if (card.getDarikCost() <= account.getDarick()) {
                //TODO account.getCardCollection().addCard((Card) card.clone());
                Card tempCard = Card.deepClone(card);
                account.getCardCollection().addCard(tempCard);
                tempCard.setAccount(account);
                account.decreaseDarick(card.getDarikCost());
                return tempCard;
            } else
                throw new Error(ConstantMessages.NOT_ENOUGH_MONEY.getMessage());
        } else {
            if (validBuyLimitOfItem(Account.getLoginedAccount()) || account.getUsername().equals("AI")) {
                if (item.getDarickCost() <= account.getDarick()) {
                    Item cloneItem = Item.deepClone(item);
                    account.getCardCollection().addItem(cloneItem);
                    cloneItem.setAccount(account);
                    account.decreaseDarick(item.getDarickCost());
                    return cloneItem;
                } else
                    throw new Error(ConstantMessages.NOT_ENOUGH_MONEY.getMessage());
            } else
                throw new Error(ConstantMessages.MORE_THAN_3_ITEM.getMessage());
        }

    }

    private boolean validBuyLimitOfItem(Account account) {
        return account.getCardCollection().getItems().size() <= 3;
    }

    public void sell(String cardName, Account account) {
        Card card = account.getCardCollection().findCard(cardName);
        if (card != null) {
            account.increaseDarick(card.getDarikCost());
            account.getCardCollection().removeCard(card);
            for (Deck deck :
                    account.getDecks()) {
                deck.getCards().remove(card);
            }
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
