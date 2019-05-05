
package Model;

import java.util.ArrayList;

public class Deck implements Cloneable {
    private static ArrayList<Deck> decks = new ArrayList<>();
    private ArrayList<Card> cards;
    private ArrayList<Minion> minions;
    private Hero hero;
    private Item item;
    private Account account;
    private String deckName;
    private boolean isValid = false;

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
        account.addDeck(new Deck(deckName, account));
    }

    public void deleteItem() {
        setItem(null);
    }

    public static boolean validateDeck(Deck deck) {
        return deck.getHero() != null && countOfMinionsInDeck(deck) == 20;
    }

    public static boolean deckHasCard(String cardId, Deck deck) {
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (deck.getCards().get(i).getCardId().equals(cardId))
                return true;
        }
        return false;
    }


    private static int countOfMinionsInDeck(Deck deck) {
        int sum = 0;
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (deck.getCards().get(i).getCardKind() == CardKind.MINION) {
                sum++;
            }
        }
        return sum;
    }

    public void addCard(Card card) {
        if (card != null) {
            if (card instanceof Hero) {
                addHero((Hero) card);
            } else if (card instanceof Minion) {
                addMinion((Minion) card);
            } else getCards().add(card);
        }
    }

    private void addHero(Hero hero) {
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

    private void addMinion(Minion minion) {
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

    private static Deck findDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck != null && deck.getDeckName().equals(deckName)) {
                return deck;
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

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isValid() {
        return isValid;
    }

    public static Deck AiDeckBuilder(int i) {
        int[] heroNumber = {1, 5, 7};
        int[][] spellNumbers = {{1, 7, 10, 11, 12, 18, 20}, {2, 3, 5, 9, 8, 13, 19}, {6, 10, 12, 14, 15, 16, 17}};
        int[][] minionNumbers = {{1, 9, 11, 11, 13, 17, 18, 21, 22, 26, 38, 36, 40}, {2, 3, 5, 8, 12, 15, 15, 19, 23, 27, 30, 33, 39}, {6, 7, 10, 14, 16, 16, 20, 24, 25, 28, 29, 31, 34}};
        int[] item = {1, 10, 5};
        return AiDeckBuilderUtility(heroNumber[i], spellNumbers[i], minionNumbers[i], item[i]);
    }

    public static Deck AiDeckBuilderUtility(int heroNumber, int[] spellNumbers, int[] minionNumbers, int item) {
        int counter = 1;
        Account AI = new Account("AI", "password");


        Deck deck = new Deck("AI_Deck1", AI);
        Hero hero = (Hero) Card.findCardInArrayList("Hero_" + heroNumber, Shop.getInstance().getCards());
        Hero hero1 = (Hero) Hero.deepClone(hero);
        hero1.setCardId(Card.makeNewID(AI.getUsername(), hero1.getCardName(), counter++));
        deck.addHero(hero);
        for (int i = 0; i < spellNumbers.length; i++) {
            Spell spell = Spell.deepClone((Spell) Spell.findCardInArrayList("Spell_" + spellNumbers[i], Shop.getInstance().getCards()));
            spell.setCardId(Card.makeNewID(AI.getUsername(), spell.getCardName(), counter++));
            deck.addCard(spell);
        }
        for (int i = 0; i < minionNumbers.length; i++) {
            Minion minion = (Minion) Minion.deepClone((Warrior) Minion.findCardInArrayList("Minion_" + minionNumbers[i], Shop.getInstance().getCards()));
            minion.setCardId(Card.makeNewID(AI.getUsername(), minion.getCardName(), counter++));
            deck.addCard(minion);
        }
        //TODO CHECK DOWNCASTING
        UsableItem item1 =(UsableItem) Item.deepClone( Shop.getInstance().searchAndGetItem("UsableItem_" + item));
        deck.setItem(item1);
        return deck;
        //UsableItem.findItemInArrayList("1",Shop.getInstance().getItems())


    }

}
