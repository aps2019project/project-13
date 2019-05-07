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

    public ArrayList<String> search(String name) {
        ArrayList<String> Ids = new ArrayList<>();
        for (Card card :
                getCards()) {
            if (card.getCardName().equals(name))
                Ids.add(card.getCardId());
        }
        if (!Ids.isEmpty())
            return Ids;
        for (Item item :
                getItems()) {
            if (item.getItemName().equals(name))
                Ids.add(item.getItemId());
        }
        if (!Ids.isEmpty())
            return Ids;
        return null;
    }


    public Account getAccount() {
        return account;
    }

    private void setAccount(Account account) {
        this.account = account;
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