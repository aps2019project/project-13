import java.util.ArrayList;

public class Card {

    private static ArrayList<Card> allCards = new ArrayList<Card>();
    private String cardId;
    private int manaCost;
    private int darikCost;
    private Cell currentCell;
    private CardKind cardKind;
    private Account account;
    private String cardDescription;
    private boolean isAbleToMove;

    public Card(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription) {
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

    public String getCardId() {
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

    public void setAbleToMove(boolean flag) {
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

    public static Card getCard(String cardId) {
        for (int i = 0; i < allCards.size(); i++) {
            if (allCards.get(i).getCardId().equals(cardId))
                return allCards.get(i);
        }
        return null;
    }


}
