package Model;

import Model.Battle;
import Model.Card;
import Model.Cell;
import Model.Item;

import java.util.ArrayList;

public class Map {


    private  Cell[][] cells = new Cell[5][9];
    private Battle battle;

    Map(Battle battle) {
        this.battle = battle;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public  Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getDistanceOfTwoCell(Cell firstCell, Cell secondCell) {
        return Math.abs(firstCell.getColumn() - secondCell.getColumn()) + Math.abs(firstCell.getRow() - secondCell.getRow());

    }

    public Item getItem(Cell cell) {
        return cell.getItem();
    }

    public void moveCard(Card card, Cell sourceCell, Cell destinationCell) {
        sourceCell.setCard(null);
        destinationCell.setCard(card);
    }

    public void insertCard(Card card, Cell cell) {
        if (cell.getCard() == null)
            return;
        cell.setCard(card);
    }

    public Battle getBattle() {
        return battle;
    }
}
