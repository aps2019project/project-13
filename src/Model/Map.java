package Model;

import Model.Battle;
import Model.Card;
import Model.Cell;
import Model.Item;
import View.ConstantMessages;
import View.Error;

import java.util.ArrayList;

public class Map {
    public static final int MAX_ROW = 5;
    public static final int MAX_COLUMN = 9;


    private Cell[][] cells = new Cell[MAX_ROW][MAX_COLUMN];
    private Battle battle;

    Map(Battle battle) {
        this.battle = battle;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= MAX_ROW || col < 0 || col >= MAX_COLUMN)
            throw new Error(ConstantMessages.INVALID_CELL_TO_INSERT_CARD.getMessage());
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

    public Cell findCardCell(String cardName) {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COLUMN; j++) {
                if (cells[i][j].getCard() != null && cells[i][j].getCard().getCardId().equals(cardName)) {
                    return cells[i][j];
                }
            }
        }
        return null;
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
