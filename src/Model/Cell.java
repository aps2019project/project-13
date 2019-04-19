package Model;

import Model.Buff;
import Model.Card;

import java.util.ArrayList;

public class Cell {
    private Card card;
    private int row;
    private int column;
    private Item item ;
    private ArrayList<Buff> buffs = new ArrayList<>();

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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public Item getItem() {
        return item;
    }
}
