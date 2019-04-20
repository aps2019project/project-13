package Model;

import Model.Account;
import Model.Card;
import Model.CardKind;

import java.util.ArrayList;

public class CardCollection {

    private ArrayList<Card> cards;
    private ArrayList<Item> items;
    private Account account;

    public CardCollection(Account account) {
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
    public void removeItem(Item item)
    {
        if(item!=null)
        {
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
    public Card findCard(String cardId) {
        return Card.findCardInArrayList(cardId, getCards());
    }
    public Item findItem(String itemId)
    {
        return Item.findItemInArrayList(itemId,getItems());
    }

    public boolean hasCard(String cardName) {

        return false;
    }

    public boolean hasItem(String itemName) {

        return false;
    }

    public String search(String name) {

        return "";
    }

    public Card getCard(String name) {

        return new Card(" ", 1, 1, CardKind.HERO, "");
    }

    public Item getItem(String name) {

        return new Item();
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

}