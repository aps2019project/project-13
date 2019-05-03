package Model;

import Model.Buff;
import Model.BuffClasses.ABuff;
import Model.Card;

import java.util.ArrayList;

public class Cell {
    private Card card;
    private int row;
    private int column;
    private Item item;
    private ArrayList<ABuff> buffs = new ArrayList<>();

    Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isEmpty() {
        return !(this.getCard() instanceof Warrior);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ArrayList<ABuff> getBuffs() {
        return buffs;
    }

    public void addBuff(ABuff buff) {
        buffs.add(buff);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
