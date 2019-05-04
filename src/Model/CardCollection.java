package Model;

import Model.Account;
import Model.Card;
import Model.CardKind;
import View.ConstantMessages;
import View.Error;

import java.util.ArrayList;

public class CardCollection {

    private ArrayList<Card> cards;
    private ArrayList<Item> items;
    private Account account;

    public CardCollection(Account account) {
        cards = new ArrayList<>();
        items = new ArrayList<>();
        setAccount(account);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void removeCard(Card card) {
        if (card != null) {
            getCards().remove(card);
        }
    }

    public void removeItem(Item item) {
        if (item != null) {
            getItems().remove(item);
        }
    }

    public void addCard(Card card) {
        if (card != null) {
            getCards().add(card);
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        if (item != null) {
            getItems().add(item);
        }
    }

    public static int getCountOfCard(ArrayList<Card> cards, Card card) {
        int counter = 0;
        for (Card card1 : cards) {
            if (card1.getCardName().equals(card.getCardName()))
                counter++;
        }
        return counter;
    }

    public Card findCard(String cardId) {
        return Card.findCardInArrayListByName(cardId, getCards());
    }

    public Item findItem(String itemId) {
        return Item.findItemInArrayList(itemId, getItems());
    }

    public boolean hasCard(String cardName) {

        return false;
    }

    public boolean hasItem(String itemName) {

        return false;
    }

    public String search(String name) {
        for (Card card :
                cards) {
            if (card.getCardName().equals(name))
                return card.getCardId();
        }
        for (Item item :
                items) {
            if (item.getItemName().equals(name))
                return item.getItemName();
        }
        return null;
    }

    public Card getCard(String name) {

        return new Card("test", "22", 1, 1, CardKind.HERO, "");
    }

    public Item getItem(String name) {

        //TODO should find an specific item by its name
        return null;
    }

    public Account getAccount() {
        return account;
    }

    private void setAccount(Account account) {
        this.account = account;
    }

    public boolean validAddItem() {

        return items.size() <= 3;

    }

    public String searchCardInCollection(String cardName) {
        for (Card card :
                Account.getLoginedAccount().getCardCollection().getCards()) {
            if (card.getCardName().equals(cardName)) {
                return card.getCardId();
            }
        }
        throw new Error(ConstantMessages.CARD_NOT_EXIST.getMessage());
    }

}