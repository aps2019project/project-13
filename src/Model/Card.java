package Model;

import java.util.ArrayList;

public class Card {

    private static ArrayList<Card> allCards = new ArrayList<>();
    private String cardId;
    private int manaCost;
    private int darikCost;
    private Cell currentCell;
    private CardKind cardKind;
    private Account account;
    private String cardDescription;
    private transient ArrayList<Buff> buffs = new ArrayList<>();
    private boolean isAbleToMove;

    public Card(String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription) {
        this.cardId = cardId;
        this.manaCost = manaCost;
        this.darikCost = darikCost;
        this.cardKind = cardKind;
        this.cardDescription = cardDescription;
        addCard(this);
    }

    public static Card findCardInArrayList(String cardId, ArrayList<Card> cards) {
        if (cards != null) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) != null && cards.get(i).getCardId().equals(cardId)) {
                    return cards.get(i);
                }
            }
        }
        return null;
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

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public void deleteBuff(Buff buff) {
        buffs.remove(buff);
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    private void addCard(Card card) {
        allCards.add(card);
    }

    public static Card getCard(String cardId) {
        return findCardInArrayList(cardId, getAllCards());
    }


}
