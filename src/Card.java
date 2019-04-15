import java.util.ArrayList;

public class Card {

    private static ArrayList<Card> allCards = new ArrayList<Card>();
    private int cardId;
    private int manaCost;
    private int darikCost;
    private Cell currentCell;
    private CardKind cardKind;
    private Account account;
    private String cardDescription;
    private boolean isAbleToMove;

    public Card(int cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription) {
        this.cardId = cardId;
        this.manaCost = manaCost;
        this.darikCost = darikCost;
        this.cardKind = cardKind;
        this.cardDescription = cardDescription;
        addCard(this);
    }

    public static ArrayList<Card> getAllCards() {
        return allCards;
    }

    public int getCardId() {
        return cardId;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getDarikCost() {
        return darikCost;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Account getAccount() {
        return account;
    }

    public CardKind getCardKind() {
        return cardKind;
    }

    public boolean setAbleToMove(boolean flag) {
        this.isAbleToMove = flag;
    }

    public boolean isAbleToMove() {
        return this.isAbleToMove;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    private void addCard(Card card) {
        allCards.add(card);
    }

    public static Card getCard(int cardId) {
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).getCardId() == cardId)
                return allCards.get(i);
        }
        return null;
    }



}
