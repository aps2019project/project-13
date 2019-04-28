package Model;

public class FlagForHoldFlagGameMode extends Item {

    private int numberOfTurns;
    private Warrior flagHolder;
    private Cell currentCell = Map.getCell(2, 4);


    public FlagForHoldFlagGameMode(String itemId, String itemDescription, ItemKind itemKind) {
        super(itemId, itemDescription, itemKind, null);
    }

    public void incrementNumberOfTurns() {
        numberOfTurns++;
    }

    public void setFlagHolder(Warrior flagHolder) {
        this.flagHolder = flagHolder;
        numberOfTurns = 0;
    }

    public void updateFlagCell() {
        if (flagHolder != null)
            currentCell = flagHolder.getCurrentCell();
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
