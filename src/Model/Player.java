package Model;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private Account account;
    private int capacityMana = 2;
    private int currentMana = 2;
    private ArrayList<Card> graveyard = new ArrayList<>();
    private ArrayList<Card> hand = new ArrayList<>();
    private Card nextCard;
    private Deck deck;
    private ArrayList<Card> inGameCards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private int flags;

    public Player() {

    }

    public void incrementFlags(int i) {
        setFlags(getFlags() + 1);
    }

    public void setNextCard() {
        Random random = new Random();
        nextCard = deck.getCards().get(random.nextInt(getDeck().getCards().size()));
    }

    public Player(Account account, Deck deck) {
        this.account = account;
        this.deck = deck;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getCapacityMana() {
        return capacityMana;
    }

    public void setCapacityMana(int capacityMana) {
        this.capacityMana = capacityMana;
    }

    public ArrayList<Card> getGraveyard() {
        return graveyard;
    }

    public void setGraveyard(ArrayList<Card> graveyard) {
        this.graveyard = graveyard;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand() {
        hand = Card.selectRandomCardsForHand(deck.getCards(), 5);
    }

    public void setNextCard(Card nextCard) {
        this.nextCard = nextCard;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public Card getNextCard() {
        return nextCard;
    }


    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public ArrayList<Card> getInGameCards() {
        return inGameCards;
    }

    public void setInGameCards(ArrayList<Card> inGameCards) {
        this.inGameCards = inGameCards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }
}
