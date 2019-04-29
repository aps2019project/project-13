package Model;

import java.util.ArrayList;

public class Deck {
    private static ArrayList<Deck> decks = new ArrayList<>();
    private ArrayList<Card> cards;
    private ArrayList<Minion> minions;
    private Hero hero;
    private Item item;
    private Account account;
    private String deckName;

    //TODO DECK TO STRING

    Deck(String deckName, Account account) {
        setDeckName(deckName);
        setAccount(account);
        setCards(new ArrayList<>());
        setMinions(new ArrayList<>());
        decks.add(this);
    }

    public static void deleteDeck(Deck deck) {
        if (deck != null) {
            decks.remove(deck);
        }
    }

    public static void createDeck(String deckName, Account account) {
        new Deck(deckName, account);
    }

    public void deleteItem() {
        setItem(null);
    }

    public static boolean validateDeck(Deck deck) {
        if (deck != null && deck.getHero() != null && deck.getCards().size() == 20) {
            return true;
        }
        return false;
    }

    public void addCard(Card card) {
        //TODO TYPE OF CARD
        if (card != null) {
            getCards().add(card);
        }
    }

    public void addHero(Hero hero) {
        if (hero != null) {
            if (getHero() != null) {
                getCards().remove(getHero());
                setHero(hero);
                getCards().add(hero);
            } else {
                setHero(hero);
                getCards().add(hero);
            }
        }
    }

    public void addMinion(Minion minion) {
        if (minion != null) {
            getMinions().add(minion);
            getCards().add(minion);
        }
    }

    public static void removeCardFromDeck(String cardId, String deckName) {
        Deck deck = findDeck(deckName);
        if (deck != null) {
            deck.removeCard(cardId);
        }
    }

    public void removeCard(String cardId) {
        Card card = Card.findCardInArrayList(cardId, this.getCards());
        if (card != null) {
            getCards().remove(card);
        }

    }

    public static Deck findDeck(String deckName) {
        for (int i = 0; i < decks.size(); i++) {
            if (decks.get(i) != null && decks.get(i).getDeckName().equals(deckName)) {
                return decks.get(i);
            }
        }
        return null;
    }


    public static ArrayList<Deck> getDecks() {
        return decks;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Minion> getMinions() {
        return minions;
    }

    public void setMinions(ArrayList<Minion> minions) {
        this.minions = minions;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(deckName).append(" \n");
        ArrayList<Card> cards = Card.cardArrayListSorter(Card.cardArrayListSorter(this.cards));
        int iter = 1;
        sb.append("Hero: \n");
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != null && cards.get(i).getCardKind().equals(CardKind.HERO)) {
                sb.append(iter++).append(". ").append(cards.get(i).toString()).append("\n");
            }
        }
        sb.append("Items: \n");
        if (this.item != null) {
            sb.append(item.toString()).append(" \n");
        }
        iter = 1;
        sb.append("Cards: \n");
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) != null && !cards.get(i).getCardKind().equals(CardKind.HERO)) {
                sb.append(iter++).append(". ").append(cards.get(i).toString()).append("\n");
            }
        }
        String result = sb.toString();

        return result;
    }
}
