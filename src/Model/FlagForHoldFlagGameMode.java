package Model;

public class FlagForHoldFlagGameMode extends Item {

    private int numberOfTurns;
    private Warrior flagHolder;
    private Cell currentCell;


    public FlagForHoldFlagGameMode(String itemId, String itemDescription, ItemKind itemKind, Cell cell) {
        super(itemId, itemDescription, itemKind, null);
        currentCell = cell;
    }

    public void incrementNumberOfTurns() {
        if (flagHolder != null)
            numberOfTurns++;
    }

    public void setFlagHolder(Warrior flagHolder) {
        this.flagHolder = flagHolder;
        numberOfTurns = 0;
    }

    public void updateFlagCell() {
        if (flagHolder != null) {
            currentCell.setItem(null);
            currentCell = flagHolder.getCurrentCell();
            currentCell.setItem(this);
        }
    }

    public void updateFlagHolder() {
        if (flagHolder.getHealthPoint() <= 0) {
            flagHolder = null;
        }
    }

    public Warrior getFlagHolder() {
        return flagHolder;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }
}
