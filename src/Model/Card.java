package Model;

import Model.BuffClasses.ABuff;

import java.util.ArrayList;

public class Card implements Cloneable{

    private static ArrayList<Card> allCards = new ArrayList<>();
    private String cardId;
    private int manaCost;
    private int darikCost;
    private Cell currentCell;
    private CardKind cardKind;
    private Account account;
    private String cardDescription;
    private String cardName;
    private ArrayList<ABuff> buffs = new ArrayList<>();
    private boolean isAbleToMove;
    private boolean isInGame;

    public Card(String cardName, String cardId, int manaCost, int darikCost, CardKind cardKind, String cardDescription) {
        this.cardName = cardName;
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

    public static Card findCardInArrayListByName(String cardName, ArrayList<Card> cards) {
        if (cards != null) {
            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) != null && cards.get(i).getCardName().equals(cardName)) {
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

    public void addBuff(ABuff buff) {
        buffs.add(buff);
    }

    public void deleteBuff(ABuff buff) {
        buffs.remove(buff);
    }

    public void clearAllBuffs() {
        buffs.clear();
    }

    public ArrayList<ABuff> getBuffs() {
        return buffs;
    }

    public void setBuffs(ArrayList<ABuff> buffs) {
        this.buffs = buffs;
    }

    private void addCard(Card card) {
        allCards.add(card);
    }

    public static Card getCard(String cardId) {
        return findCardInArrayList(cardId, getAllCards());
    }

    public static ArrayList<Card> cardArrayListSorter(ArrayList<Card> sourceCards) {
        if (sourceCards != null) {
            ArrayList<Card> copy = (ArrayList<Card>) sourceCards.clone();
            ArrayList<Card> result = new ArrayList<>();
            for (int i = 0; i < copy.size(); i++) {
                if (copy.get(i) != null && copy.get(i).getCardKind().equals(CardKind.HERO)) {
                    result.add(copy.get(i));
                }
            }
            for (int i = 0; i < copy.size(); i++) {
                if (copy.get(i) != null && copy.get(i).getCardKind().equals(CardKind.MINION)) {
                    result.add(copy.get(i));
                }
            }
            for (int i = 0; i < copy.size(); i++) {
                if (copy.get(i) != null && copy.get(i).getCardKind().equals(CardKind.SPELL)) {
                    result.add(copy.get(i));
                }
            }


            return result;
        }
        return null;
    }


    public boolean isInGame() {
        return isInGame;
    }

    public void setInGame(boolean inGame) {
        isInGame = inGame;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    //TODO THIS MAY NEED TO BE CHANGED. THE LOGIC IS THE SAME BUT MAYBE WE NEED TO CLONE ANOTHER ABuff ArrayList.
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Warrior warrior = (Warrior) super.clone();
        ArrayList<ABuff> buffsClone = ABuff.aBuffClone(this.getBuffs());
        warrior.setBuffs(buffsClone);
        return warrior;
    }
}
